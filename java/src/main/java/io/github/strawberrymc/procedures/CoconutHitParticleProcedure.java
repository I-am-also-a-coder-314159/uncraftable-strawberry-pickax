package io.github.strawberrymc.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import io.github.strawberrymc.init.StrawberrymcModParticleTypes;

public class CoconutHitParticleProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (StrawberrymcModParticleTypes.COCONUT_FRAGMENT.get()), x, y, z, Mth.nextInt(RandomSource.create(), 1, 5), 1, 1, 1, 2);
	}
}