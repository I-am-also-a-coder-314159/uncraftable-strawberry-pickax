package io.github.strawberrymc.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

public class RecoveryPearlFetchProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity immediatesourceentity) {
		if (immediatesourceentity == null)
			return;
		Player player = immediatesourceentity instanceof Player _player ? _player
				: immediatesourceentity instanceof Projectile _projectile && _projectile.getOwner() instanceof Player _player ? _player : null;
		if (player == null)
			return;
		Vec3 center = new Vec3(x, y, z);
		for (Mob mob : world.getEntitiesOfClass(Mob.class, new AABB(center, center).inflate(4), entity -> entity.distanceToSqr(center) <= 16)) {
			Vec3 destination = player.position().add(player.getViewVector(1).normalize());
			mob.teleportTo(destination.x, destination.y, destination.z);
		}
		for (ItemEntity item : world.getEntitiesOfClass(ItemEntity.class, new AABB(center, center).inflate(11), entity -> entity.distanceToSqr(center) <= 121)) {
			item.teleportTo(player.getX(), player.getY(), player.getZ());
		}
		if (world instanceof ServerLevel level)
			level.sendParticles(ParticleTypes.REVERSE_PORTAL, player.getX(), player.getY(), player.getZ(), 20, 3, 3, 3, 1);
	}
}