/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package io.github.strawberrymc.init;

import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import io.github.strawberrymc.client.particle.InvulnerabilityParticleParticle;
import io.github.strawberrymc.client.particle.DustcloudParticle;
import io.github.strawberrymc.client.particle.CoconutImpactParticle;

@EventBusSubscriber(Dist.CLIENT)
public class StrawberrymcModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(StrawberrymcModParticleTypes.DUSTCLOUD.get(), DustcloudParticle::provider);
		event.registerSpriteSet(StrawberrymcModParticleTypes.COCONUT_IMPACT.get(), CoconutImpactParticle::provider);
		event.registerSpriteSet(StrawberrymcModParticleTypes.INVULNERABILITY_PARTICLE.get(), InvulnerabilityParticleParticle::provider);
	}
}