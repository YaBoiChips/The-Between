package frenchbread.thebetween.core;

import frenchbread.thebetween.TheBetween;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
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

    public static final Item DARKWOOD_LOG = createBlockItem(TBBlocks.DARKWOOD_LOG, new Item.Properties().group(CREATIVE_TAB));
    public static final Item DARKWOOD_LEAVES = createBlockItem(TBBlocks.DARKWOOD_LEAVES, new Item.Properties().group(CREATIVE_TAB));
    public static final Item TELEPORTER_BLOCK = createBlockItem(TBBlocks.BETWEEN_TELEPORTER, new Item.Properties().group(CREATIVE_TAB));
    public static final Item HELL_STONE = createBlockItem(TBBlocks.HELL_STONE, new Item.Properties().group(CREATIVE_TAB));
    public static final Item FROSTED_STONE = createBlockItem(TBBlocks.FROSTED_STONE, new Item.Properties().group(CREATIVE_TAB));


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
