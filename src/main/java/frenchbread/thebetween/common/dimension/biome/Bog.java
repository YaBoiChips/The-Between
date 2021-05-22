package frenchbread.thebetween.common.dimension.biome;

import frenchbread.thebetween.core.world.TBConfiguredFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

public class Bog extends BiomeBase{
    static final ConfiguredSurfaceBuilder<?> SURFACE_BUILDER = ConfiguredSurfaceBuilders.MYCELIUM;
    static final Biome.RainType PRECIPATATION = Biome.RainType.RAIN;
    static final Biome.Category CATEGORY = Biome.Category.SWAMP;
    static final float DEPTH = -0.1F;
    static final float SCALE = 0.019F;
    static final float TEMPERATURE = 6.0F;
    static final float DOWNFALL = 0.9F;
    static final int WATER_COLOR = 771064;
    static final int WATER_FOG_COLOR = 778064;
    static final Biome.Climate WEATHER = new Biome.Climate(PRECIPATATION, TEMPERATURE, Biome.TemperatureModifier.NONE, DOWNFALL);
    static final MobSpawnInfo.Builder SPAWN_SETTINGS = new MobSpawnInfo.Builder();
    static final BiomeGenerationSettings.Builder GENERATION_SETTINGS = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(SURFACE_BUILDER);



    public Bog() {
        super(WEATHER, CATEGORY, DEPTH, SCALE, (new BiomeAmbience.Builder()).setWaterColor(WATER_COLOR).setWaterFogColor(WATER_FOG_COLOR)
                .setWaterColor(WATER_COLOR)
                .setWaterFogColor(WATER_FOG_COLOR)
                .setFogColor(31064)
                .withSkyColor(0)
                .withFoliageColor(6975545).withGrassColorModifier(BiomeAmbience.GrassColorModifier.SWAMP)
                .setParticle(new ParticleEffectAmbience(ParticleTypes.WARPED_SPORE, 0.00428F))
                .setAmbientSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP)
                .setMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0D))
                .setAdditionsSound(new SoundAdditionsAmbience(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0010D))
                .setMusic(BackgroundMusicTracks.getDefaultBackgroundMusicSelector(SoundEvents.MUSIC_END)).build(), GENERATION_SETTINGS.build(), SPAWN_SETTINGS.build());
    }
    static {
        GENERATION_SETTINGS.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, Blocks.IRON_ORE.getDefaultState(), 22)).range(46).square().count(22));
        GENERATION_SETTINGS.withFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Features.FOREST_ROCK);
        GENERATION_SETTINGS.withFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Features.FOREST_ROCK);
        GENERATION_SETTINGS.withFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, TBConfiguredFeatures.BIG_FOREST_ROCK);
        GENERATION_SETTINGS.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.MUSHROOM_FIELD_VEGETATION);
        GENERATION_SETTINGS.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.BROWN_MUSHROOM_TAIGA);
        GENERATION_SETTINGS.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.RED_MUSHROOM_TAIGA);
    }
}
