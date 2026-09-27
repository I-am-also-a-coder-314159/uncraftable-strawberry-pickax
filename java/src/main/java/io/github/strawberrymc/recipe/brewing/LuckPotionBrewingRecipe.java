package io.github.strawberrymc.recipe.brewing;

import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.common.brewing.IBrewingRecipe;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

import io.github.strawberrymc.init.StrawberrymcModPotions;
import io.github.strawberrymc.init.StrawberrymcModItems;

@EventBusSubscriber
public class LuckPotionBrewingRecipe implements IBrewingRecipe {
	@SubscribeEvent
	public static void init(RegisterBrewingRecipesEvent event) {
		event.getBuilder().addRecipe(new LuckPotionBrewingRecipe());
	}

	@Override
	public boolean isInput(ItemStack input) {
		return Ingredient.of(Items.BLAZE_POWDER).test(input);
	}

	@Override
	public boolean isIngredient(ItemStack ingredient) {
		return Ingredient.of(StrawberrymcModItems.RARE_CLOVER_CLUFF.get()).test(ingredient);
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
		if (isInput(input) && isIngredient(ingredient)) {
			return PotionContents.createItemStack(Items.POTION, StrawberrymcModPotions.HERB);
		}
		return ItemStack.EMPTY;
	}
}