package frenchbread.thebetween.common.dimension.surfacebuilders;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import frenchbread.thebetween.core.TBBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.MountainSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.ValleySurfaceBuilder;

import java.util.Random;

public class RedCliffsSurfaceBuilder extends MountainSurfaceBuilder {
    private static final BlockState NETHERRACK = Blocks.NETHERRACK.getDefaultState();
    private static final BlockState HELL_STONE = TBBlocks.HELL_STONE.getDefaultState();
    private static final BlockState SOUL_SOIL = Blocks.SOUL_SOIL.getDefaultState();
    public static final SurfaceBuilderConfig HELLSTONE_HELLSTONE_SOULSOIL = new SurfaceBuilderConfig(HELL_STONE, HELL_STONE, SOUL_SOIL);
    public static final SurfaceBuilderConfig NETHERRACK_HELLSTONE_SOULSOIL = new SurfaceBuilderConfig(NETHERRACK, HELL_STONE, SOUL_SOIL);



    public RedCliffsSurfaceBuilder(Codec<SurfaceBuilderConfig> codec) {
        super(codec);
    }

    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        if (noise > 1.5D) {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, HELLSTONE_HELLSTONE_SOULSOIL);
        } else {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, NETHERRACK_HELLSTONE_SOULSOIL);
        }
    }
}
