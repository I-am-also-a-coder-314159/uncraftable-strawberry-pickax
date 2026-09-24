package io.github.strawberrymc.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.CeilingHangingSignBlock;

import io.github.strawberrymc.init.StrawberrymcModWoodTypes;

public class PalmHangingSignBlock extends CeilingHangingSignBlock {
	public PalmHangingSignBlock(BlockBehaviour.Properties properties) {
		super(StrawberrymcModWoodTypes.PALM_HANGING_SIGN_WOOD_TYPE, properties.sound(SoundType.HANGING_SIGN).strength(1f).noCollision().ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn());
	}
}