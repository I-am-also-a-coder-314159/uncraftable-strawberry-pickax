package io.github.strawberrymc.fluid;

import org.apache.logging.log4j.core.util.Source;

import net.neoforged.neoforge.fluids.BaseFlowingFluid;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.ParticleOptions;

import io.github.strawberrymc.init.StrawberrymcModFluids;
import io.github.strawberrymc.init.StrawberrymcModFluidTypes;
import io.github.strawberrymc.init.StrawberrymcModBlocks;

public abstract class VoidTearsFluid extends BaseFlowingFluid {
	public static final BaseFlowingFluid.Properties PROPERTIES = new BaseFlowingFluid.Properties(() -> StrawberrymcModFluidTypes.VOID_TEARS_TYPE.get(), () -> StrawberrymcModFluids.VOID_TEARS.get(),
			() -> StrawberrymcModFluids.FLOWING_VOID_TEARS.get()).explosionResistance(100f).tickRate(4).block(() -> (LiquidBlock) StrawberrymcModBlocks.VOID_TEARS.get());

	private VoidTearsFluid() {
		super(PROPERTIES);
	}

	@Override
	public ParticleOptions getDripParticle() {
		return ParticleTypes.SQUID_INK;
	}

	public static class Source extends VoidTearsFluid {
		public int getAmount(FluidState state) {
			return 8;
		}

		public boolean isSource(FluidState state) {
			return true;
		}
	}

	public static class Flowing extends VoidTearsFluid {
		protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
			super.createFluidStateDefinition(builder);
			builder.add(LEVEL);
		}

		public int getAmount(FluidState state) {
			return state.getValue(LEVEL);
		}

		public boolean isSource(FluidState state) {
			return false;
		}
	}
}