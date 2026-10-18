package io.github.strawberrymc.block;

import net.neoforged.neoforge.common.util.DeferredSoundType;

import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import io.github.strawberrymc.procedures.PalmGrowProcedure;

public class PalmSaplingBlock extends FlowerBlock implements BonemealableBlock {
	public PalmSaplingBlock(BlockBehaviour.Properties properties) {
		super(MobEffects.HASTE, 100,
				properties.mapColor(MapColor.PLANT)
						.sound(new DeferredSoundType(1.0f, 1.0f, () -> BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("strawberrymc:palm_break")), () -> BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("strawberrymc:palm_step")),
								() -> BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("strawberrymc:palm_step")), () -> BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("strawberrymc:palm_break")),
								() -> BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("strawberrymc:palm_step"))))
						.instabreak().noCollision().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));
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
		PalmGrowProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}
}