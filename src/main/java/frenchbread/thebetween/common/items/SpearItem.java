package frenchbread.thebetween.common.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import frenchbread.thebetween.common.entities.AbstractSpearEntity;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SpearItem extends Item implements IVanishable {
    private final Multimap<Attribute, AttributeModifier> tridentAttributes;

    public SpearItem(Item.Properties properties) {
        super(properties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", 9.0D, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", (double)-2.9F, AttributeModifier.Operation.ADDITION));
        this.tridentAttributes = builder.build();
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return !player.isCreative();
    }

    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
        public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
            if (entityLiving instanceof PlayerEntity) {
                PlayerEntity playerentity = (PlayerEntity)entityLiving;
                int i = this.getUseDuration(stack) - timeLeft;
                if (i >= 10) {
                    int j = EnchantmentHelper.getRiptideModifier(stack);
                    if (j <= 0 || playerentity.isWet()) {
                        if (!worldIn.isRemote) {
                            stack.damageItem(1, playerentity, (player) -> {
                                player.sendBreakAnimation(entityLiving.getActiveHand());
                            });
                            if (j == 0) {
                                AbstractSpearEntity tridententity = new AbstractSpearEntity(worldIn, playerentity, stack);
                                tridententity.setDirectionAndMovement(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, 2.5F + (float) j * 0.5F, 1.0F);
                                tridententity.pickupStatus = AbstractArrowEntity.PickupStatus.ALLOWED;
                                worldIn.addEntity(tridententity);
                                worldIn.playMovingSound((PlayerEntity) null, tridententity, SoundEvents.ITEM_TRIDENT_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);
                                if (!playerentity.abilities.isCreativeMode) {
                                    playerentity.inventory.deleteStack(stack);
                                }
                            }
                        }

                        playerentity.addStat(Stats.ITEM_USED.get(this));
                    }
                }
            }
        }


    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (itemstack.getDamage() >= itemstack.getMaxDamage() - 1) {
            return ActionResult.resultFail(itemstack);
        } else {
            playerIn.setActiveHand(handIn);
            return ActionResult.resultConsume(itemstack);
        }
    }

    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damageItem(1, attacker, (entity) -> {
            entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
        });
        return true;
    }

    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        if ((double)state.getBlockHardness(worldIn, pos) != 0.0D) {
            stack.damageItem(2, entityLiving, (entity) -> {
                entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
            });
        }

        return true;
    }


    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
        return equipmentSlot == EquipmentSlotType.MAINHAND ? this.tridentAttributes : super.getAttributeModifiers(equipmentSlot);
    }

    public int getItemEnchantability() {
        return 1;
    }
}

