/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package io.github.strawberrymc.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import io.github.strawberrymc.client.gui.GemcutterScreen;

@EventBusSubscriber(Dist.CLIENT)
public class StrawberrymcModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(StrawberrymcModMenus.GEMCUTTER.get(), GemcutterScreen::new);
		event.register(StrawberrymcModMenus.FORGING_TABLE.get(), ForgingTableScreen::new);
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}