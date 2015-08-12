package com.cendrb.rododendron.item.charger.chargeable_tools;

import com.cendrb.rododendron.item.charger.ItemChargeableTool;
import com.cendrb.rododendron.reference.Names;
import com.cendrb.rododendron.utility.WorldHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by cendr_000 on 7. 7. 2015.
 */
public class ItemPsimStaff extends ItemChargeableTool {

    public ItemPsimStaff()
    {
        super();
        setUnlocalizedName(Names.Items.psimStaff);
    }

    private void initTag(ItemStack itemStack)
    {
        itemStack.stackTagCompound = new NBTTagCompound();
        itemStack.stackTagCompound.setFloat("multiplier", 2.0F);
    }

    @Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
        initTag(itemStack);
    }

    @Override
    public int getItemDamagePerUse(ItemStack itemStack) {
        if(itemStack.stackTagCompound == null)
            initTag(itemStack);
        return (int)(Math.pow(1 + itemStack.stackTagCompound.getFloat("multiplier"), 2.5) * 2);
    }

    @Override
    public int getMaxItemDamage() {
        return 8000;
    }

    @Override
    protected boolean useTheFuckingItem(ItemStack itemStack, EntityPlayer player, World world) {
        if(itemStack.stackTagCompound == null)
            initTag(itemStack);
        WorldHelper.launchEntity(player, player, itemStack.stackTagCompound.getFloat("multiplier"));
        world.playSound(player.posX, player.posY, player.posZ, "rododendron:psim", 1.0F, 1.0F, true);
        return true;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean p_77624_4_) {
        if (itemStack.stackTagCompound != null)
        {
            list.add(String.format("Psim multiplier: %.1f (change with + and - keys)", itemStack.stackTagCompound.getFloat("multiplier")));
        }
        super.addInformation(itemStack, player, list, p_77624_4_);
    }
}
