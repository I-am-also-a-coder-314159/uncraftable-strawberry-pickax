package io.github.strawberrymc.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.SoundType;

import io.github.strawberrymc.init.StrawberrymcModWoodTypes;
import io.github.strawberrymc.init.StrawberrymcModBlocks;

public class PalmWallHangingSignBlock extends WallHangingSignBlock {
	public PalmWallHangingSignBlock(BlockBehaviour.Properties properties) {
		super(StrawberrymcModWoodTypes.PALM_HANGING_SIGN_WOOD_TYPE, properties.sound(SoundType.HANGING_SIGN).strength(1f).noCollision().ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn()
				.overrideLootTable(StrawberrymcModBlocks.PALM_HANGING_SIGN.get().getLootTable()).overrideDescription(StrawberrymcModBlocks.PALM_HANGING_SIGN.get().getDescriptionId()));
	}
}