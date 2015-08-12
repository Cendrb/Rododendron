package com.cendrb.rododendron.item.tool;

import com.cendrb.rododendron.item.ItemGeneric;
import com.cendrb.rododendron.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
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
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {

        world.playSoundEffect(player.posX, player.posY, player.posZ, "rododendron:adhan", 64, 1);
        return itemStack;
    }
}
