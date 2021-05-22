package frenchbread.thebetween.core.world;

import frenchbread.thebetween.TheBetween;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

import java.util.ArrayList;
import java.util.List;

public class TBStructures {
    public static List<Structure<?>> structures = new ArrayList<>();


    public static <C extends IFeatureConfig, F extends Structure<C>> F createStructure(String id, F structure, int minChunkDistance, int maxChunkDistance, int seedModifier, GenerationStage.Decoration decorationStage) {
        ResourceLocation tbid = new ResourceLocation(TheBetween.MOD_ID, id);
        structure.setRegistryName(tbid);
        TBStructures.structures.add(structure);
        Structure.NAME_STRUCTURE_BIMAP.put(tbid.toString(), structure);
        Structure.STRUCTURE_DECORATION_STAGE_MAP.put(structure, decorationStage);
        return structure;
    }
    public static void init() {
    }
}
