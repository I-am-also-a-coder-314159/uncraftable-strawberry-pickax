/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package io.github.strawberrymc.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.client.renderer.entity.BoatRenderer;

@EventBusSubscriber(Dist.CLIENT)
public class StrawberrymcModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(StrawberrymcModEntities.ECHOWOOD_BOAT.get(), context -> new BoatRenderer(context, StrawberrymcModModels.ECHOWOOD_BOAT_LAYER_LOCATION));
		event.registerEntityRenderer(StrawberrymcModEntities.ECHOWOOD_CHEST_BOAT.get(), context -> new BoatRenderer(context, StrawberrymcModModels.ECHOWOOD_CHEST_BOAT_LAYER_LOCATION));
		event.registerEntityRenderer(StrawberrymcModEntities.PALM_BOAT.get(), context -> new BoatRenderer(context, StrawberrymcModModels.PALM_BOAT_LAYER_LOCATION));
		event.registerEntityRenderer(StrawberrymcModEntities.PALM_CHEST_BOAT.get(), context -> new BoatRenderer(context, StrawberrymcModModels.PALM_CHEST_BOAT_LAYER_LOCATION));
	}
}