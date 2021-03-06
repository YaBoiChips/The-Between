package frenchbread.thebetween.common.dimension.features;

import com.mojang.serialization.Codec;
import frenchbread.thebetween.core.TBBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class BoulderFeature extends Feature<NoFeatureConfig> {
    public BoulderFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        while(reader.isAirBlock(pos) && pos.getY() > 2) {
            pos = pos.down();
        }

        if (!reader.getBlockState(pos).matchesBlock(Blocks.SNOW_BLOCK)) {
            return false;
        } else {
            pos = pos.up(rand.nextInt(2));
            int i = rand.nextInt(3) + 3;
            int j = i / 4 + rand.nextInt(2);
            if (j > 1 && rand.nextInt(60) == 0) {
                pos = pos.up(10 + rand.nextInt(30));
            }
            for(int k = 0; k < i; ++k) {
                float f = (1.0F - (float)k / (float)i) * (float)j;
                int l = MathHelper.ceil(f);

                for(int i1 = -l; i1 <= l; ++i1) {
                    float f1 = (float)MathHelper.abs(i1) - 0.1F;

                    for(int j1 = -l; j1 <= l; ++j1) {
                        float f2 = (float)MathHelper.abs(j1) - 0.1F;
                        if ((i1 == 0 && j1 == 0 || !(f1 * f1 + f2 * f2 > f * f)) && (i1 != -l && i1 != l && j1 != -l && j1 != l || !(rand.nextFloat() > 0.75F))) {
                            BlockState blockstate = reader.getBlockState(pos.add(i1, k, j1));
                            Block block = blockstate.getBlock();
                            if (blockstate.isAir(reader, pos.add(i1, k, j1)) || isDirt(block) || block == Blocks.SNOW_BLOCK || block == Blocks.ICE || block == TBBlocks.FROSTED_STONE) {
                                this.setBlockState(reader, pos.add(i1, k, j1), TBBlocks.FROSTED_STONE.getDefaultState());
                            }
                        }
                    }
                }
            }

            int k1 = j - 1;
            if (k1 < 0) {
                k1 = 0;
            } else if (k1 > 1) {
                k1 = 1;
            }

            for(int l1 = -k1; l1 <= k1; ++l1) {
                for(int i2 = -k1; i2 <= k1; ++i2) {
                    BlockPos blockpos = pos.add(l1, -1, i2);
                    int j2 = 50;
                    if (Math.abs(l1) == 1 && Math.abs(i2) == 1) {
                        j2 = rand.nextInt(5);
                    }

                    while(blockpos.getY() > 50) {
                        BlockState blockstate1 = reader.getBlockState(blockpos);
                        Block block1 = blockstate1.getBlock();
                        if (!blockstate1.isAir(reader, blockpos) && !isDirt(block1) && block1 != Blocks.SNOW_BLOCK && block1 != Blocks.ICE && block1 != Blocks.PACKED_ICE) {
                            break;
                        }

                        this.setBlockState(reader, blockpos, TBBlocks.FROSTED_STONE.getDefaultState());
                        blockpos = blockpos.down();
                        --j2;
                        if (j2 <= 0) {
                            blockpos = blockpos.down(rand.nextInt(3) + 1);
                            j2 = rand.nextInt(36);
                        }
                    }
                }
            }
            return true;
        }
    }
}
