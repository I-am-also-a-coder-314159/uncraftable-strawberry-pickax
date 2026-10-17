package io.github.strawberrymc.block;

import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

import io.github.strawberrymc.procedures.VoidTearsDamageApplyProcedure;
import io.github.strawberrymc.procedures.VoidTearHissProcedure;
import io.github.strawberrymc.init.StrawberrymcModFluids;

public class VoidTearsBlock extends LiquidBlock {
	public VoidTearsBlock(BlockBehaviour.Properties properties) {
		super(StrawberrymcModFluids.VOID_TEARS.get(), properties.mapColor(MapColor.COLOR_BLACK).strength(100f).noCollision().noLootTable().liquid().pushReaction(PushReaction.DESTROY).sound(SoundType.EMPTY).replaceable());
	}

	@Override
	public int getLightDampening(BlockState state) {
		return 6;
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, orientation, moving);
		VoidTearHissProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier insideBlockEffectApplier, boolean isPrecise) {
		super.entityInside(blockstate, world, pos, entity, insideBlockEffectApplier, isPrecise);
		VoidTearsDamageApplyProcedure.execute(world, entity);
	}
}