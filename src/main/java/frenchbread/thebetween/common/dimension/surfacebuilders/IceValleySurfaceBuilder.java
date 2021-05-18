package frenchbread.thebetween.common.dimension.surfacebuilders;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import frenchbread.thebetween.core.TBBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.ValleySurfaceBuilder;

public class IceValleySurfaceBuilder extends ValleySurfaceBuilder {
    private static final BlockState SNOW = Blocks.SNOW_BLOCK.getDefaultState();
    private static final BlockState FROSTED_STONE = TBBlocks.FROSTED_STONE.getDefaultState();
    private static final BlockState DIRT = Blocks.DIRT.getDefaultState();
    private static final ImmutableList<BlockState> GROUND_STATES = ImmutableList.of(SNOW, FROSTED_STONE);

    public IceValleySurfaceBuilder(Codec<SurfaceBuilderConfig> codec) {
        super(codec);
    }

    protected ImmutableList<BlockState> func_230387_a_() {
        return GROUND_STATES;
    }

    protected ImmutableList<BlockState> func_230388_b_() {
        return GROUND_STATES;
    }

    protected BlockState func_230389_c_() {
        return SNOW;
    }
}

