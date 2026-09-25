/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package io.github.strawberrymc.init;

import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import io.github.strawberrymc.client.particle.CoconutFragmentParticle;

@EventBusSubscriber(Dist.CLIENT)
public class StrawberrymcModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(StrawberrymcModParticleTypes.COCONUT_FRAGMENT.get(), CoconutFragmentParticle::provider);
	}
}