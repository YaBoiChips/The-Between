package frenchbread.thebetween.client.renderers.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import frenchbread.thebetween.TheBetween;
import frenchbread.thebetween.client.models.StoneGolemModel;
import frenchbread.thebetween.common.entities.AbstractStoneGolemEntity;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class StoneGolemRenderer<T extends AbstractStoneGolemEntity> extends BipedRenderer<T, StoneGolemModel<T>> {

    public StoneGolemRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new StoneGolemModel<>(0.0f), 1.0f);
    }

    protected void applyRotations(T entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    public ResourceLocation getEntityTexture(T entity) {
        return TheBetween.createResource("textures/entity/golum/stone_golum");
    }
}
