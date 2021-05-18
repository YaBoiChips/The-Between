package frenchbread.thebetween.core;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import frenchbread.thebetween.TheBetween;
import frenchbread.thebetween.common.dimension.structures.BigBogRock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
