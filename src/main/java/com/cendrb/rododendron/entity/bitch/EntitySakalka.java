package com.cendrb.rododendron.entity.bitch;

import com.cendrb.rododendron.utility.GenericHelpers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntitySakalka extends EntityBitch
{
    int summonStepanek = 0;
    public EntitySakalka(World world) {
        super(world);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        tasks.addTask(2, new EntityAIWander(this, 1.0D));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        setSize(0.3F, 1.9F);
    }

    @Override
    protected void attackEntity(Entity target, float distance) {
        summonStepanek++;
        if(summonStepanek > 50)
        {
            summonStepanek = 0;
            tryFireStepanekAt(target, distance);
        }
    }

    private void tryFireStepanekAt(Entity target, float distance)
    {
        EntityStepanek stepanek = new EntityStepanek(worldObj);
        stepanek.copyLocationAndAnglesFrom(this);
        GenericHelpers.changeVelocityToTarget(target.posX, target.posY, target.posZ, stepanek, posX, posY, posZ, distance);
        worldObj.spawnEntityInWorld(stepanek);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20);
        getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(20.0);
        getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.7);
        getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0);
    }
}
