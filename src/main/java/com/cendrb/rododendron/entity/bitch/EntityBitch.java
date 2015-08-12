package com.cendrb.rododendron.entity.bitch;

import com.cendrb.rododendron.entity.EntityAkbarCow;
import com.cendrb.rododendron.init.ModItems;
import com.cendrb.rododendron.utility.WorldHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityBitch extends EntityMob {

    public EntityBitch(World par1World) {
        super(par1World);
        isImmuneToFire = true;
    }

    @Override
    protected Item getDropItem() {
        return ModItems.vibrator;
    }

    @Override
    protected void dropFewItems(boolean p_70628_1_, int lootingLevel) {
        int j = this.rand.nextInt(3 + lootingLevel);
        int k;

        for (k = 0; k < j; ++k) {
            this.dropItem(getDropItem(), 1);
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(139.0);
    }

    @Override
    public void onDeath(DamageSource p_70645_1_) {
        super.onDeath(p_70645_1_);
        if(!worldObj.isRemote) {
            WorldHelper.spawnFlyingEntities(worldObj, 500, EntityAkbarCow.class, posX, posY, posZ);
        }
    }
}
