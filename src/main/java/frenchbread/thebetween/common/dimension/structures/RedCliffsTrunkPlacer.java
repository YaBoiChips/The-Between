package frenchbread.thebetween.common.dimension.structures;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import frenchbread.thebetween.core.TBBlocks;
import frenchbread.thebetween.core.world.TBTrunkPlacers;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class RedCliffsTrunkPlacer extends AbstractTrunkPlacer {
    public static final Codec<RedCliffsTrunkPlacer> CODEC = RecordCodecBuilder.create((codec) -> {
        return getAbstractTrunkCodec(codec).apply(codec, RedCliffsTrunkPlacer::new);
    });

    public RedCliffsTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> getPlacerType() {
        return TBTrunkPlacers.RED_CLIFFS_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.Foliage> getFoliages(IWorldGenerationReader reader, Random rand, int treeHeight, BlockPos p_230382_4_, Set<BlockPos> p_230382_5_, MutableBoundingBox p_230382_6_, BaseTreeFeatureConfig p_230382_7_) {
        func_236909_a_(reader, p_230382_4_.down());
        List<FoliagePlacer.Foliage> list = Lists.newArrayList();
        Direction direction = Direction.Plane.HORIZONTAL.random(rand);
        int i = treeHeight - rand.nextInt(4) - 1;
        int j = 3 - rand.nextInt(3);
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
        int k = p_230382_4_.getX();
        int l = p_230382_4_.getZ();
        int i1 = 0;

        for(int j1 = 0; j1 < treeHeight; ++j1) {
            int k1 = p_230382_4_.getY() + j1;
            if (j1 >= i && j > 0) {
                k += direction.getXOffset();
                l += direction.getZOffset();
                --j;
            }

            if (func_236911_a_(reader, rand, blockpos$mutable.setPos(k, k1, l), p_230382_5_, p_230382_6_, p_230382_7_)) {
                i1 = k1 + 1;
            }
        }

        list.add(new FoliagePlacer.Foliage(new BlockPos(k, i1, l), 1, false));
        k = p_230382_4_.getX();
        l = p_230382_4_.getZ();
        Direction direction1 = Direction.Plane.HORIZONTAL.random(rand);
        if (direction1 != direction) {
            int k2 = i - rand.nextInt(2) - 1;
            int l1 = 1 + rand.nextInt(3);
            i1 = 0;

            for(int i2 = k2; i2 < treeHeight && l1 > 0; --l1) {
                if (i2 >= 1) {
                    int j2 = p_230382_4_.getY() + i2;
                    k += direction1.getXOffset();
                    l += direction1.getZOffset();
                    if (func_236911_a_(reader, rand, blockpos$mutable.setPos(k, j2, l), p_230382_5_, p_230382_6_, p_230382_7_)) {
                        i1 = j2 + 1;
                    }
                }

                ++i2;
            }

            if (i1 > 1) {
                list.add(new FoliagePlacer.Foliage(new BlockPos(k, i1, l), 0, false));
            }
        }

        return list;
    }


    private static boolean func_236912_a_(IWorldGenerationBaseReader reader, BlockPos pos) {
        return reader.hasBlockState(pos, (state) -> {
            Block block = state.getBlock();
            return block == Blocks.NETHERRACK && block == TBBlocks.HELL_STONE;
        });
    }

    protected static void func_236909_a_(IWorldGenerationReader reader, BlockPos pos) {
        if (!func_236912_a_(reader, pos)) {
            TreeFeature.setBlockStateWithoutUpdate(reader, pos, Blocks.NETHERRACK.getDefaultState());
        }

    }
}
