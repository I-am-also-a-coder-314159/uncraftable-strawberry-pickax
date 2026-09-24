package io.github.strawberrymc.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class ChorineOreBlock extends Block {
	public ChorineOreBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(0.6f, 0.8278377969f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM));
	}
}