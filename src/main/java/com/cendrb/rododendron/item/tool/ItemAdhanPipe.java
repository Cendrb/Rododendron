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
public class ItemAdhanPipe extends ItemChargeableTool {

    public ItemAdhanPipe()
    {
        super();
        setUnlocalizedName(Names.Items.adhanPipe);
    }

    @Override
    public int getItemDamagePerUse(ItemStack itemStack) {
        return 100;
    }

    @Override
    public int getMaxItemDamage() {
        return 1000;
    }



    @Override
    protected boolean useTheFuckingItem(ItemStack itemStack, EntityPlayer player, World world) {
        world.playSoundEffect(player.posX, player.posY, player.posZ, "rododendron:adhan", 64, 1);
        return true;
    }



}
