package io.github.strawberrymc.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import io.github.strawberrymc.init.StrawberrymcModParticleTypes;

public class CoconutImpactParticleHandlerProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (StrawberrymcModParticleTypes.COCONUT_IMPACT.get()), x, y, z, 5, 1, 1, 1, 1);
	}
}