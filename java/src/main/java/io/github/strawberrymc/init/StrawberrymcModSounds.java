/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package io.github.strawberrymc.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import io.github.strawberrymc.StrawberrymcMod;

public class StrawberrymcModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, StrawberrymcMod.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> VOID_AMB_01 = REGISTRY.register("void_amb_01", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("strawberrymc", "void_amb_01")));
	public static final DeferredHolder<SoundEvent, SoundEvent> VOID_AMB_02 = REGISTRY.register("void_amb_02", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("strawberrymc", "void_amb_02")));
}