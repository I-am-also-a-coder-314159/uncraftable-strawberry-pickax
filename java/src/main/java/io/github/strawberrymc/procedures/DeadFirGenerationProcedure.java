package io.github.strawberrymc.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class DeadFirGenerationProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        if (world == null) return;

        int height = 18 + (int)(Math.random() * 8); 

        // 1. Build the central trunk (Spruce Logs)
        for (int i = 0; i < height; i++) {
            world.setBlock(BlockPos.containing(x, y + i, z), Blocks.SPRUCE_LOG.defaultBlockState(), 3);
        }

        // 2. Add variable base roots (Spruce Wood)
        if (Math.random() < 0.6) world.setBlock(BlockPos.containing(x + 1, y, z), Blocks.SPRUCE_WOOD.defaultBlockState(), 3);
        if (Math.random() < 0.6) world.setBlock(BlockPos.containing(x - 1, y, z), Blocks.SPRUCE_WOOD.defaultBlockState(), 3);
        if (Math.random() < 0.6) world.setBlock(BlockPos.containing(x, y, z + 1), Blocks.SPRUCE_WOOD.defaultBlockState(), 3);
        if (Math.random() < 0.6) world.setBlock(BlockPos.containing(x, y, z - 1), Blocks.SPRUCE_WOOD.defaultBlockState(), 3);

        // 3. Generate Skeletal Branches (Spruce Fences instead of Leaves)
        int firstTier = 3 + (int)(Math.random() * 2); 
        for (int i = firstTier; i < height - 2; i++) { 
            boolean isWideTier = (i % 2 == 0); 
            
            int radius;
            if (isWideTier) {
                radius = (int) Math.ceil((double)(height - i) / 6.5); 
            } else {
                radius = 1; 
            }
            
            if (radius < 1) radius = 1;
            
            for (int dx = -radius; dx <= radius; dx++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    if (dx == 0 && dz == 0) continue; 
                    
                    if (Math.abs(dx) == radius && Math.abs(dz) == radius) {
                        if (radius > 1 && Math.random() < 0.8) continue; 
                        if (radius == 1 && Math.random() < 0.5) continue; 
                    }
                    
                    // Heavy decay sparseness (75% chance to skip)
                    if (Math.random() < 0.75) continue; 

                    BlockPos branchPos = BlockPos.containing(x + dx, y + i, z + dz);
                    if (world.isEmptyBlock(branchPos)) {
                        world.setBlock(branchPos, Blocks.SPRUCE_FENCE.defaultBlockState(), 3);
                    }
                }
            }
        }

        // 4. Generate a snapped, dead top spire
        for (int i = height - 2; i <= height + 1; i++) {
            if (Math.random() < 0.4) continue; // Randomly missing sections for a broken look
            BlockPos spirePos = BlockPos.containing(x, y + i, z);
            if (world.isEmptyBlock(spirePos)) {
                world.setBlock(spirePos, Blocks.SPRUCE_FENCE.defaultBlockState(), 3);
            }
        }
    }
}