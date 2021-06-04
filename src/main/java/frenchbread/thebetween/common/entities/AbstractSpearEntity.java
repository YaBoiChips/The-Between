package frenchbread.thebetween.common.entities;

import frenchbread.thebetween.core.TBEntities;
import frenchbread.thebetween.core.TBItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AbstractSpearEntity extends AbstractArrowEntity {
    private boolean dealtDamage;
    private final ItemStack thrownStack = new ItemStack(TBItems.SPEAR);


    public AbstractSpearEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }
    public AbstractSpearEntity(World worldIn, LivingEntity thrower, ItemStack thrownStackIn) {
        super(TBEntities.SPEAR, thrower, worldIn);
    }

    protected ItemStack getArrowStack() {
        return this.thrownStack.copy();
    }


    @Nullable
    protected EntityRayTraceResult rayTraceEntities(Vector3d startVec, Vector3d endVec) {
        return this.dealtDamage ? null : super.rayTraceEntities(startVec, endVec);
    }

    @Override
    @Nonnull
    public IPacket createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    protected void onEntityHit(EntityRayTraceResult result) {
            Entity entity = result.getEntity();
            float f = 8.0F;
            if (entity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity) entity;
                f += EnchantmentHelper.getModifierForCreature(this.thrownStack, livingentity.getCreatureAttribute());
            }

            Entity entity1 = this.getShooter();
            DamageSource damagesource = DamageSource.causeTridentDamage(this, (Entity) (entity1 == null ? this : entity1));
            this.dealtDamage = true;
            SoundEvent soundevent = SoundEvents.ENTITY_ARROW_HIT;
            if (entity.attackEntityFrom(damagesource, f)) {
                if (entity.getType() == EntityType.ENDERMAN) {
                    return;
                }

                if (entity instanceof LivingEntity) {
                    LivingEntity livingentity1 = (LivingEntity) entity;
                    if (entity1 instanceof LivingEntity) {
                        EnchantmentHelper.applyThornEnchantments(livingentity1, entity1);
                        EnchantmentHelper.applyArthropodEnchantments((LivingEntity) entity1, livingentity1);
                    }

                    this.arrowHit(livingentity1);
                }
            }
        this.playSound(soundevent, 1.0F, 1.0F);
        }
}
