package io.github.strawberrymc.entity;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.entity.EntityType;

import io.github.strawberrymc.init.StrawberrymcModItems;

public class JuniperChestBoatEntity extends ChestBoat {
	public JuniperChestBoatEntity(EntityType<JuniperChestBoatEntity> type, Level world) {
		super(type, world, StrawberrymcModItems.JUNIPER_CHEST_BOAT);
	}
}