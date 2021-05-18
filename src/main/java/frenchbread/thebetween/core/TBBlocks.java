package frenchbread.thebetween.core;

import frenchbread.thebetween.TheBetween;
import frenchbread.thebetween.common.block.TeleporterBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
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

    public static final Block DARKWOOD_LOG = createLog("darkwood_log");
    public static final Block DARKWOOD_LEAVES = registerBlock("darkwood_leaves", new LeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES).notSolid()));
    public static final Block HELL_STONE = createStoneBlock("hell_stone");
    public static final Block FROSTED_STONE = createStoneBlock("frosted_stone");

    static @Nonnull Block createTeleporterBlock(String id, RegistryKey<World> worldRegistryKey) {
        Block createBlock = new TeleporterBlock(AbstractBlock.Properties.create(Material.IRON).sound(SoundType.STONE).hardnessAndResistance(2.0f, 6.0f).harvestTool(ToolType.PICKAXE).setRequiresTool(), worldRegistryKey);
        return registerBlock(id, createBlock);
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
