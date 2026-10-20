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
		if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu1 ? _menu1.getSlots().get(4).getItem() : ItemStack.EMPTY).getItem() == Items.ARROW) {
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu3 ? _menu3.getSlots().get(0).getItem() : ItemStack.EMPTY).getItem() == Items.PRISMARINE_SHARD) {
				var_tip = "prismarine";
			}
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu5 ? _menu5.getSlots().get(0).getItem() : ItemStack.EMPTY).getItem() == Items.ECHO_SHARD) {
				var_tip = "echo";
			}
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu7 ? _menu7.getSlots().get(0).getItem() : ItemStack.EMPTY).getItem() == Items.AMETHYST_SHARD) {
				var_tip = "am";
			}
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu9 ? _menu9.getSlots().get(1).getItem() : ItemStack.EMPTY).getItem() == Items.BLAZE_ROD) {
				var_shaft = "blaze";
			}
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu11 ? _menu11.getSlots().get(1).getItem() : ItemStack.EMPTY).getItem() == Items.BREEZE_ROD) {
				var_shaft = "breeze";
			}
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu13 ? _menu13.getSlots().get(2).getItem() : ItemStack.EMPTY).getItem() == Items.PHANTOM_MEMBRANE) {
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
					ItemStack _setstack19 = output_arrow.copy();
					_setstack19.setCount(getAmountInGUISlot(entity, 4));
					_menu.getSlots().get(3).set(_setstack19);
					_player.containerMenu.broadcastChanges();
				}
			} else {
				if (entity instanceof Player _player && _player.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu) {
					ItemStack _setstack20 = new ItemStack(Blocks.AIR).copy();
					_setstack20.setCount(1);
					_menu.getSlots().get(3).set(_setstack20);
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