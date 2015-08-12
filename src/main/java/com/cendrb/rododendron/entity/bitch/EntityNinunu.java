package com.cendrb.rododendron.entity.bitch;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

public class EntityNinunu extends EntityBitch {

	double buffOffset = 6;
	int splash = 0;
	final int max = 15;

	public EntityNinunu(World par1World) {
		super(par1World);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		tasks.addTask(3, new EntityAIWander(this, 1.0D));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		setSize(0.6F, 1.8F);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.9D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(256.0D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.3D);
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
        super.dropFewItems(par1, par2);
		this.dropItem(Item.getItemFromBlock(Blocks.gravel), 4096);
	}

	@Override
	public void onLivingUpdate() {
		splash++;
		if (splash >= max) {
			PotionEffect blindness = new PotionEffect(15, 200, 0, true);
			PotionEffect slowness = new PotionEffect(2, 200, 2, true);
			PotionEffect weakness = new PotionEffect(18, 200, 7, true);
			double x2 = posX + buffOffset;
			double y2 = posY + buffOffset;
			double z2 = posZ + buffOffset;
			double x1 = posX - buffOffset;
			double y1 = posY - buffOffset;
			double z1 = posZ - buffOffset;
			AxisAlignedBB boundingBox = AxisAlignedBB.getBoundingBox(x1, y1, z1, x2, y2, z2);
			List<Entity> entities = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox);
			for(Entity entity: entities) {
				if (entity instanceof EntityPlayer) {
					((EntityPlayer) entity).addPotionEffect(blindness);
					((EntityPlayer) entity).addPotionEffect(slowness);
					((EntityPlayer) entity).addPotionEffect(weakness);
				}
			}
			splash = 0;
		}
		super.onLivingUpdate();
	}
}
