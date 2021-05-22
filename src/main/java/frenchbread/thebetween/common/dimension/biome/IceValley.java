package frenchbread.thebetween.common.dimension.biome;

import frenchbread.thebetween.core.world.TBConfiguredFeatures;
import frenchbread.thebetween.core.world.TBConfiguredSurfaceBuilders;
import net.minecraft.block.Blocks;
import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;

public class IceValley extends BiomeBase {
    static final ConfiguredSurfaceBuilder<?> SURFACE_BUILDER = TBConfiguredSurfaceBuilders.ICE_VALLEY;
    static final Biome.RainType PRECIPATATION = Biome.RainType.SNOW;
    static final Biome.Category CATEGORY = Biome.Category.ICY;
    static final float DEPTH = 0.15F;
    static final float SCALE = 0.2F;
    static final float TEMPERATURE = -2.0F;
    static final float DOWNFALL = 0.9F;
    static final int WATER_COLOR = 31064;
    static final int WATER_FOG_COLOR = 38064;
    static final Biome.Climate WEATHER = new Biome.Climate(PRECIPATATION, TEMPERATURE, Biome.TemperatureModifier.NONE, DOWNFALL);
    static final MobSpawnInfo.Builder SPAWN_SETTINGS = new MobSpawnInfo.Builder();
    static final BiomeGenerationSettings.Builder GENERATION_SETTINGS = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(SURFACE_BUILDER);


    public IceValley() {
        super(WEATHER, CATEGORY, DEPTH, SCALE, (new BiomeAmbience.Builder()).setWaterColor(WATER_COLOR).setWaterFogColor(WATER_FOG_COLOR)
                .setWaterColor(WATER_COLOR)
                .setWaterFogColor(WATER_FOG_COLOR)
                .setFogColor(3158064)
                .withSkyColor(0)
                .setParticle(new ParticleEffectAmbience(ParticleTypes.WARPED_SPORE, 0.00428F))
                .setAmbientSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP)
                .setMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0D))
                .setAdditionsSound(new SoundAdditionsAmbience(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0010D))
                .setMusic(BackgroundMusicTracks.getDefaultBackgroundMusicSelector(SoundEvents.MUSIC_END)).build(), GENERATION_SETTINGS.build(), SPAWN_SETTINGS.build());
    }
    static {
        GENERATION_SETTINGS.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, Blocks.IRON_ORE.getDefaultState(), 22)).range(46).square().count(22));
        GENERATION_SETTINGS.withFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Feature.ICE_SPIKE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).count(1));
        SPAWN_SETTINGS.withSpawner(EntityClassification.AMBIENT, new MobSpawnInfo.Spawners(EntityType.SNOW_GOLEM, 102, 4, 4));
        GENERATION_SETTINGS.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TBConfiguredFeatures.SNOWDROP_PATCH);
        GENERATION_SETTINGS.withFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, TBConfiguredFeatures.BIG_COLD_ROCK);
        GENERATION_SETTINGS.withFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, TBConfiguredFeatures.BIG_COLD_ROCK);
        GENERATION_SETTINGS.withFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, TBConfiguredFeatures.BIG_COLD_ROCK);

    }
}
