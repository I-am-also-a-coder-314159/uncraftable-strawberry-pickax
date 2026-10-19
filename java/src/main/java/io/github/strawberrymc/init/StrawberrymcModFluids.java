/*
 * MCreator note: This file will be REGENERATED on each build.
 */
package io.github.strawberrymc.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.core.registries.BuiltInRegistries;

import io.github.strawberrymc.fluid.VoidTearsFluid;
import io.github.strawberrymc.StrawberrymcMod;

public class StrawberrymcModFluids {
	public static final DeferredRegister<Fluid> REGISTRY = DeferredRegister.create(BuiltInRegistries.FLUID, StrawberrymcMod.MODID);
	public static final DeferredHolder<Fluid, FlowingFluid> VOID_TEARS = REGISTRY.register("void_tears", VoidTearsFluid.Source::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_VOID_TEARS = REGISTRY.register("flowing_void_tears", VoidTearsFluid.Flowing::new);
}