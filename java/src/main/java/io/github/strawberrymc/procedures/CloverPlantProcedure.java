package io.github.strawberrymc.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import io.github.strawberrymc.init.StrawberrymcModItems;
import io.github.strawberrymc.init.StrawberrymcModBlocks;

public class CloverPlantProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity sourceentity) {
		if (sourceentity == null)
			return;
		world.setBlock(BlockPos.containing(x, y + 1, z), StrawberrymcModBlocks.CLOVER_MAT.get().defaultBlockState(), 3);
		if (sourceentity instanceof Player _player) {
			ItemStack _stktoremove = new ItemStack(StrawberrymcModItems.CLOVER_CLUFF.get());
			_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
		}
	}
}