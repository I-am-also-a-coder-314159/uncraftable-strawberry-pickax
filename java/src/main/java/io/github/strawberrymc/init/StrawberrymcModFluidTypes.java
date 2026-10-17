/*
 * MCreator note: This file will be REGENERATED on each build.
 */
package io.github.strawberrymc.init;

import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.fluids.FluidType;

import io.github.strawberrymc.fluid.types.VoidTearsFluidType;
import io.github.strawberrymc.StrawberrymcMod;

public class StrawberrymcModFluidTypes {
	public static final DeferredRegister<FluidType> REGISTRY = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, StrawberrymcMod.MODID);
	public static final DeferredHolder<FluidType, FluidType> VOID_TEARS_TYPE = REGISTRY.register("void_tears", VoidTearsFluidType::new);
}