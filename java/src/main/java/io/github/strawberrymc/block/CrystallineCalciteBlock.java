package io.github.strawberrymc.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class CrystallineCalciteBlock extends Block {
	public CrystallineCalciteBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.AMETHYST).strength(1f, 10f));
	}

	@Override
	public int getLightDampening(BlockState state) {
		return 10;
	}
}