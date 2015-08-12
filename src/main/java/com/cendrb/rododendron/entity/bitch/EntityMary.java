package com.cendrb.rododendron.entity.bitch;

import com.cendrb.rododendron.entity.EntityAkbarCow;
import com.cendrb.rododendron.init.ModItems;
import com.cendrb.rododendron.utility.WorldHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityMary extends EntityBitch {

    int fireFart = 0;
    int largeFart = 0;
    final int FART_FARTS_COUNT = 1;

	public EntityMary(World par1World) {
		super(par1World);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		tasks.addTask(3, new EntityAIWander(this, 1.0D));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		setSize(0.3F, 1.9F);
	}

    @Override
    protected void dropFewItems(boolean par1, int par2) {
        super.dropFewItems(par1, par2);
        this.dropItem(ModItems.uselessWater, 1);
    }

    @Override
    public void onDeath(DamageSource p_70645_1_) {
        worldObj.setBlock((int)posX, (int)posY, (int)posZ, Blocks.flowing_water);
        super.onDeath(p_70645_1_);
    }

    @Override
    protected void attackEntity(final Entity target, final float distance) {
        fireFart++;
        largeFart++;

        if (fireFart > 69) {
            fireFart = 0;
            fartAt();
        }

        if (largeFart > 111)
        {
            largeFart = 0;
            largeFartAt(target, distance);
        }

    }

    private void largeFartAt(Entity target, float distance)
    {
        double d0 = target.posX - this.posX;
        double d1 = target.boundingBox.minY + (double) (target.height / 2.0F) - (this.posY + (double) (this.height / 2.0F));
        double d2 = target.posZ - this.posZ;

        float f1 = MathHelper.sqrt_float(distance) * 0.5F;

        EntityLargeFireball fireball1 = new EntityLargeFireball(worldObj, this, d0 + this.rand.nextGaussian() * (double) f1, d1, d2 + this.rand.nextGaussian() * (double) f1);
        fireball1.posY += 1;
        worldObj.spawnEntityInWorld(fireball1);
    }

    private void fartAt()
    {
        if(!worldObj.isRemote)
        {
            WorldHelper.spawnFlyingEntities(worldObj, FART_FARTS_COUNT, EntityAkbarCow.class, posX, posY, posZ);
        }
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
