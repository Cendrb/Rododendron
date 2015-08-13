package com.cendrb.rododendron.entity;

import com.cendrb.rododendron.init.ModBlocks;
import com.cendrb.rododendron.init.ModItems;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Created by cendr_000 on 6. 7. 2015.
 */

public class EntityDun extends EntityAnimal {

    int timeUntilNextThing = rand.nextInt(6000) + 6000;
    int jesLimiter = 0;
    int rototo = -1;

    boolean doTheThing;
    boolean currentlyRototo;

    public EntityDun(World p_i1681_1_) {
        super(p_i1681_1_);
        setSize(0.4F, 1.9F);

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.0D, ModItems.dunPie, false));
        this.tasks.addTask(7, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));


    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30D);
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.22D);
    }

    @Override
    public boolean interact(EntityPlayer player) {
        if (!worldObj.isRemote && player.inventory.getCurrentItem() == null && jesLimiter < 6000) {
            playSound("rododendron:dun/jes", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            timeUntilNextThing -= 240;
            rototo += 20;
            return true;
        } else
            return super.interact(player);
    }

    @Override
    public boolean isBreedingItem(ItemStack itemStack) {
        return itemStack.getItem() == ModItems.dunPie;
    }

    @Override
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
        dropItem(Item.getItemFromBlock(ModBlocks.gayHead), 1);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        DataWatcher dataWatcher = getDataWatcher();
        dataWatcher.addObject(20, (byte)0);
        dataWatcher.addObject(21, (byte)0);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!worldObj.isRemote) {
            jesLimiter--;

            if (rototo > -1) {
                rototo--;

                if(!currentlyRototo)
                {
                    currentlyRototo = true;
                    dataWatcher.updateObject(21, (byte)1);
                }
            }
            else
            {
                if(currentlyRototo)
                {
                    currentlyRototo = false;
                    dataWatcher.updateObject(21, (byte)0);
                }
            }

            if(doTheThing)
            {
                doTheThing = false;
                dataWatcher.updateObject(20, (byte)0);
            }

            if (--timeUntilNextThing < 0) {
                dropItem(ModItems.gaySemen, 1);
                playSound("rododendron:dun/pop", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                jesLimiter += 3000;
                timeUntilNextThing = rand.nextInt(6000) + 6000;

                doTheThing = true;
                dataWatcher.updateObject(20, (byte)1);
            }
        }
        else
        {
            if(dataWatcher.getWatchableObjectByte(20) == 1)
                worldObj.spawnParticle("hugeexplosion", posX, posY + 1, posZ, 1.0D, 0.0D, 0.0D);
            if(dataWatcher.getWatchableObjectByte(21) == 1)
                worldObj.spawnParticle("cloud", posX, posY + 1, posZ, 0.0D, 1.0D, 0.0D);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        timeUntilNextThing = nbtTagCompound.getInteger("nextThing");
        jesLimiter = nbtTagCompound.getInteger("jesLimiter");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setInteger("nextThing", timeUntilNextThing);
        nbtTagCompound.setInteger("jesLimiter", jesLimiter);
    }

    @Override
    protected boolean isAIEnabled() {
        return true;
    }

    @Override
    public EntityAgeable createChild(EntityAgeable p_90011_1_) {
        return new EntityDun(worldObj);
    }

    @Override
    protected float getSoundVolume() {
        return 0.7F;
    }

    @Override
    protected String getHurtSound() {
        return "rododendron:dun/hurt";
    }

    @Override
    protected String getDeathSound() {
        return "rododendron:dun/death";
    }

    @Override
    protected String getLivingSound() {
        return "rododendron:dun/say";
    }

    public int getRototo() {
        return rototo;
    }
}
