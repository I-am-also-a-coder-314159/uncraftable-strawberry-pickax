package io.github.strawberrymc.client.gui;

import net.neoforged.neoforge.client.gui.widget.ExtendedSlider;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import io.github.strawberrymc.world.inventory.ForgingTableMenu;
import io.github.strawberrymc.init.StrawberrymcModScreens;

import com.mojang.blaze3d.platform.InputConstants;

public class ForgingTableScreen extends AbstractContainerScreen<ForgingTableMenu> implements StrawberrymcModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private ExtendedSlider option;
	private static final Identifier BACKGROUND = Identifier.parse("strawberrymc:textures/screens/forging_table.png");

	public ForgingTableScreen(ForgingTableMenu container, Inventory inventory, Component text) {
		super(container, inventory, text, 176, 166);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		if (elementType == 2 && elementState instanceof Number n) {
			if (name.equals("option"))
				option.setValue(n.doubleValue());
		}
		menuStateUpdateActive = false;
	}

	@Override
	public void extractRenderState(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.extractRenderState(guiGraphics, mouseX, mouseY, partialTicks);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.extractBackground(guiGraphics, mouseX, mouseY, partialTicks);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
	}

	@Override
	public boolean keyPressed(KeyEvent event) {
		int key = InputConstants.getKey(event).getValue();
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(event);
	}

	@Override
	protected void extractLabels(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY) {
		guiGraphics.text(this.font, Component.translatable("gui.strawberrymc.forging_table.label_forger"), 9, 5, -12829636, false);
		guiGraphics.text(this.font, Component.translatable("gui.strawberrymc.forging_table.label_base"), 14, 39, -12829636, false);
		guiGraphics.text(this.font, Component.translatable("gui.strawberrymc.forging_table.label_modifier"), 41, 39, -12829636, false);
		guiGraphics.text(this.font, Component.translatable("gui.strawberrymc.forging_table.label_remainder"), 39, 70, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		option = new ExtendedSlider(this.leftPos + 114, this.topPos + 53, 40, 20, Component.translatable("gui.strawberrymc.forging_table.option_prefix"), Component.translatable("gui.strawberrymc.forging_table.option_suffix"), 0, 3, 0, 1, 0, true) {
			@Override
			protected void applyValue() {
				if (!menuStateUpdateActive)
					menu.sendMenuStateUpdate(entity, 2, "option", this.getValue(), false);
			}
		};
		this.addRenderableWidget(option);
		if (!menuStateUpdateActive)
			menu.sendMenuStateUpdate(entity, 2, "option", option.getValue(), false);
	}
}