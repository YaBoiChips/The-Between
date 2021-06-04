package frenchbread.thebetween.core;

import frenchbread.thebetween.TheBetween;
import frenchbread.thebetween.common.entities.AbstractSpearEntity;
import frenchbread.thebetween.common.entities.AbstractStoneGolemEntity;
import frenchbread.thebetween.common.entities.StoneGolemBrute;
import frenchbread.thebetween.common.entities.StoneGolemSpear;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class TBEntities {
    public static List<EntityType<?>> entities = new ArrayList<>();

    public static final EntityType<StoneGolemBrute> TALL_FOREST_STONE_GOLEM = createEntity("tall_forest_stone_golem", EntityType.Builder.create(StoneGolemBrute::new, EntityClassification.MONSTER).build("tall_forest_stone_golem"));
    public static final EntityType<StoneGolemSpear> TALL_FOREST_STONE_GOLEM_SPEAR = createEntity("tall_forest_stone_golem_spear", EntityType.Builder.create(StoneGolemSpear::new, EntityClassification.MONSTER).build("tall_forest_stone_golem_spear"));
    public static final EntityType<AbstractSpearEntity> SPEAR = createEntity("spear", EntityType.Builder.<AbstractSpearEntity>create(AbstractSpearEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).trackingRange(4).updateInterval(20).build("spear"));

    public static <E extends Entity, ET extends EntityType<E>> ET createEntity(String id, ET entityType) {
        entityType.setRegistryName(new ResourceLocation(TheBetween.MOD_ID, id));
        entities.add(entityType);
        return entityType;
    }
    public static void init() {
    }
}
