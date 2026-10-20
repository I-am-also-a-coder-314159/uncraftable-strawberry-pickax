package io.github.strawberrymc.procedures;

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
		output_arrow = new ItemStack(Items.ARROW).copy();
		if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu0 ? _menu0.getSlots().get(4).getItem() : ItemStack.EMPTY).getItem() == Items.ARROW) {
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu2 ? _menu2.getSlots().get(0).getItem() : ItemStack.EMPTY).getItem() == Items.PRISMARINE_SHARD) {
				var_tip = "prismarine";
			}
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu4 ? _menu4.getSlots().get(0).getItem() : ItemStack.EMPTY).getItem() == Items.ECHO_SHARD) {
				var_tip = "echo";
			}
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu6 ? _menu6.getSlots().get(0).getItem() : ItemStack.EMPTY).getItem() == Items.AMETHYST_SHARD) {
				var_tip = "am";
			}
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu8 ? _menu8.getSlots().get(1).getItem() : ItemStack.EMPTY).getItem() == Items.BLAZE_ROD) {
				var_tip = "blaze";
			}
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu10 ? _menu10.getSlots().get(1).getItem() : ItemStack.EMPTY).getItem() == Items.BREEZE_ROD) {
				var_tip = "breeze";
			}
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu12 ? _menu12.getSlots().get(2).getItem() : ItemStack.EMPTY).getItem() == Items.PHANTOM_MEMBRANE) {
				var_tip = "phantom";
			}
			if (!(var_tip).equals("") && !(var_shaft).equals("") && !(var_fletching).equals("")) {
				if (entity instanceof Player _player && _player.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu) {
					ItemStack _setstack14 = output_arrow.copy();
					_setstack14.setCount(1);
					_menu.getSlots().get(3).set(_setstack14);
					_player.containerMenu.broadcastChanges();
				}
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
			} else {
				if (entity instanceof Player _player && _player.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu) {
					ItemStack _setstack18 = new ItemStack(Items.ARROW).copy();
					_setstack18.setCount(1);
					_menu.getSlots().get(3).set(_setstack18);
					_player.containerMenu.broadcastChanges();
				}
			}
		}
	}
}