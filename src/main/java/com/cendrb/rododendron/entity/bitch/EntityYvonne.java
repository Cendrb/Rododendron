package com.cendrb.rododendron.entity.bitch;

import com.cendrb.rododendron.entity.ai.EntityAIYvonneFuck;
import com.cendrb.rododendron.init.ModItems;
import com.cendrb.rododendron.utility.GenericHelpers;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.Random;

public class EntityYvonne extends EntityBitch {

	int fuse = 20;
	int timeAfterActivation = 0;
	boolean primed = false;
	boolean bombing = false;

	public EntityYvonne(World par1World) {
		super(par1World);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		tasks.addTask(2, new EntityAIYvonneFuck(this));
		tasks.addTask(3, new EntityAIWander(this, 1.0D));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		setSize(0.3F, 1.9F);
	}

	public void setPrimed(boolean bool) {
		primed = bool;
	}

	public boolean getPrimed() {
		return primed;
	}

	@Override
	protected void damageEntity(DamageSource par1DamageSource, float par2) {
		if (par1DamageSource.damageType == "fall" && bombing) {
			bombing = false;
			heal(par2);
		}
		super.damageEntity(par1DamageSource, par2);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(20.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.7);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0);
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
        super.dropFewItems(par1, par2);
		this.dropItem(ModItems.rightHand, 1);
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	public void onUpdate() {
		if (primed)
			timeAfterActivation += 1;
		if (timeAfterActivation > fuse) {
			explode();
		}
		super.onUpdate();
	}

	private void explode() {
		Random rand = new Random();

		float xDif = GenericHelpers.randomFloatInRange(0.5F, 1.5F);
		float yDif = GenericHelpers.randomFloatInRange(0.5F, 1.5F);

		if (rand.nextBoolean())
			xDif = -(xDif);
		if (rand.nextBoolean())
			yDif = -(yDif);
		
		//EntityLivingBase target = getAttackTarget();
		//double d0 = target.posX + target.motionX - this.posX;
		//double d1 = target.posY + (double) target.getEyeHeight() - 1.100000023841858D - this.posY;
		//double d2 = target.posZ + target.motionZ - this.posZ;
		//float f1 = MathHelper.sqrt_double(d0 * d0 + d2 * d2);

		motionX = xDif;
		motionY = GenericHelpers.randomFloatInRange(0.7F, 1.7F);
		motionZ = yDif;
		worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 5.0F, true);
		timeAfterActivation = 0;
		primed = false;
		bombing = true;
	}

}