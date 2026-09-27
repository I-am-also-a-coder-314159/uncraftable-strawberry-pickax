package io.github.strawberrymc.item;

import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class CoconutOilItem extends Item {
	public CoconutOilItem(Item.Properties properties) {
		super(properties.food((new FoodProperties.Builder()).nutrition(0).saturationModifier(0f).alwaysEdible().build(), Consumables.DEFAULT_DRINK).usingConvertsTo(Items.GLASS_BOTTLE));
	}
}