package io.github.strawberrymc.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.SoundType;

import io.github.strawberrymc.init.StrawberrymcModWoodTypes;
import io.github.strawberrymc.init.StrawberrymcModBlocks;

public class JuniperWallSignBlock extends WallSignBlock {
	public JuniperWallSignBlock(BlockBehaviour.Properties properties) {
		super(StrawberrymcModWoodTypes.JUNIPER_SIGN_WOOD_TYPE, properties.sound(SoundType.WOOD).strength(1f).noCollision().ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn()
				.overrideLootTable(StrawberrymcModBlocks.JUNIPER_SIGN.get().getLootTable()).overrideDescription(StrawberrymcModBlocks.JUNIPER_SIGN.get().getDescriptionId()));
	}
}