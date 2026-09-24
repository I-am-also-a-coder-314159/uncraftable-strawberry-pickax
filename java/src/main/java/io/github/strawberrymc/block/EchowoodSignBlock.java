package io.github.strawberrymc.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.SoundType;

import io.github.strawberrymc.init.StrawberrymcModWoodTypes;

public class EchowoodSignBlock extends StandingSignBlock {
	public EchowoodSignBlock(BlockBehaviour.Properties properties) {
		super(StrawberrymcModWoodTypes.ECHOWOOD_SIGN_WOOD_TYPE, properties.sound(SoundType.WOOD).strength(1f).noCollision().ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn());
	}
}