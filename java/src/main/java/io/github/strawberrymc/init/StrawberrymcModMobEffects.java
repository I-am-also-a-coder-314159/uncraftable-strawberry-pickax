/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package io.github.strawberrymc.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.registries.Registries;

import io.github.strawberrymc.potion.InvulnerabilityMobEffect;
import io.github.strawberrymc.StrawberrymcMod;

public class StrawberrymcModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, StrawberrymcMod.MODID);
	public static final DeferredHolder<MobEffect, MobEffect> INVULNERABILITY = REGISTRY.register("invulnerability", InvulnerabilityMobEffect::new);
}