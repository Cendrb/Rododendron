package com.cendrb.rododendron.entity;

import com.cendrb.rododendron.utility.WorldHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.world.World;

/**
 * Created by cendr_000 on 4. 7. 2015.
 */
public class EntityAkbarCow extends EntityCow {

    World world;

    public EntityAkbarCow(World world) {
        super(world);
        this.world = world;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10000.0D);
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if(onGround && !worldObj.isRemote)
        {
            WorldHelper.createExplosion(world, this, posX, posY - 3, posZ, 15.0F);
            setDead();
        }
    }
}
