package frenchbread.thebetween.core;

import com.mojang.serialization.Codec;
import frenchbread.thebetween.common.dimension.structures.RedCliffsTrunkPlacer;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;


public class TBTrunkPlacers {

    public static TrunkPlacerType<RedCliffsTrunkPlacer> RED_CLIFFS_TRUNK_PLACER;

    private static <P extends AbstractTrunkPlacer> TrunkPlacerType<P> register(String key, Codec<P> codec) {
        return Registry.register(Registry.TRUNK_REPLACER, key, new TrunkPlacerType<>(codec));
    }
    public static void registerTrunkPlacers(){
        RED_CLIFFS_TRUNK_PLACER = register("red_cliffs_trunk_placer", RedCliffsTrunkPlacer.CODEC);
    }
}
