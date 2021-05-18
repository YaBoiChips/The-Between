package frenchbread.thebetween.core;

import net.minecraft.block.Blocks;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class TBConfiguredSurfaceBuilders {

    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> ICE_VALLEY = TBSurfaceBuilders.ICE_VALLEY.func_242929_a(new SurfaceBuilderConfig(Blocks.SNOW_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.ICE.getDefaultState()));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> RED_CLIFFS = TBSurfaceBuilders.RED_CLIFFS.func_242929_a(new SurfaceBuilderConfig(Blocks.NETHERRACK.getDefaultState(), Blocks.COBBLESTONE.getDefaultState(), Blocks.DIRT.getDefaultState()));

    public static void register(){
        WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, "ice_valley", TBConfiguredSurfaceBuilders.ICE_VALLEY);
        WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, "red_cliffs", TBConfiguredSurfaceBuilders.RED_CLIFFS);

    }
}
