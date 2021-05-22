package frenchbread.thebetween.client.renderers.worldrenderers;

import frenchbread.thebetween.core.TBBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

import java.util.Arrays;

public class TBCutOutTextures {

    public static void registerCutOuts(){
        Block[] blocks = {
                TBBlocks.SNOWDROP.getBlock(),
        };

        Arrays.stream(blocks).forEach((block) -> RenderTypeLookup.setRenderLayer(block, RenderType.getCutoutMipped()));

    }
}
