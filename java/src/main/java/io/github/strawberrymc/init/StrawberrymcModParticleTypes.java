/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package io.github.strawberrymc.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import io.github.strawberrymc.StrawberrymcMod;

public class StrawberrymcModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(Registries.PARTICLE_TYPE, StrawberrymcMod.MODID);
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DUSTCLOUD = REGISTRY.register("dustcloud", () -> new SimpleParticleType(true));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> COCONUT_IMPACT = REGISTRY.register("coconut_impact", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> INVULNERABILITY_PARTICLE = REGISTRY.register("invulnerability_particle", () -> new SimpleParticleType(false));
}