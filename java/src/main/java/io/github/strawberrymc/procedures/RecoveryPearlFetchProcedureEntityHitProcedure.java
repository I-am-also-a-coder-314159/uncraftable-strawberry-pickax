package io.github.strawberrymc.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

public class RecoveryPearlFetchProcedureEntityHitProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity immediatesourceentity) {
		RecoveryPearlFetchProcedureProcedure.execute(world, x, y, z, immediatesourceentity);
	}
}