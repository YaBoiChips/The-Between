package frenchbread.thebetween.core.world;

import frenchbread.thebetween.TheBetween;
import frenchbread.thebetween.common.dimension.structures.RedCliffsTrunkPlacer;
import frenchbread.thebetween.core.TBBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.PineFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class TBConfiguredFeatures {
    public static final ConfiguredPlacement<NoPlacementConfig> RED_CLIFFS_TREE_PLACEMENT = Placement.HEIGHTMAP.configure(IPlacementConfig.NO_PLACEMENT_CONFIG);


    public static final ConfiguredFeature<?, ?> TALL_FOREST_TREE = Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(TBBlocks.DARKWOOD_LOG.getDefaultState()), new SimpleBlockStateProvider(TBBlocks.DARKWOOD_LEAVES.getDefaultState()), new SpruceFoliagePlacer(FeatureSpread.create(2, 1), FeatureSpread.create(1, 2), FeatureSpread.create(1, 1)), new StraightTrunkPlacer(13, 1, 0), new TwoLayerFeature(3, 0, 2))).setMaxWaterDepth(1).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(2, 0.1F, 1)));
    public static final ConfiguredFeature<?, ?> BIG_FOREST_ROCK = TBFeatures.BIG_FOREST_ROCK.withConfiguration(new BlockStateFeatureConfig(Blocks.MOSSY_COBBLESTONE.getDefaultState())).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).variableCount(12);
    public static final ConfiguredFeature<?, ?> BIG_COLD_ROCK = TBFeatures.BIG_COLD_ROCK.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).count(1);
    public static final ConfiguredFeature<?, ?> TWISTING_VINES_PATCH = Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().addWeightedBlockstate(Blocks.TWISTING_VINES.getDefaultState(), 8), new SimpleBlockPlacer())).tries(12).build());
    public static final ConfiguredFeature<?, ?> SNOWDROP_PATCH = Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().addWeightedBlockstate(TBBlocks.SNOWDROP.getDefaultState(), 8), new SimpleBlockPlacer())).tries(12).build());
    public static final ConfiguredFeature<?, ?> BLUE_GRASS_PATCH = Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().addWeightedBlockstate(TBBlocks.BLUE_GRASS.getDefaultState(), 24), new SimpleBlockPlacer())).tries(18).build());
    public static final ConfiguredFeature<?, ?> BLUE_FERN_PATCH = Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().addWeightedBlockstate(TBBlocks.BLUE_FERN.getDefaultState(), 24), new SimpleBlockPlacer())).tries(18).build());
    public static final ConfiguredFeature<?, ?> RED_CLIFFS_TREE = Feature.TREE.withConfiguration(new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.CRIMSON_STEM.getDefaultState()), new SimpleBlockStateProvider(Blocks.NETHER_WART_BLOCK.getDefaultState()), new AcaciaFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(0)), new RedCliffsTrunkPlacer(9, 1, 3), new TwoLayerFeature(1, 0, 2)).build()).withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(6)));

    public static void registerConfiguredFeatures() {
        Registry<ConfiguredFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_FEATURE;
        Registry.register(registry, TheBetween.createResource("configured_tall_forest_tree"), TALL_FOREST_TREE);
        Registry.register(registry, TheBetween.createResource("configured_red_cliffs_tree"), RED_CLIFFS_TREE);
        Registry.register(registry, TheBetween.createResource("configured_big_forest_rock"), BIG_FOREST_ROCK);
        Registry.register(registry, TheBetween.createResource("configured_twisting_vines"), TWISTING_VINES_PATCH);
        Registry.register(registry, TheBetween.createResource("configured_blue_fern_patch"), BLUE_FERN_PATCH);
        Registry.register(registry, TheBetween.createResource("configured_blue_grass_patch"), BLUE_GRASS_PATCH);
        Registry.register(registry, TheBetween.createResource("configured_snowdrop_patch"), SNOWDROP_PATCH);
    }
}
