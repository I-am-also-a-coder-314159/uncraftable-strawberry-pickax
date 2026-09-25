/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package io.github.strawberrymc.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import io.github.strawberrymc.entity.*;
import io.github.strawberrymc.StrawberrymcMod;

public class StrawberrymcModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, StrawberrymcMod.MODID);
	public static final DeferredHolder<EntityType<?>, EntityType<EchowoodBoatEntity>> ECHOWOOD_BOAT = register("echowood_boat",
			EntityType.Builder.<EchowoodBoatEntity>of(EchowoodBoatEntity::new, MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<EchowoodChestBoatEntity>> ECHOWOOD_CHEST_BOAT = register("echowood_chest_boat",
			EntityType.Builder.<EchowoodChestBoatEntity>of(EchowoodChestBoatEntity::new, MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<PalmBoatEntity>> PALM_BOAT = register("palm_boat",
			EntityType.Builder.<PalmBoatEntity>of(PalmBoatEntity::new, MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<PalmChestBoatEntity>> PALM_CHEST_BOAT = register("palm_chest_boat",
			EntityType.Builder.<PalmChestBoatEntity>of(PalmChestBoatEntity::new, MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<RecoveryPearlProjectileEntity>> RECOVERY_PEARL_PROJECTILE = register("recovery_pearl_projectile",
			EntityType.Builder.<RecoveryPearlProjectileEntity>of(RecoveryPearlProjectileEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<CoconutProjectileEntity>> COCONUT_PROJECTILE = register("coconut_projectile",
			EntityType.Builder.<CoconutProjectileEntity>of(CoconutProjectileEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(StrawberrymcMod.MODID, registryname))));
	}
}