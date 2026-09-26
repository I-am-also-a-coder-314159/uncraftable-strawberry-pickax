package io.github.strawberrymc.procedures;

import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.item.ItemUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

@EventBusSubscriber
public class EntityShockProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingDamageEvent.Pre event) {
		if (event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double shockSlotDrop = 0;
		if ((entity instanceof LivingEntity _entUseItem0 ? _entUseItem0.getUseItem() : ItemStack.EMPTY)
				.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ResourceKey.create(Registries.ENCHANTMENT, Identifier.parse("strawberrymc:electricution")))) > 0) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.ANGRY_VILLAGER, (entity.getX()), (entity.getY()), (entity.getZ()), 2, 1, 1, 1, 1);
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.ENCHANTED_HIT, (entity.getX()), (entity.getY()), (entity.getZ()), 2, 1, 1, 1, 1);
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.GUST, (entity.getX()), (entity.getY()), (entity.getZ()), 2, 1, 1, 1, 1);
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == Items.CHAINMAIL_LEGGINGS
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == Items.GOLDEN_LEGGINGS
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == Items.IRON_LEGGINGS
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == Items.COPPER_LEGGINGS) {
				{
					Entity _ent = entity;
					if (_ent.level() instanceof ServerLevel _serverLevel) {
						_ent.hurtServer(_serverLevel, new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.parse("strawberrymc:shocking")))), 2);
					}
				}
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == Items.CHAINMAIL_BOOTS
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == Items.GOLDEN_BOOTS
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == Items.IRON_BOOTS
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == Items.COPPER_BOOTS) {
				{
					Entity _ent = entity;
					if (_ent.level() instanceof ServerLevel _serverLevel) {
						_ent.hurtServer(_serverLevel, new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.parse("strawberrymc:shocking")))), 1);
					}
				}
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == Items.CHAINMAIL_HELMET
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == Items.GOLDEN_HELMET
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == Items.IRON_HELMET
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == Items.COPPER_HELMET) {
				{
					Entity _ent = entity;
					if (_ent.level() instanceof ServerLevel _serverLevel) {
						_ent.hurtServer(_serverLevel, new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.parse("strawberrymc:shocking")))), 1);
					}
				}
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == Items.CHAINMAIL_CHESTPLATE
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == Items.GOLDEN_CHESTPLATE
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == Items.IRON_CHESTPLATE
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == Items.COPPER_CHESTPLATE) {
				{
					Entity _ent = entity;
					if (_ent.level() instanceof ServerLevel _serverLevel) {
						_ent.hurtServer(_serverLevel, new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.parse("strawberrymc:shocking")))), 3);
					}
				}
			}
			if (Mth.nextInt(RandomSource.create(), 1, 8) == 1) {
			}
			if (Mth.nextInt(RandomSource.create(), 1, 8) == 1) {
				shockSlotDrop = Mth.nextInt(RandomSource.create(), 100, 103);
				if (world instanceof ServerLevel _level) {
					ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, (getEntitySlot(entity, (int) shockSlotDrop)));
					entityToSpawn.setPickUpDelay(5);
					_level.addFreshEntity(entityToSpawn);
				}
				if (entity.getCapability(Capabilities.Item.ENTITY, null) instanceof ResourceHandler<ItemResource> _resourceHandler) {
					setStackInSlot(_resourceHandler, (int) shockSlotDrop, ItemResource.of(new ItemStack(Blocks.AIR)), 1);
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("item.shield.break")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("item.shield.break")), SoundSource.NEUTRAL, 1, 1, false);
					}
				}
			}
		}
	}

	private static ItemStack getEntitySlot(Entity entity, int slot) {
		if (entity != null) {
			ResourceHandler<ItemResource> resourceHandler = entity.getCapability(Capabilities.Item.ENTITY, null);
			if (resourceHandler != null) {
				return ItemUtil.getStack(resourceHandler, slot);
			}
		}
		return ItemStack.EMPTY;
	}

	private static void setStackInSlot(ResourceHandler<ItemResource> handler, int index, ItemResource resource, int amount) {
		try (var tx = Transaction.openRoot()) {
			if (!handler.getResource(index).isEmpty())
				handler.extract(index, handler.getResource(index), handler.getAmountAsInt(index), tx);
			if (!resource.isEmpty() && amount > 0)
				handler.insert(index, resource, amount, tx);
			tx.commit();
		}
	}
}