package com.cendrb.rododendron.utility.objects;

import net.minecraft.item.ItemStack;

/**
 * Created by cendr_000 on 7. 7. 2015.
 */
public class ChargeableRecipeResult {

    ItemStack result;
    int chargeTime;

    public ChargeableRecipeResult(ItemStack itemStack, int chargeTime)
    {
        this.result = itemStack;
        this.chargeTime = chargeTime;
    }

    public ItemStack getResult() {
        return result;
    }

    public int getChargeTime() {
        return chargeTime;
    }
}
