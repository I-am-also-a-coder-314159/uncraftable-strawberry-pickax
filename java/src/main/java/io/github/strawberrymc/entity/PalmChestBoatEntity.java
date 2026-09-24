package io.github.strawberrymc.entity;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.entity.EntityType;

import io.github.strawberrymc.init.StrawberrymcModItems;

public class PalmChestBoatEntity extends ChestBoat {
	public PalmChestBoatEntity(EntityType<PalmChestBoatEntity> type, Level world) {
		super(type, world, StrawberrymcModItems.PALM_CHEST_BOAT);
	}
}