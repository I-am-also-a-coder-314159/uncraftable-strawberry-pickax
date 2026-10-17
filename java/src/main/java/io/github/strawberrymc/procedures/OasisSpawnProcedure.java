package io.github.strawberrymc.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
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
            // Using the strict Mojang mapping for 1.20+
            var villageTag = TagKey.create(Registries.STRUCTURE, Identifier.parse("minecraft:village"));
            var nearestVillage = serverLevel.findNearestMapStructure(villageTag, origin, 100, false);
            
            if (nearestVillage == null) {
                return;
            }
        }

        boolean isLarge = random.nextBoolean();
        int baseRadius = isLarge ? 5 : 3;

        // Create 2 to 3 overlapping pool centers to make an organic shape
        int numPools = 2 + random.nextInt(2);
        int[][] pools = new int[numPools][3]; // Array holding X offset, Z offset, and Radius for each pool
        
        for (int i = 0; i < numPools; i++) {
            pools[i][0] = random.nextInt(baseRadius) - (baseRadius / 2); // Shift center X
            pools[i][1] = random.nextInt(baseRadius) - (baseRadius / 2); // Shift center Z
            pools[i][2] = baseRadius + random.nextInt(3);                // Individual pool radius
        }

        int maxSearch = baseRadius * 2 + 2;

        for (int dx = -maxSearch; dx <= maxSearch; dx++) {
            for (int dz = -maxSearch; dz <= maxSearch; dz++) {
                BlockPos targetPos = origin.offset(dx, 0, dz);
                
                boolean isWater = false;
                boolean isBank = false;
                
                // Check if this block falls inside ANY of the overlapping pools
                for (int i = 0; i < numPools; i++) {
                    double dist = Math.sqrt(Math.pow(dx - pools[i][0], 2) + Math.pow(dz - pools[i][1], 2));
                    
                    // Subtract random noise (0.0 to 1.2) from the radius to make the edges jagged
                    double noisyRadius = pools[i][2] - (random.nextFloat() * 1.2f);
                    
                    if (dist <= noisyRadius) {
                        isWater = true;
                        break; // No need to check other pools if it's already water
                    } else if (dist <= noisyRadius + 2) {
                        isBank = true;
                    }
                }

                if (isWater) {
                    int depth = 1 + random.nextInt(3);
                    
                    // Fill water downwards from the origin level
                    for (int dy = -1; dy >= -depth; dy--) {
                        world.setBlock(origin.offset(dx, dy, dz), Blocks.WATER.defaultBlockState(), 3);
                    }
                    
                    // Place sand floor directly beneath the water
                    BlockPos bottomPos = origin.offset(dx, -depth - 1, dz);
                    world.setBlock(bottomPos, Blocks.SAND.defaultBlockState(), 3);
                    
                    // RETAINING WALL DOWNWARD: Fill any air below the sand down to the terrain
                    BlockPos fillPos = bottomPos.below();
                    while (world.isEmptyBlock(fillPos) && fillPos.getY() > origin.getY() - 15) {
                        world.setBlock(fillPos, Blocks.SAND.defaultBlockState(), 3);
                        fillPos = fillPos.below();
                    }
                    
                } else if (isBank) {
                    BlockPos bankPos = origin.offset(dx, 0, dz);
                    world.setBlock(bankPos, Blocks.GRASS_BLOCK.defaultBlockState(), 3);
                    
                    // RETAINING WALL DOWNWARD: Build dirt pillars below the grass until they hit ground
                    BlockPos fillPos = bankPos.below();
                    while (world.isEmptyBlock(fillPos) && fillPos.getY() > origin.getY() - 15) {
                        world.setBlock(fillPos, Blocks.DIRT.defaultBlockState(), 3);
                        fillPos = fillPos.below();
                    }
                    
                    // Surface vegetation
                    BlockPos aboveBank = bankPos.above();
                    if (world.isEmptyBlock(aboveBank)) {
                        float vegChance = random.nextFloat();
                        if (vegChance < 0.15f) {
                            world.setBlock(aboveBank, Blocks.FERN.defaultBlockState(), 3);
                        } else if (vegChance < 0.35f) {
                            world.setBlock(aboveBank, Blocks.SHORT_GRASS.defaultBlockState(), 3);
                        }
                    }
                }
            }
        }

        // Tree spawning logic remains the same...
        int numTrees = isLarge ? 3 + random.nextInt(3) : 1 + random.nextInt(2);
        for (int i = 0; i < numTrees; i++) {
            double angle = random.nextDouble() * Math.PI * 2;
            int treeX = (int) (x + Math.cos(angle) * (baseRadius + 2));
            int treeZ = (int) (z + Math.sin(angle) * (baseRadius + 2));
            
            int treeY = (int) y + 2;
            while (world.isEmptyBlock(BlockPos.containing(treeX, treeY, treeZ)) && treeY > y - 5) {
                treeY--;
            }
            
            SpawnPalmTreeProcedureProcedure.execute(world, treeX, treeY + 1, treeZ);
        }
    }
}