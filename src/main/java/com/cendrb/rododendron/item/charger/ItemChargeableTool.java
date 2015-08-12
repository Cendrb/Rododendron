package com.cendrb.rododendron.item.charger;

import com.cendrb.rododendron.init.ChargerRecipes;
import com.cendrb.rododendron.item.ItemGeneric;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by cendr_000 on 7. 7. 2015.
 */
public abstract class ItemChargeableTool extends ItemGeneric {

    public ItemChargeableTool()
    {
        super();
        ChargerRecipes.addChargeableTool(this);

        setNoRepair();
        setMaxStackSize(1);
        setMaxDamage(getMaxItemDamage());
    }

    public abstract int getItemDamagePerUse(ItemStack itemStack);
    public abstract int getMaxItemDamage();

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if(itemStack.getItemDamage() + getItemDamagePerUse(itemStack) < getMaxItemDamage()) {
            boolean damage = useTheFuckingItem(itemStack, player, world);
            if(!world.isRemote && damage)
                itemStack.damageItem(getItemDamagePerUse(itemStack), player);
            return itemStack;

        }
        return itemStack;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean p_77624_4_) {
        if(GuiScreen.isShiftKeyDown())
        {
            list.add("Rechargeable tool");
            list.add(String.format("Energy: %s / %s SEU", getMaxItemDamage() - itemStack.getItemDamage(), getMaxItemDamage()));
            list.add(String.format("Power usage: %s SEU", getItemDamagePerUse(itemStack)));
        }
        else
        {
            list.add("Press SHIFT for charging info");
        }

    }

    protected abstract boolean useTheFuckingItem(ItemStack itemStack, EntityPlayer player, World world);
}
