package io.github.strawberrymc.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import io.github.strawberrymc.StrawberrymcMod;

public class FarmlandTrampleCustomProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		StrawberrymcMod.queueServerWork(2, () -> {
			world.setBlock(BlockPos.containing(x, y, z), Blocks.DIRT.defaultBlockState(), 3);
		});
	}
}