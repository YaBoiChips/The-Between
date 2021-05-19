package frenchbread.thebetween.core;

import frenchbread.thebetween.TheBetween;
import frenchbread.thebetween.common.block.TeleporterBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TBBlocks {

    public static List<Block> blocks = new ArrayList<>();

    public static final Block BETWEEN_TELEPORTER = createTeleporterBlock("between_teleporter", RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation(TheBetween.MOD_ID, "between")));

    public static final Block DARKWOOD_LOG = createLog("darkwood_log");
    public static final Block DARKWOOD_LEAVES = registerBlock("darkwood_leaves", new LeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES).notSolid()));
    public static final Block HELL_STONE = createStoneBlock("hell_stone");
    public static final Block HELL_COBBLESTONE = createCobbleBlock("hell_cobblestone");
    public static final Block FROSTED_COBBLESTONE = createCobbleBlock("frosted_cobblestone");
    public static final Block HELL_DIRT = createDirtBlock("hell_dirt");
    public static final Block FROSTED_STONE = createStoneBlock("frosted_stone");

    static @Nonnull Block createTeleporterBlock(String id, RegistryKey<World> worldRegistryKey) {
        Block createBlock = new TeleporterBlock(AbstractBlock.Properties.create(Material.IRON).sound(SoundType.STONE).hardnessAndResistance(2.0f, 6.0f).harvestTool(ToolType.PICKAXE).setRequiresTool(), worldRegistryKey);
        return registerBlock(id, createBlock);
    }

    static @Nonnull Block createDirtBlock(String id) {
        Block dirt = new Block(AbstractBlock.Properties.from(Blocks.DIRT));
        return registerBlock(id, dirt);
    }

    static @Nonnull Block createCobbleBlock(String id) {
        Block dirt = new Block(AbstractBlock.Properties.from(Blocks.COBBLESTONE));
        return registerBlock(id, dirt);
    }

    static @Nonnull Block createLog(String id) {
        Block createBlock = new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0f));
        return registerBlock(id, createBlock);
    }

    static @Nonnull Block createStoneBlock(String id) {
        Block stone = new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F));
        return registerBlock(id, stone);
    }

    static @Nonnull
    <T extends Block> T registerBlock(String id, @Nonnull T block) {
        block.setRegistryName(TheBetween.createResource(id));

        blocks.add(block);

        return block;
    }

    public static void init() {
    }

}
