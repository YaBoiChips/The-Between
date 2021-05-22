package frenchbread.thebetween.client.renderers.entities;

import frenchbread.thebetween.TheBetween;
import frenchbread.thebetween.client.models.StoneGolemModel;
import frenchbread.thebetween.common.entities.AbstractStoneGolemEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class StoneGolemRenderer<T extends AbstractStoneGolemEntity> extends MobRenderer<T, StoneGolemModel<T>> {

    public StoneGolemRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new StoneGolemModel<>(), 1.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(T entity) {
        return TheBetween.createResource("textures/entity/golum/stone_golum");
    }
}
