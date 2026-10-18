package io.github.strawberrymc.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.BlockPos;
import io.github.strawberrymc.init.StrawberrymcModBlocks;

// Import the tree generator
import io.github.strawberrymc.procedures.SpawnEchowoodTreeProcedure;

public class EchowoodGrowProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        if (world.getRandom().nextFloat() > 0.05f) {
            return;
        }

        Block sapling = StrawberrymcModBlocks.ECHOWOOD_SAPLING.get();
        int ix = (int) x;
        int iy = (int) y;
        int iz = (int) z;

        int baseX = 0;
        int baseZ = 0;
        boolean is2x2 = false;

        if (checkSapling(world, ix, iy, iz, sapling) && checkSapling(world, ix + 1, iy, iz, sapling) && checkSapling(world, ix, iy, iz + 1, sapling) && checkSapling(world, ix + 1, iy, iz + 1, sapling)) {
            baseX = ix; baseZ = iz; is2x2 = true;
        } 
        else if (checkSapling(world, ix - 1, iy, iz, sapling) && checkSapling(world, ix, iy, iz, sapling) && checkSapling(world, ix - 1, iy, iz + 1, sapling) && checkSapling(world, ix, iy, iz + 1, sapling)) {
            baseX = ix - 1; baseZ = iz; is2x2 = true;
        } 
        else if (checkSapling(world, ix, iy, iz - 1, sapling) && checkSapling(world, ix + 1, iy, iz - 1, sapling) && checkSapling(world, ix, iy, iz, sapling) && checkSapling(world, ix + 1, iy, iz, sapling)) {
            baseX = ix; baseZ = iz - 1; is2x2 = true;
        } 
        else if (checkSapling(world, ix - 1, iy, iz - 1, sapling) && checkSapling(world, ix, iy, iz - 1, sapling) && checkSapling(world, ix - 1, iy, iz, sapling) && checkSapling(world, ix, iy, iz, sapling)) {
            baseX = ix - 1; baseZ = iz - 1; is2x2 = true;
        }

        if (is2x2) {
            world.setBlock(BlockPos.containing(baseX, iy, baseZ), Blocks.AIR.defaultBlockState(), 3);
            world.setBlock(BlockPos.containing(baseX + 1, iy, baseZ), Blocks.AIR.defaultBlockState(), 3);
            world.setBlock(BlockPos.containing(baseX, iy, baseZ + 1), Blocks.AIR.defaultBlockState(), 3);
            world.setBlock(BlockPos.containing(baseX + 1, iy, baseZ + 1), Blocks.AIR.defaultBlockState(), 3);

            SpawnEchowoodTreeProcedure.execute(world, baseX, iy, baseZ);
        }
    }

    private static boolean checkSapling(LevelAccessor world, int checkX, int checkY, int checkZ, Block saplingBlock) {
        return world.getBlockState(BlockPos.containing(checkX, checkY, checkZ)).getBlock() == saplingBlock;
    }
}