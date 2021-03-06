package com.cendrb.rododendron.slot;

import com.cendrb.rododendron.init.ChargerRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by cendr_000 on 6. 7. 2015.
 */
public class SlotChargerInput extends Slot {
    public SlotChargerInput(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
        super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
    }

    @Override
    public boolean isItemValid(ItemStack p_75214_1_) {
        return ChargerRecipes.canBeCharged(p_75214_1_);
    }
}
