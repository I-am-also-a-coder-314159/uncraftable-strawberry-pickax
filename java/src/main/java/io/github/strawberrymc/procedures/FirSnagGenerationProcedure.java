package io.github.strawberrymc.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class FirSnagGenerationProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        if (world == null) return;

        // Keep the exact same trunk height: 18-25 blocks
        int height = 18 + (int)(Math.random() * 8); 

        // 1. Build the bare central trunk with a jagged, broken top
        for (int i = 0; i < height; i++) {
            // Randomly skip blocks near the very top to simulate a snapped trunk
            if (i > height - 4 && Math.random() < 0.3) continue; 
            
            world.setBlock(BlockPos.containing(x, y + i, z), Blocks.SPRUCE_LOG.defaultBlockState(), 3);
        }

        // 2. Add the exact same variable base roots (Spruce Wood)
        if (Math.random() < 0.6) world.setBlock(BlockPos.containing(x + 1, y, z), Blocks.SPRUCE_WOOD.defaultBlockState(), 3);
        if (Math.random() < 0.6) world.setBlock(BlockPos.containing(x - 1, y, z), Blocks.SPRUCE_WOOD.defaultBlockState(), 3);
        if (Math.random() < 0.6) world.setBlock(BlockPos.containing(x, y, z + 1), Blocks.SPRUCE_WOOD.defaultBlockState(), 3);
        if (Math.random() < 0.6) world.setBlock(BlockPos.containing(x, y, z - 1), Blocks.SPRUCE_WOOD.defaultBlockState(), 3);
    }
}