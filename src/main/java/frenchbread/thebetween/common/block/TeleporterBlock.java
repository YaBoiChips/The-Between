package frenchbread.thebetween.common.block;

import frenchbread.thebetween.TheBetween;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;

public class TeleporterBlock extends Block {
    private final RegistryKey<World> worldKey;
    private final RegistryKey<World> overworldKey;


    public TeleporterBlock(Properties properties, RegistryKey<World> worldKey) {
        super(properties);
        this.worldKey = worldKey;
        this.overworldKey = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, DimensionType.OVERWORLD_ID);
    }


    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            ServerWorld world = worldIn.getServer().getWorld(worldKey);
            ServerWorld overworld = worldIn.getServer().getWorld(overworldKey);
            if (world != null) {
                if (player.getEntityWorld() != world) {
                    sendPlayerToDimension((ServerPlayerEntity) player, world, new Vector3d(player.getPosX(), player.getPosY(), player.getPosZ()));
                    player.sendStatusMessage(new TranslationTextComponent("thebetween.teleport.success"), true);
                    return ActionResultType.SUCCESS;
                }else{
                    if (overworld != null) {
                        sendPlayerToDimension((ServerPlayerEntity) player, overworld, new Vector3d(player.getPosX(), player.getPosY(), player.getPosZ()));
                    }
                }
            }
            else {
                player.sendStatusMessage(new TranslationTextComponent("thebetween.teleport.failed"), true);
                return ActionResultType.FAIL;
            }
        }

        return ActionResultType.PASS;
    }


    public static void sendPlayerToDimension(ServerPlayerEntity serverPlayer, ServerWorld targetWorld, Vector3d targetVec) {
        // ensure destination chunk is loaded before we put the player in it
        ServerWorld world = targetWorld.getWorld();
        targetWorld.getChunk(new BlockPos(targetVec));
            serverPlayer.teleport(targetWorld, targetVec.getX(), targetWorld.getHeight(Heightmap.Type.MOTION_BLOCKING, (int) targetVec.getX(), (int) targetVec.getZ()), targetVec.getZ(), serverPlayer.rotationYaw, serverPlayer.rotationPitch);
    }
}