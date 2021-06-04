package frenchbread.thebetween.client.renderers.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import frenchbread.thebetween.TheBetween;
import frenchbread.thebetween.client.models.SpearModel;
import frenchbread.thebetween.common.entities.AbstractSpearEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class SpearRenderer extends EntityRenderer<AbstractSpearEntity> {
    public static final ResourceLocation SPEAR_TEXTURE = new ResourceLocation(TheBetween.MOD_ID, "textures/entity/spear.png");
    private final frenchbread.thebetween.client.models.SpearModel spearModel = new SpearModel();

    public SpearRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    public void render(AbstractSpearEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.push();
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90.0F));
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch) + 90.0F));
        IVertexBuilder ivertexbuilder = net.minecraft.client.renderer.ItemRenderer.getEntityGlintVertexBuilder(bufferIn, this.spearModel.getRenderType(this.getEntityTexture(entityIn)), false, false);
        this.spearModel.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.pop();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    public ResourceLocation getEntityTexture(AbstractSpearEntity entity) {
        return SPEAR_TEXTURE;
    }
}

