package frenchbread.thebetween.core.world;

import com.google.common.collect.ImmutableList;
import frenchbread.thebetween.TheBetween;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.Structure;


public class TBConfiguredStructures {

    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> CONFIGURED_TALL_FOREST_DUNGEON = TBStructures.TALL_FOREST_DUNGEON.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);

}