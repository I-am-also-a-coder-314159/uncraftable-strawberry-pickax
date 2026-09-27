package io.github.strawberrymc.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import io.github.strawberrymc.init.StrawberrymcModMenus;
import io.github.strawberrymc.init.StrawberrymcModItems;

public class CalcGemcutterOutProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		String GemcutterValidInputs = "";
		GemcutterValidInputs = "raw_diamond \\ green_beryl \\ garnet_nodule \\  lazurite \\";
		if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu0 ? _menu0.getSlots().get(1).getItem() : ItemStack.EMPTY).getItem() == StrawberrymcModItems.COCONUT_OIL.get()
				&& (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu2 ? _menu2.getSlots().get(2).getItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem()) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("item.axe.wax_off")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("item.axe.wax_off")), SoundSource.NEUTRAL, 1, 1, false);
				}
			}
			if (GemcutterValidInputs.contains((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu5 ? _menu5.getSlots().get(0).getItem() : ItemStack.EMPTY) + "null")) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("ui.stonecutter.take_result")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("ui.stonecutter.take_result")), SoundSource.NEUTRAL, 1, 1, false);
					}
				}
				if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu7 ? _menu7.getSlots().get(0).getItem() : ItemStack.EMPTY).getItem() == StrawberrymcModItems.GREEN_BERYL
						.get()) {
					if (entity instanceof Player _player && _player.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu) {
						ItemStack _setstack9 = new ItemStack(Items.EMERALD).copy();
						_setstack9.setCount(1);
						_menu.getSlots().get(0).set(_setstack9);
						_player.containerMenu.broadcastChanges();
					}
				} else {
					if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu10 ? _menu10.getSlots().get(0).getItem() : ItemStack.EMPTY).getItem() == StrawberrymcModItems.RAW_DIAMOND
							.get()) {
						if (entity instanceof Player _player && _player.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu) {
							ItemStack _setstack12 = new ItemStack(Items.DIAMOND).copy();
							_setstack12.setCount(1);
							_menu.getSlots().get(0).set(_setstack12);
							_player.containerMenu.broadcastChanges();
						}
					} else {
						if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu13 ? _menu13.getSlots().get(0).getItem() : ItemStack.EMPTY).getItem() == StrawberrymcModItems.LAZURITE
								.get()) {
							if (entity instanceof Player _player && _player.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu) {
								ItemStack _setstack15 = new ItemStack(Items.LAPIS_LAZULI).copy();
								_setstack15.setCount(1);
								_menu.getSlots().get(0).set(_setstack15);
								_player.containerMenu.broadcastChanges();
							}
						}
					}
				}
				if (entity instanceof Player _player && _player.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu) {
					_menu.getSlots().get(0).remove(1);
					_menu.getSlots().get(1).remove(1);
					_player.containerMenu.broadcastChanges();
				}
			}
		}
	}
}