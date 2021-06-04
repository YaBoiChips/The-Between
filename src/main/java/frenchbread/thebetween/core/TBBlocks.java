package frenchbread.thebetween.core;

import frenchbread.thebetween.TheBetween;
import frenchbread.thebetween.common.block.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.potion.Effects;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class TBBlocks {

    public static List<Block> blocks = new ArrayList<>();

    public static final Block BETWEEN_TELEPORTER = createTeleporterBlock("between_teleporter", RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation(TheBetween.MOD_ID, "between")));

    public static final Block HELL_STONE = createStoneBlock("hell_stone");
    public static final Block HELL_COBBLESTONE = createCobbleBlock("hell_cobblestone");
    public static final Block FROSTED_COBBLESTONE = createCobbleBlock("frosted_cobblestone");
    public static final Block HELL_DIRT = createDirtBlock("hell_dirt");
    public static final Block FROSTED_STONE = createStoneBlock("frosted_stone");
    public static final Block DUNGEON_GATE_KEY = registerBlock("dungeon_gate_key", new DungeonGateKey(AbstractBlock.Properties.from(Blocks.BEDROCK)));
    public static final Block DUNGEON_GATE_KEY_2 = registerBlock("dungeon_gate_key_2", new DungeonGateKey2(AbstractBlock.Properties.from(Blocks.BEDROCK)));
    public static final Block DUNGEON_GATE_KEY_3 = registerBlock("dungeon_gate_key_3", new DungeonGateKey3(AbstractBlock.Properties.from(Blocks.BEDROCK)));
    public static final Block DUNGEON_GATE_FRAME = registerBlock("dungeon_gate_frame", new Block(AbstractBlock.Properties.from(Blocks.BEDROCK)));

    //tall forest
    public static final Block TALL_FOREST_DUNGEON_BLOCK = registerBlock("tall_forest_dungeon_brick", new Block(AbstractBlock.Properties.from(Blocks.BEDROCK)));
    public static final Block CRACKED_TALL_FOREST_DUNGEON_BLOCK = registerBlock("cracked_tall_forest_dungeon_brick", new Block(AbstractBlock.Properties.from(Blocks.BEDROCK)));
    public static final Block TALL_FOREST_DUNGEON_STAIRS = registerBlock("tall_forest_dungeon_brick_stairs", new StairsBlock(Blocks.BEDROCK.getDefaultState(), AbstractBlock.Properties.from(Blocks.BEDROCK)));
    public static final Block CRACKED_TALL_FOREST_DUNGEON_STAIRS = registerBlock("cracked_tall_forest_dungeon_stairs", new StairsBlock(Blocks.BEDROCK.getDefaultState(), AbstractBlock.Properties.from(Blocks.BEDROCK)));
    public static final Block TALL_FOREST_DUNGEON_SLAB = registerBlock("tall_forest_dungeon_brick_slab", new SlabBlock(AbstractBlock.Properties.from(Blocks.BEDROCK)));
    public static final Block CRACKED_TALL_FOREST_DUNGEON_SLAB = registerBlock("cracked_tall_forest_dungeon_brick_slab", new SlabBlock(AbstractBlock.Properties.from(Blocks.BEDROCK)));
    public static final Block BLUE_GRASS = createPlantBlock("blue_grass");
    public static final Block BLUE_FERN = createPlantBlock("blue_fern");
    public static final Block DARKWOOD_LOG = createLog("darkwood_log");
    public static final Block DARKWOOD_LEAVES = registerBlock("darkwood_leaves", new LeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES).notSolid()));
    public static final Block DARKWOOD_PLANKS = registerBlock("darkwood_planks", new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));
    public static final Block DARKWOOD_STAIRS = registerBlock("darkwood_stairs", new StairsBlock(TBBlocks.DARKWOOD_PLANKS.getDefaultState(), AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));
    public static final Block DARKWOOD_SLAB = registerBlock("darkwood_slab", new SlabBlock(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));


    //snow valley
    public static final Block SNOWDROP = registerBlock("snowdrop", new SnowPlantBlock(Effects.SATURATION, 7, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().notSolid().zeroHardnessAndResistance().sound(SoundType.PLANT)));

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
    static @Nonnull Block createPlantBlock(String id) {
        Block grass = new TallGrassBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT));
        return registerBlock(id, grass);
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
