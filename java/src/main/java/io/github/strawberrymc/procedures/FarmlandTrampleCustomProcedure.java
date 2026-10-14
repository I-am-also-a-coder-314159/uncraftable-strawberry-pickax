package io.github.strawberrymc.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;

public class FarmlandTrampleCustomProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        if (world == null) return;
        world.setBlock(BlockPos.containing(x, y, z), Blocks.DIRT.defaultBlockState(), 3);
    }
}
