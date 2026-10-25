/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package io.github.strawberrymc.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.resources.Identifier;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;

import io.github.strawberrymc.client.model.Modelivy;
import io.github.strawberrymc.client.model.Modelgrass;
import io.github.strawberrymc.client.model.Modelcoconut_model_Converted;

@EventBusSubscriber(Dist.CLIENT)
public class StrawberrymcModModels {
	public static final ModelLayerLocation ECHOWOOD_BOAT_LAYER_LOCATION = new ModelLayerLocation(Identifier.parse("strawberrymc:boat/echowood_boat"), "main");
	public static final ModelLayerLocation ECHOWOOD_CHEST_BOAT_LAYER_LOCATION = new ModelLayerLocation(Identifier.parse("strawberrymc:chest_boat/echowood_chest_boat"), "main");
	public static final ModelLayerLocation PALM_BOAT_LAYER_LOCATION = new ModelLayerLocation(Identifier.parse("strawberrymc:boat/palm_boat"), "main");
	public static final ModelLayerLocation PALM_CHEST_BOAT_LAYER_LOCATION = new ModelLayerLocation(Identifier.parse("strawberrymc:chest_boat/palm_chest_boat"), "main");
	public static final ModelLayerLocation JUNIPER_BOAT_LAYER_LOCATION = new ModelLayerLocation(Identifier.parse("strawberrymc:boat/juniper_boat"), "main");
	public static final ModelLayerLocation JUNIPER_CHEST_BOAT_LAYER_LOCATION = new ModelLayerLocation(Identifier.parse("strawberrymc:chest_boat/juniper_chest_boat"), "main");

	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modelgrass.LAYER_LOCATION, Modelgrass::createBodyLayer);
		event.registerLayerDefinition(Modelcoconut_model_Converted.LAYER_LOCATION, Modelcoconut_model_Converted::createBodyLayer);
		event.registerLayerDefinition(Modelivy.LAYER_LOCATION, Modelivy::createBodyLayer);
		event.registerLayerDefinition(ECHOWOOD_BOAT_LAYER_LOCATION, BoatModel::createBoatModel);
		event.registerLayerDefinition(ECHOWOOD_CHEST_BOAT_LAYER_LOCATION, BoatModel::createChestBoatModel);
		event.registerLayerDefinition(PALM_BOAT_LAYER_LOCATION, BoatModel::createBoatModel);
		event.registerLayerDefinition(PALM_CHEST_BOAT_LAYER_LOCATION, BoatModel::createChestBoatModel);
		event.registerLayerDefinition(JUNIPER_BOAT_LAYER_LOCATION, BoatModel::createBoatModel);
		event.registerLayerDefinition(JUNIPER_CHEST_BOAT_LAYER_LOCATION, BoatModel::createChestBoatModel);
	}
}