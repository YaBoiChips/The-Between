package frenchbread.thebetween.common.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class StoneGolemSpear extends AbstractStoneGolemEntity implements IRangedAttackMob {
    protected StoneGolemSpear(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {

    }
}
