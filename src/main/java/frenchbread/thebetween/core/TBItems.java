package frenchbread.thebetween.core;

import frenchbread.thebetween.TheBetween;
import frenchbread.thebetween.common.items.SpearItem;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class TBItems {
    public static List<Item> items = new ArrayList<>();

    public static final ItemGroup CREATIVE_TAB = new ItemGroup(TheBetween.MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(TBItems.DARKWOOD_LOG);
        }

        @Override
        public boolean hasSearchBar() {
            return true;
        }

        @Override
        public boolean hasScrollbar() {
            return true;
        }

        @Override
        public ResourceLocation getBackgroundImage() {
            return new ResourceLocation("minecraft", "textures/gui/container/creative_inventory/tab_item_search.png");
        }
    };

    public static final Item TELEPORTER_BLOCK = createBlockItem(TBBlocks.BETWEEN_TELEPORTER, new Item.Properties().group(CREATIVE_TAB));
    public static final Item HELL_STONE = createBlockItem(TBBlocks.HELL_STONE, new Item.Properties().group(CREATIVE_TAB));
    public static final Item HELL_DIRT = createBlockItem(TBBlocks.HELL_DIRT, new Item.Properties().group(CREATIVE_TAB));
    public static final Item FROSTED_STONE = createBlockItem(TBBlocks.FROSTED_STONE, new Item.Properties().group(CREATIVE_TAB));
    public static final Item HELL_COBBLESTONE = createBlockItem(TBBlocks.HELL_COBBLESTONE, new Item.Properties().group(CREATIVE_TAB));
    public static final Item FROSTED_COBBLESTONE = createBlockItem(TBBlocks.FROSTED_COBBLESTONE, new Item.Properties().group(CREATIVE_TAB));
    public static final Item DUNGEON_GATE_KEY = createBlockItem(TBBlocks.DUNGEON_GATE_KEY, new Item.Properties().group(CREATIVE_TAB));
    public static final Item DUNGEON_GATE_KEY_2 = createBlockItem(TBBlocks.DUNGEON_GATE_KEY_2, new Item.Properties().group(CREATIVE_TAB));
    public static final Item DUNGEON_GATE_KEY_3 = createBlockItem(TBBlocks.DUNGEON_GATE_KEY_3, new Item.Properties().group(CREATIVE_TAB));
    public static final Item DUNGEON_GATE_FRAME = createBlockItem(TBBlocks.DUNGEON_GATE_FRAME, new Item.Properties().group(CREATIVE_TAB));
    public static final Item SNOWDROP = createBlockItem(TBBlocks.SNOWDROP, new Item.Properties().group(CREATIVE_TAB));

    public static final Item DUNGEON_KEY_1 = createItem(new Item(new Item.Properties().group(CREATIVE_TAB)), "dungeon_key_1");
    public static final Item DUNGEON_KEY_2 = createItem(new Item(new Item.Properties().group(CREATIVE_TAB)), "dungeon_key_2");
    public static final Item DUNGEON_KEY_3 = createItem(new Item(new Item.Properties().group(CREATIVE_TAB)), "dungeon_key_3");

    public static final Item SPEAR = createItem(new SpearItem(new Item.Properties().maxDamage(350).group(CREATIVE_TAB)), "spear");

    //tall forest
    public static final Item TALL_FOREST_DUNGEON_BLOCK = createBlockItem(TBBlocks.TALL_FOREST_DUNGEON_BLOCK, new Item.Properties().group(CREATIVE_TAB));
    public static final Item CRACKED_TALL_FOREST_DUNGEON_BLOCK = createBlockItem(TBBlocks.CRACKED_TALL_FOREST_DUNGEON_BLOCK, new Item.Properties().group(CREATIVE_TAB));
    public static final Item TALL_FOREST_DUNGEON_STAIRS = createBlockItem(TBBlocks.TALL_FOREST_DUNGEON_STAIRS, new Item.Properties().group(CREATIVE_TAB));
    public static final Item CRACKED_TALL_FOREST_DUNGEON_STAIRS = createBlockItem(TBBlocks.CRACKED_TALL_FOREST_DUNGEON_STAIRS, new Item.Properties().group(CREATIVE_TAB));
    public static final Item TALL_FOREST_DUNGEON_SLAB = createBlockItem(TBBlocks.TALL_FOREST_DUNGEON_SLAB, new Item.Properties().group(CREATIVE_TAB));
    public static final Item CRACKED_TALL_FOREST_DUNGEON_SLAB = createBlockItem(TBBlocks.CRACKED_TALL_FOREST_DUNGEON_SLAB, new Item.Properties().group(CREATIVE_TAB));
    public static final Item TALL_FOREST_STONE_GOLEM_SPEAR_SPAWN_EGG = createItem(new SpawnEggItem(TBEntities.TALL_FOREST_STONE_GOLEM_SPEAR, 196656, 186895, (new Item.Properties()).group(CREATIVE_TAB)), "tall_forest_stone_golem_spear_spawn_egg");
    public static final Item TALL_FOREST_STONE_GOLEM_SPAWN_EGG = createItem(new SpawnEggItem(TBEntities.TALL_FOREST_STONE_GOLEM, 496656, 386895, (new Item.Properties()).group(CREATIVE_TAB)), "tall_forest_stone_golem_spawn_egg");
    public static final Item DARKWOOD_LOG = createBlockItem(TBBlocks.DARKWOOD_LOG, new Item.Properties().group(CREATIVE_TAB));
    public static final Item DARKWOOD_LEAVES = createBlockItem(TBBlocks.DARKWOOD_LEAVES, new Item.Properties().group(CREATIVE_TAB));
    public static final Item DARKWOOD_PLANKS = createBlockItem(TBBlocks.DARKWOOD_PLANKS, new Item.Properties().group(CREATIVE_TAB));
    public static final Item DARKWOOD_STAIRS = createBlockItem(TBBlocks.DARKWOOD_STAIRS, new Item.Properties().group(CREATIVE_TAB));
    public static final Item DARKWOOD_SLAB = createBlockItem(TBBlocks.DARKWOOD_SLAB, new Item.Properties().group(CREATIVE_TAB));


    public static Item createItem(Item item, String id) {
        return createItem(item, TheBetween.createResource(id));
    }

    public static Item createBlockItem(Block block, Item.Properties props) {
        return createItem(new BlockItem(block, props), Registry.BLOCK.getKey(block));
    }

    public static Item createItem(Item item, ResourceLocation id) {
        if (id != null && !id.equals(new ResourceLocation("minecraft:air"))) {
            item.setRegistryName(id);

            items.add(item);

            return item;
        } else return null;
    }

    public static void init() {
    }
}
