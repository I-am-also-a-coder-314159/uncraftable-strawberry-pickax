package io.github.strawberrymc.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.component.DataComponents;

import io.github.strawberrymc.init.StrawberrymcModMenus;

public class CalcFletchingProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		String var_tip = "";
		String var_shaft = "";
		String var_fletching = "";
		ItemStack output_arrow = ItemStack.EMPTY;
		var_tip = "";
		var_shaft = "";
		var_fletching = "";
		output_arrow = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu0 ? _menu0.getSlots().get(4).getItem() : ItemStack.EMPTY).copy();
		if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu1 ? _menu1.getSlots().get(4).getItem() : ItemStack.EMPTY).getItem() == Items.ARROW
				&& getAmountInGUISlot(entity, 4) >= 5) {
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu4 ? _menu4.getSlots().get(4).getItem() : ItemStack.EMPTY).getItem() == Items.PRISMARINE_SHARD
					&& getAmountInGUISlot(entity, 4) >= 5) {
				var_tip = "prismarine";
			}
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu7 ? _menu7.getSlots().get(4).getItem() : ItemStack.EMPTY).getItem() == Items.ECHO_SHARD
					&& getAmountInGUISlot(entity, 4) >= 5) {
				var_tip = "echo";
			}
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu10 ? _menu10.getSlots().get(4).getItem() : ItemStack.EMPTY).getItem() == Items.AMETHYST_SHARD
					&& getAmountInGUISlot(entity, 4) >= 5) {
				var_tip = "amethyst";
			}
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu13 ? _menu13.getSlots().get(4).getItem() : ItemStack.EMPTY).getItem() == Items.BLAZE_ROD
					&& getAmountInGUISlot(entity, 4) >= 5) {
				var_shaft = "blaze";
			}
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu16 ? _menu16.getSlots().get(4).getItem() : ItemStack.EMPTY).getItem() == Items.BREEZE_ROD
					&& getAmountInGUISlot(entity, 4) >= 5) {
				var_shaft = "breeze";
			}
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu19 ? _menu19.getSlots().get(4).getItem() : ItemStack.EMPTY).getItem() == Items.PHANTOM_MEMBRANE
					&& getAmountInGUISlot(entity, 4) >= 5) {
				var_fletching = "phantom";
			}
			if (!(var_tip).equals("") || !(var_shaft).equals("") || !(var_fletching).equals("")) {
				{
					final String _tagName = "tip";
					final String _tagValue = var_tip;
					CustomData.update(DataComponents.CUSTOM_DATA, output_arrow, tag -> tag.putString(_tagName, _tagValue));
				}
				{
					final String _tagName = "shaft";
					final String _tagValue = var_shaft;
					CustomData.update(DataComponents.CUSTOM_DATA, output_arrow, tag -> tag.putString(_tagName, _tagValue));
				}
				{
					final String _tagName = "fletching";
					final String _tagValue = var_fletching;
					CustomData.update(DataComponents.CUSTOM_DATA, output_arrow, tag -> tag.putString(_tagName, _tagValue));
				}
				if (entity instanceof Player _player && _player.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu) {
					ItemStack _setstack25 = output_arrow.copy();
					_setstack25.setCount(5);
					_menu.getSlots().get(3).set(_setstack25);
					_player.containerMenu.broadcastChanges();
				}
			} else {
				if (entity instanceof Player _player && _player.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu) {
					ItemStack _setstack26 = new ItemStack(Blocks.AIR).copy();
					_setstack26.setCount(1);
					_menu.getSlots().get(3).set(_setstack26);
					_player.containerMenu.broadcastChanges();
				}
			}
		}
	}

	private static int getAmountInGUISlot(Entity entity, int sltid) {
		if (entity instanceof Player player && player.containerMenu instanceof StrawberrymcModMenus.MenuAccessor menuAccessor) {
			ItemStack stack = menuAccessor.getSlots().get(sltid).getItem();
			if (stack != null)
				return stack.getCount();
		}
		return 0;
	}
}