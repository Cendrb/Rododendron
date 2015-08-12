package com.cendrb.rododendron.item.tool;

import com.cendrb.rododendron.Rododendron;
import com.cendrb.rododendron.item.ItemGeneric;
import com.cendrb.rododendron.item.charger.ItemChargeableTool;
import com.cendrb.rododendron.network.MessagePlayerInventoryItemChange;
import com.cendrb.rododendron.reference.Names;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Honza on 12. 8. 2015.
 */
public class ItemAdhanPipe extends ItemGeneric {

    public ItemAdhanPipe()
    {
        super();
        setUnlocalizedName(Names.Items.adhanPipe);
        setNoRepair();
        setMaxDamage(400);
        setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {

        if (!world.isRemote && item.getItemDamage() == 0)
        {
            item.damageItem(399, player);
            world.playSoundEffect(player.posX, player.posY, player.posZ, "rododendron:adhan", 64, 1);
        }
        return item;
    }

    @Override
    public void onUpdate(ItemStack item, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {

        if (!world.isRemote && item.getItemDamage() > 0)
        {
            item.setItemDamage(item.getItemDamage() - 1);
        }


        super.onUpdate(item, world, entity, p_77663_4_, p_77663_5_);
    }
}
