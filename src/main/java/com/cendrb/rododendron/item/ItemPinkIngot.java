package com.cendrb.rododendron.item;

import com.cendrb.rododendron.init.ModItems;
import com.cendrb.rododendron.item.charger.ItemChargeableItem;
import com.cendrb.rododendron.reference.Names;
import com.cendrb.rododendron.utility.LogHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by cendr_000 on 6. 7. 2015.
 */
public class ItemPinkIngot extends ItemChargeableItem {

    public ItemPinkIngot()
    {
        super();
        setUnlocalizedName(Names.Items.pinkIngot);
    }

    @Override
    public int getChargeCost() {
        return 400;
    }

    @Override
    public ItemStack getChargingResult() {
        return new ItemStack(ModItems.energizedIngot);
    }
    
}
