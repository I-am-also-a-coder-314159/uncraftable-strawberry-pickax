package io.github.strawberrymc.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class LazuriteItem extends Item {
	public LazuriteItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public boolean isFoil(ItemStack itemstack) {
		return true;
	}
}