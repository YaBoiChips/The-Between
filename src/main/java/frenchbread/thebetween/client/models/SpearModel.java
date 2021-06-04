package frenchbread.thebetween.client.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class SpearModel extends Model {
    private final ModelRenderer SpearHead_r1;
    private final ModelRenderer SpearHead_r1_r1;
    public SpearModel() {
        super(RenderType::getEntitySolid);
        textureWidth = 32;
        textureHeight = 32;

        SpearHead_r1 = new ModelRenderer(this);
        SpearHead_r1.setRotationPoint(0.0F, 12.0F, 0.0F);
        setRotationAngle(SpearHead_r1, 0.0F, 1.5708F, 0.0F);


        SpearHead_r1_r1 = new ModelRenderer(this);
        SpearHead_r1_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        SpearHead_r1.addChild(SpearHead_r1_r1);
        setRotationAngle(SpearHead_r1_r1, -1.5708F, 0.0F, 0.0F);
        SpearHead_r1_r1.setTextureOffset(0, 0).addBox(-1.0F, 0.0F, -13.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
        SpearHead_r1_r1.setTextureOffset(0, 13).addBox(-1.0F, 0.5F, -17.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        SpearHead_r1_r1.setTextureOffset(10, 13).addBox(-1.0F, -0.5F, -15.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    }




    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        SpearHead_r1.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
