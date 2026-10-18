package io.github.strawberrymc.block;

import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import io.github.strawberrymc.procedures.EchowoodGrowProcedure;
import io.github.strawberrymc.init.StrawberrymcModMobEffects;

public class EchowoodSaplingBlock extends FlowerBlock implements BonemealableBlock {
	public EchowoodSaplingBlock(BlockBehaviour.Properties properties) {
		super(StrawberrymcModMobEffects.INVULNERABILITY, 100, properties.mapColor(MapColor.PLANT).instabreak().noCollision().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 100;
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 60;
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader worldIn, BlockPos pos, BlockState blockstate) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState blockstate) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState blockstate) {
		EchowoodGrowProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, net.minecraft.world.level.BlockGetter worldIn, BlockPos pos) {
    return state.getBlock() == net.minecraft.world.level.block.Blocks.END_STONE || super.mayPlaceOn(state, worldIn, pos);
	}
}