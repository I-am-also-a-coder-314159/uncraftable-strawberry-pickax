package io.github.strawberrymc.mixin;

import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.core.Holder;

import io.github.strawberrymc.init.StrawberrymcModBiomes;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;

@Mixin(NoiseGeneratorSettings.class)
public class NoiseGeneratorSettingsMixin implements StrawberrymcModBiomes.StrawberrymcModNoiseGeneratorSettings {
	@Unique
	private Holder<DimensionType> strawberrymc_dimensionTypeReference;

	@WrapMethod(method = "surfaceRule")
	public SurfaceRules.RuleSource surfaceRule(Operation<SurfaceRules.RuleSource> original) {
		SurfaceRules.RuleSource retval = original.call();
		if (this.strawberrymc_dimensionTypeReference != null) {
			retval = StrawberrymcModBiomes.adaptSurfaceRule(retval, this.strawberrymc_dimensionTypeReference);
		}
		return retval;
	}

	@Override
	public void setstrawberrymcDimensionTypeReference(Holder<DimensionType> dimensionType) {
		this.strawberrymc_dimensionTypeReference = dimensionType;
	}
}