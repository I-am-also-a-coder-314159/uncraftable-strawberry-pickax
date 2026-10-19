package io.github.strawberrymc.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;

import io.github.strawberrymc.procedures.SpawnPalmTreeProcedureProcedure;

public class OasisSpawnProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        BlockPos origin = BlockPos.containing(x, y, z);
        world.setBlock(origin, Blocks.AIR.defaultBlockState(), 3);

        RandomSource random = world.getRandom();

        if (random.nextFloat() > 0.5f) {
            return;
        }

        if (world instanceof ServerLevel serverLevel) {
            var villageTag = TagKey.create(Registries.STRUCTURE, Identifier.parse("minecraft:village"));
            var nearestVillage = serverLevel.findNearestMapStructure(villageTag, origin, 100, false);
            
            if (nearestVillage == null) {
                return;
            }
        }

        boolean isLarge = random.nextBoolean();
        int baseRadius = isLarge ? 5 : 3;

        int numPools = 2 + random.nextInt(2);
        int[][] pools = new int[numPools][3]; 
        
        for (int i = 0; i < numPools; i++) {
            pools[i][0] = random.nextInt(baseRadius) - (baseRadius / 2); 
            pools[i][1] = random.nextInt(baseRadius) - (baseRadius / 2); 
            pools[i][2] = baseRadius + random.nextInt(3);                
        }

        // Search area increased significantly to allow for a wide, seamless fade-out
        int maxSearch = baseRadius * 2 + 10;

        for (int dx = -maxSearch; dx <= maxSearch; dx++) {
            for (int dz = -maxSearch; dz <= maxSearch; dz++) {
                BlockPos targetPos = origin.offset(dx, 0, dz);
                
                boolean isWater = false;
                double minDistToWaterEdge = 999.0;
                double maxInsideDist = -999.0; // Tracks how deep inside the water boundary we are
                
                for (int i = 0; i < numPools; i++) {
                    double dist = Math.sqrt(Math.pow(dx - pools[i][0], 2) + Math.pow(dz - pools[i][1], 2));
                    double noisyRadius = pools[i][2] - (random.nextFloat() * 1.2f);
                    
                    if (dist <= noisyRadius) {
                        isWater = true;
                        double insideDist = noisyRadius - dist;
                        if (insideDist > maxInsideDist) maxInsideDist = insideDist;
                    } else {
                        double edgeDist = dist - noisyRadius;
                        if (edgeDist < minDistToWaterEdge) minDistToWaterEdge = edgeDist;
                    }
                }

                if (isWater) {
                    // Carve out airspace above the water to prevent terrain overhangs
                    for (int c = 0; c <= 3; c++) {
                        world.setBlock(origin.offset(dx, c, dz), Blocks.AIR.defaultBlockState(), 3);
                    }

                    // Tapered depth: shallow at edges, deep in center
                    int depth = 1;
                    if (maxInsideDist > 2.5) depth = 3;
                    else if (maxInsideDist > 1.0) depth = 2;
                    
                    for (int dy = -1; dy >= -depth; dy--) {
                        world.setBlock(origin.offset(dx, dy, dz), Blocks.WATER.defaultBlockState(), 3);
                    }
                    
                    BlockPos bottomPos = origin.offset(dx, -depth - 1, dz);
                    world.setBlock(bottomPos, Blocks.SAND.defaultBlockState(), 3);
                    
                    BlockPos fillPos = bottomPos.below();
                    while (world.isEmptyBlock(fillPos) && fillPos.getY() > origin.getY() - 15) {
                        world.setBlock(fillPos, Blocks.SAND.defaultBlockState(), 3);
                        fillPos = fillPos.below();
                    }
                    
                } else if (minDistToWaterEdge <= 10.0) { 
                    // Calculate fade-out chance. 100% chance near water, rapidly drops to 0% at 10 blocks out.
                    boolean placeBank = true;
                    if (minDistToWaterEdge > 4.0) {
                        float chance = (10.0f - (float)minDistToWaterEdge) / 6.0f;
                        // Squaring the chance makes the fade-out look much more natural and scattered
                        if (random.nextFloat() > (chance * chance)) placeBank = false;
                    }

                    if (placeBank) {
                        // Place bank at y-1 so it is perfectly flush with the water surface
                        BlockPos bankPos = origin.offset(dx, -1, dz);
                        
                        // Clear airspace above the bank to smoothly cut into hills
                        for (int c = 0; c <= 2; c++) {
                            world.setBlock(origin.offset(dx, c, dz), Blocks.AIR.defaultBlockState(), 3);
                        }

                        BlockState surfaceBlock;
                        boolean isMudPatch = (Math.sin(bankPos.getX() * 0.7) * Math.cos(bankPos.getZ() * 0.7)) > 0.3;
                        
                        if (isMudPatch && random.nextFloat() < 0.6f) {
                            surfaceBlock = Blocks.MUD.defaultBlockState();
                        } else if (minDistToWaterEdge <= 2.0) {
                            surfaceBlock = random.nextFloat() < 0.8f ? Blocks.SAND.defaultBlockState() : Blocks.GRASS_BLOCK.defaultBlockState();
                        } else {
                            surfaceBlock = random.nextFloat() < 0.4f ? Blocks.SAND.defaultBlockState() : Blocks.GRASS_BLOCK.defaultBlockState();
                        }

                        world.setBlock(bankPos, surfaceBlock, 3);
                        
                        BlockState filler = (surfaceBlock.getBlock() == Blocks.SAND) ? Blocks.SAND.defaultBlockState() : Blocks.DIRT.defaultBlockState();
                        BlockPos fillPos = bankPos.below();
                        while (world.isEmptyBlock(fillPos) && fillPos.getY() > origin.getY() - 15) {
                            world.setBlock(fillPos, filler, 3);
                            fillPos = fillPos.below();
                        }
                        
                        // Surface vegetation
                        BlockPos aboveBank = bankPos.above();
                        if (world.isEmptyBlock(aboveBank) && surfaceBlock.getBlock() == Blocks.GRASS_BLOCK) {
                            float vegChance = random.nextFloat();
                            // Vegetation gets rarer the further out you go
                            float distanceModifier = minDistToWaterEdge > 4.0 ? 0.3f : 1.0f; 
                            
                            if (vegChance < 0.15f * distanceModifier) {
                                world.setBlock(aboveBank, Blocks.FERN.defaultBlockState(), 3);
                            } else if (vegChance < 0.35f * distanceModifier) {
                                world.setBlock(aboveBank, Blocks.SHORT_GRASS.defaultBlockState(), 3);
                            }
                        }
                    }
                }
            }
        }

        int numTrees = isLarge ? 3 + random.nextInt(3) : 1 + random.nextInt(2);
        for (int i = 0; i < numTrees; i++) {
            double angle = random.nextDouble() * Math.PI * 2;
            int treeX = (int) (x + Math.cos(angle) * (baseRadius + 3));
            int treeZ = (int) (z + Math.sin(angle) * (baseRadius + 3));
            
            int treeY = (int) y + 2;
            while (world.isEmptyBlock(BlockPos.containing(treeX, treeY, treeZ)) && treeY > y - 5) {
                treeY--;
            }
            
            SpawnPalmTreeProcedureProcedure.execute(world, treeX, treeY + 1, treeZ);
        }
    }
}