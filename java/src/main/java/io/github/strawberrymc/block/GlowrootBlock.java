package io.github.strawberrymc.block;

import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.core.BlockPos;

import io.github.strawberrymc.init.StrawberrymcModBlocks;

public class GlowrootBlock extends FlowerBlock {
	public GlowrootBlock(BlockBehaviour.Properties properties) {
		super(MobEffects.SPEED, 100, properties.mapColor(MapColor.PLANT).sound(SoundType.GRASS).strength(0f, 1f).postProcess((bs, br, bp) -> bp).emissiveRendering((bs, br, bp) -> true).lightLevel(state -> 15).noCollision()
				.offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state, boolean includeData, Player entity) {
		return new ItemStack(StrawberrymcModBlocks.GLOWROOT_TUBER_STONE.get());
	}

	@Override
	public boolean mayPlaceOn(BlockState groundState, BlockGetter worldIn, BlockPos pos) {
		return groundState.is(StrawberrymcModBlocks.GLOWROOT_TUBER_STONE.get());
	}

	@Override
	public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.below();
		BlockState groundState = worldIn.getBlockState(blockpos);
		return this.mayPlaceOn(groundState, worldIn, blockpos);
	}
}