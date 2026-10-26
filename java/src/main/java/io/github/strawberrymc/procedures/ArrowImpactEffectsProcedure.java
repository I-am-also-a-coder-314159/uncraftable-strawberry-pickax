package io.github.strawberrymc.procedures;

import net.neoforged.neoforge.transfer.item.ItemUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.component.DataComponents;

import java.util.Comparator;

public class ArrowImpactEffectsProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		ItemStack ammo_item = ItemStack.EMPTY;
		double slot_index = 0;
		boolean found_arrow = false;
		slot_index = 0;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == Items.ARROW) {
			ammo_item = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).copy();
			found_arrow = true;
		} else {
			while (found_arrow == false && slot_index < 36) {
				if ((getEntitySlot(entity, (int) slot_index)).getItem() == Items.ARROW) {
					ammo_item = (getEntitySlot(entity, (int) slot_index)).copy();
					found_arrow = true;
				}
				slot_index = slot_index + 1;
			}
		}
		(findEntityInWorldRange(world, Arrow.class, x, y, z, 4)).getPersistentData().putString("tip", (ammo_item.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getStringOr("tip", "")));
		(findEntityInWorldRange(world, Arrow.class, x, y, z, 4)).getPersistentData().putString("shaft", (ammo_item.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getStringOr("shaft", "")));
		(findEntityInWorldRange(world, Arrow.class, x, y, z, 4)).getPersistentData().putString("fletching", (ammo_item.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getStringOr("fletching", "")));
	}

	private static ItemStack getEntitySlot(Entity entity, int slot) {
		if (entity != null) {
			ResourceHandler<ItemResource> resourceHandler = entity.getCapability(Capabilities.Item.ENTITY, null);
			if (resourceHandler != null) {
				return ItemUtil.getStack(resourceHandler, slot);
			}
		}
		return ItemStack.EMPTY;
	}

	private static Entity findEntityInWorldRange(LevelAccessor world, Class<? extends Entity> clazz, double x, double y, double z, double range) {
		return (Entity) world.getEntitiesOfClass(clazz, AABB.ofSize(new Vec3(x, y, z), range, range, range), e -> true).stream().sorted(Comparator.comparingDouble(e -> e.distanceToSqr(x, y, z))).findFirst().orElse(null);
	}
}