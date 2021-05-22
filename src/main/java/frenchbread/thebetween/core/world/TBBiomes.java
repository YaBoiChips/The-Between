package frenchbread.thebetween.core.world;

import frenchbread.thebetween.TheBetween;
import frenchbread.thebetween.common.dimension.biome.Bog;
import frenchbread.thebetween.common.dimension.biome.IceValley;
import frenchbread.thebetween.common.dimension.biome.RedCliffs;
import frenchbread.thebetween.common.dimension.biome.TallForest;
import net.minecraft.block.Block;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TBBiomes {
    public static List<PreserveBiomeOrder> biomes = new ArrayList<>();
    public static final Biome TALL_FOREST = registerBiome("tall_forest", new TallForest().getBiome(), 1);
    public static final Biome ICE_VALLEY = registerBiome("ice_valley", new IceValley().getBiome(), 2);
    public static final Biome RED_CLIFFS = registerBiome("red_cliffs", new RedCliffs().getBiome(), 3);
    public static final Biome BOG = registerBiome("bog", new Bog().getBiome(), 4);


    public static Biome registerBiome(String id, Biome biome, int numid) {
        biome.setRegistryName(TheBetween.createResource(id));

        biomes.add(new PreserveBiomeOrder(biome, numid));

        return biome;
    }

    public static void init() {
    }

    public static class PreserveBiomeOrder {
        private final Biome biome;
        private final int orderPosition;

        public PreserveBiomeOrder(Biome biome, int orderPosition) {
            this.biome = biome;
            this.orderPosition = orderPosition;
        }

        public Biome getBiome() {
            return biome;
        }

        public int getOrderPosition() {
            return orderPosition;
        }
    }
}
