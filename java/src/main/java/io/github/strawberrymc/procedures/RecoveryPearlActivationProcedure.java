package io.github.strawberrymc.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

public class RecoveryPearlActivationProcedure {
	public static void execute(Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		{
			Entity _ent = entity;
			double _tx = (sourceentity.getX());
			double _ty = (sourceentity.getY());
			double _tz = (sourceentity.getZ());
			_ent.teleportTo(_tx, _ty, _tz);
			if (_ent instanceof ServerPlayer _serverPlayer)
				_serverPlayer.connection.teleport(_tx, _ty, _tz, _ent.getYRot(), _ent.getXRot());
		}
	}
}