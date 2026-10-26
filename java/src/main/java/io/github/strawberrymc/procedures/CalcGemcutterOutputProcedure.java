package io.github.strawberrymc.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import io.github.strawberrymc.init.StrawberrymcModItems;

public class CalcGemcutterOutputProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (ItemStack.EMPTY.getItem() == StrawberrymcModItems.COCONUT_OIL.get()) {
			if (ItemStack.EMPTY.getItem() == StrawberrymcModItems.GREEN_BERYL.get()) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("ui.stonecutter.take_result")), SoundSource.BLOCKS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("ui.stonecutter.take_result")), SoundSource.BLOCKS, 1, 1, false);
					}
				}
			}
		}
	}
}