package com.cendrb.rododendron.entity;

import com.cendrb.rododendron.init.ModItems;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by cendr_000 on 6. 7. 2015.
 */
public class EntityDildo extends EntityMob {
    public EntityDildo(World p_i1738_1_) {
        super(p_i1738_1_);
        setSize(1, 2);

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40);
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3);
        getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8);
        getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32);
    }

    @Override
    protected int getExperiencePoints(EntityPlayer p_70693_1_) {
        return 8;
    }

    @Override
    protected void dropFewItems(boolean killedByPlayer, int looting) {
        dropItem(ModItems.pinkDust, rand.nextInt(4 + looting));

        if (rand.nextInt(4) == 0)
            dropItem(ModItems.pinkIngot, 1);

    }

    @Override
    protected boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void dropRareDrop(int looting) {
        dropItem(ModItems.pinkCrystal, 1 + looting);
    }

    @Override
    protected float getSoundVolume() {
        return 0.7F;
    }

    @Override
    protected String getHurtSound() {
        return "rododendron:dildo/hurt";
    }

    @Override
    protected String getDeathSound() {
        return "rododendron:dildo/death";
    }

    @Override
    protected String getLivingSound() {
        return "rododendron:dildo/say";
    }
}
