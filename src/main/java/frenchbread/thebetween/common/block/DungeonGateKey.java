package frenchbread.thebetween.common.block;

import frenchbread.thebetween.core.TBBlocks;
import frenchbread.thebetween.core.TBItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class DungeonGateKey extends Block {
    public DungeonGateKey(Properties properties) {
        super(properties);
    }




    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        List<BlockPos> posNextTo = new ArrayList<>();
        posNextTo.add(pos.up());
        posNextTo.add(pos.down());
        posNextTo.add(pos.east());
        posNextTo.add(pos.west());
        posNextTo.add(pos.north());
        posNextTo.add(pos.south());
        posNextTo.add(pos);
        posNextTo.add(pos.east().up());
        posNextTo.add(pos.west().up());
        posNextTo.add(pos.north().up());
        posNextTo.add(pos.south().up());
        posNextTo.add(pos.east().down());
        posNextTo.add(pos.west().down());
        posNextTo.add(pos.north().down());
        posNextTo.add(pos.south().down());
        if (player.getHeldItemMainhand().getItem() == TBItems.DUNGEON_KEY_1) {
            for (BlockPos pos2 : posNextTo) {
                if (worldIn.getBlockState(pos2) == TBBlocks.DUNGEON_GATE_FRAME.getDefaultState()) {
                    worldIn.setBlockState(pos2, Blocks.AIR.getDefaultState());
                }
            }
            worldIn.setBlockState(pos,Blocks.AIR.getDefaultState());
                if (!player.isCreative()) {
                    player.getHeldItemMainhand().shrink(1);
            }
        }
       return ActionResultType.SUCCESS;
    }
}
