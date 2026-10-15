package io.github.strawberrymc.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.resources.Identifier;
import net.minecraft.core.BlockPos;

public class CrystalHollowsGeneratorProcedure {
	public static boolean execute(LevelAccessor world, double x, double z) {
		double checkY = 0;
		checkY = -60;
		for (int _i1 = 0; _i1 < 13; _i1++) {
			if (world.getBiome(BlockPos.containing(x, checkY, z)).is(Identifier.parse("strawberrymc:crystal_hollows"))) {
				return true;
			}
			checkY = checkY + 8;
		}
		return false;
	}
}