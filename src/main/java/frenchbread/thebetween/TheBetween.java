package frenchbread.thebetween;

import frenchbread.thebetween.client.renderers.entities.SpearRenderer;
import frenchbread.thebetween.client.renderers.entities.StoneGolemRenderer;
import frenchbread.thebetween.client.renderers.worldrenderers.TBCutOutTextures;
import frenchbread.thebetween.common.entities.AbstractStoneGolemEntity;
import frenchbread.thebetween.common.entities.StoneGolemBrute;
import frenchbread.thebetween.core.*;
import frenchbread.thebetween.core.world.*;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.Item;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TheBetween.MOD_ID)
public class TheBetween {

    public static final String MOD_ID = "thebetween";
    public static final Logger LOGGER = LogManager.getLogger();
    public static RegistryKey<World> BETWEEN_DIMENSION;

    public TheBetween() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(this);
        TBStructures.init();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM PREINIT");
        BETWEEN_DIMENSION = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation(MOD_ID, "between"));
        event.enqueueWork(TBConfiguredFeatures::registerConfiguredFeatures);
        TBTrunkPlacers.registerTrunkPlacers();
        TBConfiguredSurfaceBuilders.register();
        GlobalEntityTypeAttributes.put(TBEntities.TALL_FOREST_STONE_GOLEM, AbstractStoneGolemEntity.setCustomAttributes().create());
        GlobalEntityTypeAttributes.put(TBEntities.TALL_FOREST_STONE_GOLEM_SPEAR, AbstractStoneGolemEntity.setCustomAttributes().create());

    }

    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
        TBCutOutTextures.registerCutOuts();
        RenderingRegistry.registerEntityRenderingHandler(TBEntities.TALL_FOREST_STONE_GOLEM, StoneGolemRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(TBEntities.TALL_FOREST_STONE_GOLEM_SPEAR, StoneGolemRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(TBEntities.SPEAR, SpearRenderer::new);
    }


    private void enqueueIMC(final InterModEnqueueEvent event) {
        InterModComms.sendTo(MOD_ID, "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    public static @Nonnull
    ResourceLocation createResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(RegistryEvent.Register<Block> event) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
            TBBlocks.init();
            TBBlocks.blocks.forEach(block -> event.getRegistry().register(block));
            TBBlocks.blocks.clear();
            TBBlocks.blocks = null;
        }
        @SubscribeEvent
        public static void onBiomeRegistry(RegistryEvent.Register<Biome> event) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
            TBBiomes.init();
            TBBiomes.biomes.sort(Comparator.comparingInt(TBBiomes.PreserveBiomeOrder::getOrderPosition));
            TBBiomes.biomes.forEach(preserveBiomeOrder -> event.getRegistry().register(preserveBiomeOrder.getBiome()));
            TBBiomes.biomes.clear();
            TBBiomes.biomes = null;
        }
        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            LOGGER.debug("Registering items...");
            TBItems.init();
            TBItems.items.forEach(item -> event.getRegistry().register(item));
            TBItems.items.clear();
            TBItems.items = null;
        }
        @SubscribeEvent
        public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
            LOGGER.debug("Registering features...");
            TBFeatures.init();
            TBFeatures.features.forEach(feature -> event.getRegistry().register(feature));
            TBFeatures.features.clear();
            TBFeatures.features = null;
            LOGGER.info("Features registered!");
        }
        @SubscribeEvent
        public static void registerSurfaceBuilders(RegistryEvent.Register<SurfaceBuilder<?>> event) {
            LOGGER.debug("Registering surface builders...");
            TBSurfaceBuilders.init();
            TBSurfaceBuilders.surfaceBuilders.forEach(surfaceBuilder -> event.getRegistry().register(surfaceBuilder));
            TBSurfaceBuilders.surfaceBuilders.clear();
            TBSurfaceBuilders.surfaceBuilders = null;
            LOGGER.info("Surface builders Registered!");
        }
    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
        LOGGER.debug("Preparing Entities");
        TBEntities.init();
        TBEntities.entities.forEach(entityType -> event.getRegistry().register(entityType));
        TBEntities.entities.clear();
        TBEntities.entities = null;
        LOGGER.info("Entities registered!!");
    }
}
}
