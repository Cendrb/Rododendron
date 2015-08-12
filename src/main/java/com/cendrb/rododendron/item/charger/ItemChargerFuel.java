package com.cendrb.rododendron.item.charger;

import com.cendrb.rododendron.init.ChargerRecipes;
import com.cendrb.rododendron.item.ItemGeneric;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by cendr_000 on 7. 7. 2015.
 */
public abstract class ItemChargerFuel extends ItemGeneric {

    public ItemChargerFuel()
    {
        ChargerRecipes.addChargerFuel(this);
    }

    public abstract int getChargerFuelValue();

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean p_77624_4_) {
        if(GuiScreen.isShiftKeyDown())
        {
            list.add("Fuel for charger");
            list.add(String.format("Charger fuel value: %s SEU", getChargerFuelValue()));
        }
        else
        {
            list.add("Press SHIFT for charging info");
        }
    }
}
