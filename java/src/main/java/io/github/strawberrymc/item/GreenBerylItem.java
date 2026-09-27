package io.github.strawberrymc.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class GreenBerylItem extends Item {
	public GreenBerylItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON));
	}

	@Override
	public boolean isFoil(ItemStack itemstack) {
		return true;
	}
}