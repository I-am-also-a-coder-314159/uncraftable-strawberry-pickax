package io.github.strawberrymc.client.renderer;

import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;

import io.github.strawberrymc.entity.CoconutProjectileEntity;
import io.github.strawberrymc.client.model.Modelcoconut_model_Converted;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.PoseStack;

public class CoconutProjectileRenderer extends EntityRenderer<CoconutProjectileEntity, LivingEntityRenderState> {
	private static final Identifier texture = Identifier.parse("strawberrymc:textures/entities/palm_chest_boat.png");
	private final Modelcoconut_model_Converted model;

	public CoconutProjectileRenderer(EntityRendererProvider.Context context) {
		super(context);
		model = new Modelcoconut_model_Converted(context.bakeLayer(Modelcoconut_model_Converted.LAYER_LOCATION));
	}

	@Override
	public void submit(LivingEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
		poseStack.pushPose();
		poseStack.mulPose(Axis.YP.rotationDegrees(state.yRot - 90));
		poseStack.mulPose(Axis.ZP.rotationDegrees(90 + state.xRot));
		model.setupAnim(state);
		submitNodeCollector.submitModel(this.model, state, poseStack, texture, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
		poseStack.popPose();
		super.submit(state, poseStack, submitNodeCollector, camera);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(CoconutProjectileEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.xRot = entity.getXRot(partialTicks);
		state.yRot = entity.getYRot(partialTicks);
	}
}