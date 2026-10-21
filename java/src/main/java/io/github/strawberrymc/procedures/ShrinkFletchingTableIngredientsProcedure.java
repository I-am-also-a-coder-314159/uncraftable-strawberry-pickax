package io.github.strawberrymc.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import io.github.strawberrymc.init.StrawberrymcModMenus;

public class ShrinkFletchingTableIngredientsProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && _player.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu) {
			_menu.getSlots().get(0).remove(5);
			_menu.getSlots().get(1).remove(5);
			_menu.getSlots().get(2).remove(5);
			_menu.getSlots().get(4).remove(5);
			_player.containerMenu.broadcastChanges();
		}
	}
}