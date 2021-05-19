package frenchbread.thebetween.core;


import frenchbread.thebetween.TheBetween;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.*;

import java.util.ArrayList;
import java.util.List;

public class TBFeatures {
    public static List<Feature<?>> features = new ArrayList<>();
    public static final Feature<BlockStateFeatureConfig> BIG_FOREST_ROCK = register("big_forest_rock", new BlockBlobFeature(BlockStateFeatureConfig.CODEC));

    public static <C extends IFeatureConfig, F extends Feature<C>> F register(String id, F feature) {
        ResourceLocation featureID = TheBetween.createResource(id);
        feature.setRegistryName(featureID);
        features.add(feature);
        return feature;
    }

    public static void init() {

    }
}
