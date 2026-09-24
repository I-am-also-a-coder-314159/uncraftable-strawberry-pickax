package io.github.strawberrymc.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Final;

import net.minecraft.world.level.biome.MultiNoiseBiomeSourceParameterList;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;

import java.util.function.Function;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

import io.github.strawberrymc.init.StrawberrymcModBiomes;

@Mixin(MultiNoiseBiomeSourceParameterList.Preset.class)
public class BiomeSourcePresetMixin {
	@Mutable
	@Shadow
	@Final
	private Object provider;

	@Inject(method = "<init>", at = @At("RETURN"))
	private void daisyChainProvider(Identifier idArg, Object ignored, CallbackInfo ci) {
		if (idArg.equals(StrawberrymcModBiomes.OVERWORLD_BIOMESOURCE_PRESET_ID) || idArg.equals(StrawberrymcModBiomes.NETHER_BIOMESOURCE_PRESET_ID)) {
			try {
				Field providerField = MultiNoiseBiomeSourceParameterList.Preset.class.getDeclaredField("provider");
				providerField.setAccessible(true);
				Object existingProvider = providerField.get(this);
				Class<?> providerType = providerField.getType();
				this.provider = Proxy.newProxyInstance(providerType.getClassLoader(), new Class<?>[] { providerType },
					(proxy, method, args) -> {
						if (method.getName().equals("apply")) {
							@SuppressWarnings("unchecked")
							Function<ResourceKey<Biome>, Object> lookup = (Function<ResourceKey<Biome>, Object>) args[0];
							@SuppressWarnings("unchecked")
							Climate.ParameterList<Object> originalList = (Climate.ParameterList<Object>) method.invoke(existingProvider, lookup);
							return StrawberrymcModBiomes.adaptPresetParameterList(idArg, originalList, lookup);
						}
						return method.invoke(existingProvider, args);
					});
			} catch (ReflectiveOperationException exception) {
				throw new IllegalStateException("Unable to adapt biome source preset provider", exception);
			}
		}
	}
}