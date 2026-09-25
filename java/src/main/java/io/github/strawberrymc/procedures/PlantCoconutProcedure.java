package io.github.strawberrymc.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import io.github.strawberrymc.init.StrawberrymcModBlocks;

public class PlantCoconutProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		world.setBlock(BlockPos.containing(x, y + 1, z), StrawberrymcModBlocks.PALM_SEEDLING.get().defaultBlockState(), 3);
	}
}