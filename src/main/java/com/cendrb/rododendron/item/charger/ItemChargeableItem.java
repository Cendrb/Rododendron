package com.cendrb.rododendron.item.charger;

import com.cendrb.rododendron.init.ChargerRecipes;
import com.cendrb.rododendron.item.ItemGeneric;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by cendr_000 on 8. 7. 2015.
 */
public abstract class ItemChargeableItem extends ItemGeneric {

    public ItemChargeableItem() {
        ChargerRecipes.addRecipe(this, getChargingResult(), getChargeCost());
    }

    public abstract int getChargeCost();
    public abstract ItemStack getChargingResult();

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean p_77624_4_) {
        if(GuiScreen.isShiftKeyDown())
        {
            list.add("Chargeable");
            list.add(String.format("Cost: %s SEU", getChargeCost()));
            list.add(String.format("Result: %s", getChargingResult().getDisplayName()));
        }
        else
        {
            list.add("Press SHIFT for charging info");
        }
    }
}
