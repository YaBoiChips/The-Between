package frenchbread.thebetween.common.entities;

import frenchbread.thebetween.core.TBItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class StoneGolemSpear extends AbstractStoneGolemEntity implements IRangedAttackMob {

    public StoneGolemSpear(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(2, new SpearAttackGoal(this, 1.0D, 40, 10.0F));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));

    }
    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        AbstractSpearEntity spearentity = new AbstractSpearEntity(this.world, this, new ItemStack(TBItems.SPEAR));
        double d0 = target.getPosX() - this.getPosX();
        double d1 = target.getPosYHeight(0.3333333333333333D) - spearentity.getPosY();
        double d2 = target.getPosZ() - this.getPosZ();
        double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
        spearentity.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(14 - this.world.getDifficulty().getId() * 4));
        this.playSound(SoundEvents.ENTITY_DROWNED_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.addEntity(spearentity);
    }

    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        super.setEquipmentBasedOnDifficulty(difficulty);
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(TBItems.SPEAR));
    }

    @Nullable
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        this.setEquipmentBasedOnDifficulty(difficultyIn);
        this.setEnchantmentBasedOnDifficulty(difficultyIn);
        return spawnDataIn;
    }

    static class SpearAttackGoal extends RangedAttackGoal {
        private final StoneGolemSpear entity;

        public SpearAttackGoal(IRangedAttackMob creature, double p_i48907_2_, int p_i48907_4_, float p_i48907_5_) {
            super(creature, p_i48907_2_, p_i48907_4_, p_i48907_5_);
            this.entity = (StoneGolemSpear) creature;
        }

        public boolean shouldExecute() {
            return super.shouldExecute() && this.entity.getHeldItemMainhand().getItem() == TBItems.SPEAR;
        }

        public void startExecuting() {
            super.startExecuting();
            this.entity.setAggroed(true);
            this.entity.setActiveHand(Hand.MAIN_HAND);
        }

        public void resetTask() {
            super.resetTask();
            this.entity.resetActiveHand();
            this.entity.setAggroed(false);
        }
    }
}
