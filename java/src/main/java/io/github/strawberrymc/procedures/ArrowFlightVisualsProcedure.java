package io.github.strawberrymc.procedures;

import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.permissions.LevelBasedPermissionSet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import javax.annotation.Nullable;

import java.util.Comparator;

@EventBusSubscriber
public class ArrowFlightVisualsProcedure {
	@SubscribeEvent
	public static void onEntityTick(EntityTickEvent.Pre event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		Entity echo_target = null;
		if (entity instanceof Arrow) {
			if (!entity.getPersistentData().getBooleanOr("has_shooter_stamp", false) && !((findEntityInWorldRange(world, Player.class, x, y, z, 4)) == null)) {
				entity.getPersistentData().putString("shooter_name", ((findEntityInWorldRange(world, Player.class, x, y, z, 4)).getDisplayName().getString()));
				entity.getPersistentData().putBoolean("has_shooter_stamp", true);
			}
			if ((entity.getPersistentData().getStringOr("shaft", "")).equals("blaze") && !(entity.getDeltaMovement().x() == 0 && entity.getDeltaMovement().y() == 0 && entity.getDeltaMovement().z() == 0)) {
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.SMALL_FLAME, x, y, z, 1, 0.05, 0.05, 0.05, 0);
			}
			if ((entity.getPersistentData().getStringOr("shaft", "")).equals("breeze") && !(entity.getDeltaMovement().x() == 0 && entity.getDeltaMovement().y() == 0 && entity.getDeltaMovement().z() == 0)) {
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.SMALL_GUST, x, y, z, 1, 0.1, 0.1, 0.1, 1);
			}
			if ((entity.getPersistentData().getStringOr("fletching", "")).equals("phantom") && !(entity.getDeltaMovement().x() == 0 && entity.getDeltaMovement().y() == 0 && entity.getDeltaMovement().z() == 0)) {
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.ASH, x, y, z, 1, 0.1, 0.1, 0.1, 1);
			}
			if (entity.getDeltaMovement().x() == 0 && entity.getDeltaMovement().y() == 0 && entity.getDeltaMovement().z() == 0 && !entity.getPersistentData().getBooleanOr("ground_impact", false)) {
				entity.getPersistentData().putBoolean("ground_impact", true);
				if ((entity.getPersistentData().getStringOr("shaft", "")).equals("blaze")) {
					if ((entity.getPersistentData().getStringOr("tip", "")).equals("gold")) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(
									new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, LevelBasedPermissionSet.OWNER, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"fill ~-3 ~-3 ~-3 ~3 ~3 ~3 air replace water");
					} else {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(
									new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, LevelBasedPermissionSet.OWNER, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"fill ~-1 ~-2 ~-1 ~1 ~2 ~1 minecraft:fire replace minecraft:air");
					}
					{
						final Vec3 _center = new Vec3(x, y, z);
						for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(2 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
							entityiterator.igniteForSeconds(10);
						}
					}
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.LAVA, x, y, z, 5, 3, 3, 3, 1);
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.generic.explode")), SoundSource.NEUTRAL, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.generic.explode")), SoundSource.NEUTRAL, 1, 1, false);
						}
					}
				}
				if ((entity.getPersistentData().getStringOr("tip", "")).equals("sponge")) {
					if (!(entity.getPersistentData().getStringOr("shaft", "")).equals("aqua")) {
						if ((entity.getPersistentData().getStringOr("tip", "")).equals("gold")) {
							if (world instanceof ServerLevel _level)
								_level.getServer().getCommands().performPrefixedCommand(
										new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, LevelBasedPermissionSet.OWNER, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
										"fill ~-3 ~-3 ~-3 ~3 ~3 ~3 air replace water");
						} else {
							if (world instanceof ServerLevel _level)
								_level.getServer().getCommands().performPrefixedCommand(
										new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, LevelBasedPermissionSet.OWNER, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
										"fill ~-2 ~-2 ~-2 ~2 ~2 ~2 air replace water");
						}
					}
				}
				if ((entity.getPersistentData().getStringOr("tip", "")).equals("gunpowder")) {
					if (!(entity.getPersistentData().getStringOr("shaft", "")).equals("aqua")) {
						if (world instanceof Level _level && !_level.isClientSide())
							_level.explode(null, x, y, z, (float) 1.5, Level.ExplosionInteraction.MOB);
						if (world instanceof ServerLevel _level)
							_level.sendParticles(ParticleTypes.EXPLOSION_EMITTER, x, y, z, 1, 0.5, 0.5, 0.5, 0.5);
					}
				}
			}
			if ((entity.getPersistentData().getStringOr("fletching", "")).equals("phantom")) {
				if (!(entity.getDeltaMovement().x() == 0 && entity.getDeltaMovement().y() == 0 && entity.getDeltaMovement().z() == 0) && !entity.isInWater()) {
					entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x() * 1.013), (entity.getDeltaMovement().y() * 1.013), (entity.getDeltaMovement().z() * 1.013)));
				}
			}
			if ((entity.getPersistentData().getStringOr("tip", "")).equals("echo")) {
				echo_target = findEntityInWorldRange(world, LivingEntity.class, x, y, z, 12);
				if (!(echo_target == null) && !(entity.getDeltaMovement().x() == 0 && entity.getDeltaMovement().y() == 0 && entity.getDeltaMovement().z() == 0)) {
					if (!(entity.getPersistentData().getStringOr("shooter_name", "")).equals(echo_target.getDisplayName().getString())
							&& (new Vec3((entity.getX()), (entity.getY()), (entity.getZ()))).distanceTo((new Vec3((echo_target.getX()), (echo_target.getY()), (echo_target.getZ())))) > 4) {
						entity.setDeltaMovement(new Vec3(
								(entity.getDeltaMovement().x()
										+ (echo_target.getX() - entity.getX()) / ((new Vec3((entity.getX()), (entity.getY()), (entity.getZ()))).distanceTo((new Vec3((echo_target.getX()), (echo_target.getY()), (echo_target.getZ())))) * 0.13)),
								(entity.getDeltaMovement().y()
										+ (echo_target.getY() - entity.getY()) / ((new Vec3((entity.getX()), (entity.getY()), (entity.getZ()))).distanceTo((new Vec3((echo_target.getX()), (echo_target.getY()), (echo_target.getZ())))) * 0.13) + 1),
								(entity.getDeltaMovement().z()
										+ (echo_target.getZ() - entity.getZ()) / ((new Vec3((entity.getX()), (entity.getY()), (entity.getZ()))).distanceTo((new Vec3((echo_target.getX()), (echo_target.getY()), (echo_target.getZ())))) * 0.13))));
						if (world instanceof ServerLevel _level)
							_level.sendParticles(ParticleTypes.SCULK_SOUL, (echo_target.getX()), (echo_target.getY() + 1), (echo_target.getZ()), 1, 0.5, 0.5, 0.5, 0.3);
					}
				}
			}
		}
	}

	private static Entity findEntityInWorldRange(LevelAccessor world, Class<? extends Entity> clazz, double x, double y, double z, double range) {
		return (Entity) world.getEntitiesOfClass(clazz, AABB.ofSize(new Vec3(x, y, z), range, range, range), e -> true).stream().sorted(Comparator.comparingDouble(e -> e.distanceToSqr(x, y, z))).findFirst().orElse(null);
	}
}