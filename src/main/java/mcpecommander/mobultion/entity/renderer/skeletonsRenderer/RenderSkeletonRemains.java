package mcpecommander.mobultion.entity.renderer.skeletonsRenderer;

import mcpecommander.mobultion.Reference;
import mcpecommander.mobultion.entity.entities.skeletons.EntitySkeletonRemains;
import mcpecommander.mobultion.entity.model.ModelCraftStudioSon;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderSkeletonRemains extends RenderLivingBase<EntitySkeletonRemains> {
	private static final DynamicTexture TEXTURE_BRIGHTNESS = new DynamicTexture(16, 16);
	public ResourceLocation mobTexture = new ResourceLocation(Reference.MOD_ID,
			"textures/entity/withering_skeleton.png");
	public ResourceLocation mobTexture2 = new ResourceLocation(Reference.MOD_ID,
			"textures/entity/volcanic_skeleton.png");
	public ResourceLocation mobTexture3 = new ResourceLocation(Reference.MOD_ID, "textures/entity/forest_skeleton.png");
	//public ResourceLocation mobTexture4 = new ResourceLocation(Reference.MOD_ID, "textures/entity/joker_skeleton.png");
	public ResourceLocation mobTexture4 = new ResourceLocation(Reference.MOD_ID,
			"textures/entity/corrupted_skeleton.png");
	public ResourceLocation mobTexture5 = new ResourceLocation(Reference.MOD_ID,
			"textures/entity/vampire_skeleton.png");
	public static ModelCraftStudioSon model = new ModelCraftStudioSon(Reference.MOD_ID, "skeleton_remains", 64, 32);

	protected RenderSkeletonRemains(RenderManager renderManager) {
		super(renderManager, model, 0.25f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySkeletonRemains entity) {
		switch (entity.getTYPE()) {
		case (byte) 1:
			return mobTexture;
		case (byte) 2:
			return mobTexture2;
		case (byte) 3:
		case (byte) 4:
		case (byte) 5:
			return mobTexture3;
		// case (byte)5: return mobTexture4;
		case (byte) 6:
			return mobTexture4;
		case (byte) 7:
			return mobTexture5;
		default:
			return mobTexture;
		}

	}

	@Override
	protected void applyRotations(EntitySkeletonRemains entityLiving, float p_77043_2_, float rotationYaw,
			float partialTicks) {
		GlStateManager.rotate(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);
		String s = TextFormatting.getTextWithoutFormattingCodes(entityLiving.getName());
		if (s != null && ("Dinnerbone".equals(s) || "Grumm".equals(s))) {
			GlStateManager.translate(0.0F, entityLiving.height + 0.1F, 0.0F);
			GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
		}
	}

	@Override
	protected boolean canRenderName(EntitySkeletonRemains entity) {
		return entity.hasCustomName();
	}

	@Override
	protected boolean setBrightness(EntitySkeletonRemains entitylivingbaseIn, float partialTicks,
			boolean combineTextures) {
		float f = entitylivingbaseIn.getBrightness();
		int i = this.getColorMultiplier(entitylivingbaseIn, f, partialTicks);
		boolean flag = (i >> 24 & 255) > 0;
		boolean flag1 = entitylivingbaseIn.hurtTime > 0;

		if (!flag && !flag1) {
			return false;
		} else if (!flag && !combineTextures) {
			return false;
		} else {
			GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
			GlStateManager.enableTexture2D();
			GlStateManager.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.defaultTexUnit);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PRIMARY_COLOR);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.defaultTexUnit);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
			GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
			GlStateManager.enableTexture2D();
			GlStateManager.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, OpenGlHelper.GL_INTERPOLATE);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.GL_CONSTANT);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PREVIOUS);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE2_RGB, OpenGlHelper.GL_CONSTANT);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND2_RGB, 770);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.GL_PREVIOUS);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
			this.brightnessBuffer.position(0);

			if (flag1) {
				this.brightnessBuffer.put(1.0F);
				this.brightnessBuffer.put(0.0F);
				this.brightnessBuffer.put(0.0F);
				this.brightnessBuffer.put(0.3F);
			} else {
				float f1 = (i >> 24 & 255) / 255.0F;
				float f2 = (i >> 16 & 255) / 255.0F;
				float f3 = (i >> 8 & 255) / 255.0F;
				float f4 = (i & 255) / 255.0F;
				this.brightnessBuffer.put(f2);
				this.brightnessBuffer.put(f3);
				this.brightnessBuffer.put(f4);
				this.brightnessBuffer.put(1.0F - f1);
			}

			this.brightnessBuffer.flip();
			GlStateManager.glTexEnv(8960, 8705, this.brightnessBuffer);
			GlStateManager.setActiveTexture(OpenGlHelper.GL_TEXTURE2);
			GlStateManager.enableTexture2D();
			GlStateManager.bindTexture(TEXTURE_BRIGHTNESS.getGlTextureId());
			GlStateManager.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.GL_PREVIOUS);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.lightmapTexUnit);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.GL_PREVIOUS);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
			GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
			return true;
		}
	}

	public static class Factory implements IRenderFactory<EntitySkeletonRemains> {

		@Override
		public Render<? super EntitySkeletonRemains> createRenderFor(RenderManager manager) {
			return new RenderSkeletonRemains(manager);
		}

	}

}
