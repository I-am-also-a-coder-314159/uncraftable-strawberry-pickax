package io.github.strawberrymc.entity;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.EntityType;

import io.github.strawberrymc.init.StrawberrymcModItems;

public class JuniperBoatEntity extends Boat {
	public JuniperBoatEntity(EntityType<JuniperBoatEntity> type, Level world) {
		super(type, world, StrawberrymcModItems.JUNIPER_BOAT);
	}
}