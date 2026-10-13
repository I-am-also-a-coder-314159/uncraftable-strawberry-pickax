package io.github.strawberrymc.item;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class FlintShardItem extends Item {
	public FlintShardItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public float getDestroySpeed(ItemStack itemstack, BlockState state) {
		return 1.2f;
	}
}