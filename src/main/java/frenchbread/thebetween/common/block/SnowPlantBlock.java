package frenchbread.thebetween.common.block;

import frenchbread.thebetween.core.TBBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.potion.Effect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class SnowPlantBlock extends FlowerBlock {
    public SnowPlantBlock(Effect effect, int effectDuration, Properties properties) {
        super(effect, effectDuration, properties);
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.matchesBlock(Blocks.GRASS_BLOCK) || state.matchesBlock(Blocks.DIRT) || state.matchesBlock(Blocks.COARSE_DIRT) || state.matchesBlock(Blocks.PODZOL) || state.matchesBlock(Blocks.FARMLAND) || state.matchesBlock(Blocks.SNOW_BLOCK);
    }
}
