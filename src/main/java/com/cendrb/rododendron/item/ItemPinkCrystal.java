package com.cendrb.rododendron.item;

import com.cendrb.rododendron.init.ModItems;
import com.cendrb.rododendron.item.charger.ItemChargeableItem;
import com.cendrb.rododendron.reference.Names;
import net.minecraft.item.ItemStack;

/**
 * Created by cendr_000 on 3. 7. 2015.
 */
public class ItemPinkCrystal extends ItemChargeableItem {

    public ItemPinkCrystal()
    {
        super();
        setUnlocalizedName(Names.Items.pinkCrystal);
    }

    @Override
    public int getChargeCost() {
        return 1000;
    }

    @Override
    public ItemStack getChargingResult() {
        return new ItemStack(ModItems.energizedCrystal);
    }
}
