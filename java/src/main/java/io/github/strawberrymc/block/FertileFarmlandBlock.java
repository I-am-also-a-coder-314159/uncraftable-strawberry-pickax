package io.github.strawberrymc.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import io.github.strawberrymc.procedures.TrampleFertileFarmlandProcedure;
import io.github.strawberrymc.procedures.SulfurParticulateProcedure;

public class FertileFarmlandBlock extends FarmlandBlock {
	private static final VoxelShape SHAPE = box(0, 0, 0, 16, 15, 16);
	public static final IntegerProperty USES = IntegerProperty.create("uses", 0, 4);


	public FertileFarmlandBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.DIRT).sound(SoundType.GRAVEL).strength(0.6f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(USES, 0));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(USES);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public void attack(BlockState blockstate, Level world, BlockPos pos, Player entity) {
		super.attack(blockstate, world, pos, entity);
		SulfurParticulateProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}
}
