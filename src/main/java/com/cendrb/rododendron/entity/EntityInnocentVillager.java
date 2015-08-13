package com.cendrb.rododendron.entity;

import com.cendrb.rododendron.utility.WorldHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.DamageSource;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenVillage;

/**
 * Created by cendr_000 on 13.08.2015.
 */
public class EntityInnocentVillager extends EntityVillager {

    int ticksPassed = 0;

    public EntityInnocentVillager(World p_i1595_1_) {
        super(p_i1595_1_);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if(worldObj.getClosestPlayer(posX, posY, posZ, 3) != null)
        {
            ticksPassed += worldObj.rand.nextInt(5);
        }
        else if(ticksPassed > 0)
        {
            ticksPassed -= worldObj.rand.nextInt(5);
        }

        if(ticksPassed > 300)
        {
            if(!worldObj.isRemote) {
                WorldHelper.createExplosion(worldObj, this, posX, posY, posZ, 5);
                setDead();
            }
        }
    }

    @Override
    protected void damageEntity(DamageSource source, float damage) {
        super.damageEntity(source, damage);
        if(source.isExplosion() || source.damageType.equals("fall"))
            heal(damage);
    }
}
