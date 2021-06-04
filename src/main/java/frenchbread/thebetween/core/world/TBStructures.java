package frenchbread.thebetween.core.world;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import frenchbread.thebetween.TheBetween;
import frenchbread.thebetween.common.dimension.structures.TallForestDungeonStructure;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.Method;
import java.util.*;

public class TBStructures {
    public static final DeferredRegister<Structure<?>> DEFERRED_REGISTRY = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, TheBetween.MOD_ID);
    public static final RegistryObject<Structure<NoFeatureConfig>> TALL_FOREST_DUNGEON = DEFERRED_REGISTRY.register("tall_forest_dungeon", () -> new TallForestDungeonStructure(NoFeatureConfig.CODEC));



    public static void init() {
        DEFERRED_REGISTRY.register(FMLJavaModLoadingContext.get().getModEventBus());

        FMLJavaModLoadingContext.get().getModEventBus().addListener(TBStructures::commonSetup);
        MinecraftForge.EVENT_BUS.addListener(TBStructures::addDimensionalSpacing);
        MinecraftForge.EVENT_BUS.addListener(TBStructures::onBiomeLoad);
    }

    private static void onBiomeLoad(BiomeLoadingEvent event) {
        if (event.getName().toString().equals("thebetween:tall_forest"))
        event.getGeneration().getStructures().add(() -> TBConfiguredStructures.CONFIGURED_TALL_FOREST_DUNGEON);
    }

    private static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // Add stronghold to the structure map
            Structure.NAME_STRUCTURE_BIMAP.put("Better Stronghold".toLowerCase(Locale.ROOT), TALL_FOREST_DUNGEON.get());

            // Add structure + spacing settings to default dimension structures.
            // Note that we make a similar change in the WorldEvent.Load handler
            // as a safety for custom dimension support.
            DimensionStructuresSettings.field_236191_b_ =
                    ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                            .putAll(DimensionStructuresSettings.field_236191_b_)
                            .put(TALL_FOREST_DUNGEON.get(), new StructureSeparationSettings(80, 30, 4206991))
                            .build();

            // Register the configured structure feature
            Registry.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, new ResourceLocation(TheBetween.MOD_ID, "tall_forest_dungeon"), TBConfiguredStructures.CONFIGURED_TALL_FOREST_DUNGEON);

            // Add structure feature to this to prevent any issues if other mods' custom ChunkGenerators use FlatGenerationSettings.STRUCTURES
            FlatGenerationSettings.STRUCTURES.put(TALL_FOREST_DUNGEON.get(), TBConfiguredStructures.CONFIGURED_TALL_FOREST_DUNGEON);

            // Register separation settings for mods that might need it, like Terraforged
            WorldGenRegistries.NOISE_SETTINGS.getEntries().forEach(settings -> {
                Map<Structure<?>, StructureSeparationSettings> structureMap = settings.getValue().getStructures().func_236195_a_();

                // Precaution in case a mod makes the structure map immutable like datapacks do
                if (structureMap instanceof ImmutableMap){
                    Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
                    tempMap.put(TALL_FOREST_DUNGEON.get(), DimensionStructuresSettings.field_236191_b_.get(TALL_FOREST_DUNGEON.get()));
                    settings.getValue().getStructures().field_236193_d_ = tempMap;
                } else {
                    structureMap.put(TALL_FOREST_DUNGEON.get(), DimensionStructuresSettings.field_236191_b_.get(TALL_FOREST_DUNGEON.get()));
                }
            });
        });
    }

    private static Method GETCODEC_METHOD; // Cached instance since this will never change once initialized

    @SuppressWarnings("unchecked")
    private static void addDimensionalSpacing(final WorldEvent.Load event) {
        if (event.getWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) event.getWorld();

            // Skip Terraforged's chunk generator as they are a special case of a mod locking down their chunkgenerator.
            // Credits to TelepathicGrunt for this.
            try {
                if (GETCODEC_METHOD == null) {
                    GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
                }

                ResourceLocation chunkGenResourceLocation  = Registry.CHUNK_GENERATOR_CODEC.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(serverWorld.getChunkProvider().generator));
                if (chunkGenResourceLocation  != null && chunkGenResourceLocation.getNamespace().equals("terraforged")) {
                    return;
                }
            } catch (Exception e) {
                TheBetween.LOGGER.error("Was unable to check if " + serverWorld.getDimensionKey().getLocation() + " is using Terraforged's ChunkGenerator.");
            }


            // We use a temp map to add our spacing because some mods handle immutable maps
            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkProvider().generator.func_235957_b_().func_236195_a_());
            if (serverWorld.getChunkProvider().getChunkGenerator() instanceof FlatChunkGenerator && serverWorld.getDimensionKey().equals(World.OVERWORLD)) {
                tempMap.keySet().remove(TBStructures.TALL_FOREST_DUNGEON.get());
            } else {
                tempMap.put(TBStructures.TALL_FOREST_DUNGEON.get(), DimensionStructuresSettings.field_236191_b_.get(TBStructures.TALL_FOREST_DUNGEON.get()));
            }
            serverWorld.getChunkProvider().generator.func_235957_b_().field_236193_d_ = tempMap;
        }
    }
}

