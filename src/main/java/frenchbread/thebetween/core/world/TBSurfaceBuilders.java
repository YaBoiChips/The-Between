package frenchbread.thebetween.core.world;

import frenchbread.thebetween.TheBetween;
import frenchbread.thebetween.common.dimension.surfacebuilders.IceValleySurfaceBuilder;
import frenchbread.thebetween.common.dimension.surfacebuilders.RedCliffsSurfaceBuilder;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SoulSandValleySurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.ArrayList;
import java.util.List;

public class TBSurfaceBuilders {
    public static List<SurfaceBuilder<?>> surfaceBuilders = new ArrayList<>();

    public static final SurfaceBuilder<SurfaceBuilderConfig> ICE_VALLEY = createSurfaceBuilder("ice_valley", new IceValleySurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderConfig> RED_CLIFFS = createSurfaceBuilder("red_cliffs", new RedCliffsSurfaceBuilder(SurfaceBuilderConfig.CODEC));



    public static <SBC extends ISurfaceBuilderConfig, SB extends SurfaceBuilder<SBC>> SB createSurfaceBuilder(String id, SB surfaceBuilder) {
        ResourceLocation tbid = new ResourceLocation(TheBetween.MOD_ID, id);
        surfaceBuilder.setRegistryName(tbid);
        surfaceBuilders.add(surfaceBuilder);
        return surfaceBuilder;
    }

        public static void init() {
    }
}
