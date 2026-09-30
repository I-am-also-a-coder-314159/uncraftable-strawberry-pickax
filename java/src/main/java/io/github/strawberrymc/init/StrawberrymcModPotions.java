/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package io.github.strawberrymc.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.core.registries.Registries;

import io.github.strawberrymc.StrawberrymcMod;

public class StrawberrymcModPotions {
	public static final DeferredRegister<Potion> REGISTRY = DeferredRegister.create(Registries.POTION, StrawberrymcMod.MODID);
	public static final DeferredHolder<Potion, Potion> HERB = REGISTRY.register("herb", () -> new Potion("herb"));
}