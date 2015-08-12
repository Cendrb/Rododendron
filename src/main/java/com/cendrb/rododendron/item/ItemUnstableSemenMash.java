package com.cendrb.rododendron.item;

import com.cendrb.rododendron.reference.Names;
import com.cendrb.rododendron.utility.WorldHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Created by cendr_000 on 3. 7. 2015.
 */
public class ItemUnstableSemenMash extends ItemGeneric {

    int onGround = 0;

    public ItemUnstableSemenMash()
    {
        super();
        setUnlocalizedName(Names.Items.unstableSemenMash);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
        onGround = 0;
        return super.onDroppedByPlayer(item, player);
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem) {
        if(!entityItem.worldObj.isRemote)
        onGround++;
        if(onGround > 1200) {
            WorldHelper.createCustomExplosion(entityItem.worldObj, entityItem, entityItem.posX, entityItem.posY, entityItem.posZ, 40);
            entityItem.setDead();
        }
        return super.onEntityItemUpdate(entityItem);
    }
}
