package com.cendrb.rododendron.item.charger.chargeable_tools;

import com.cendrb.rododendron.item.charger.ItemChargeableTool;
import com.cendrb.rododendron.reference.Names;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by cendr_000 on 8. 7. 2015.
 */
public class ItemHomeSweetHome extends ItemChargeableTool {

    public ItemHomeSweetHome() {
        super();
        setUnlocalizedName(Names.Items.homeSweetHome);
    }

    @Override
    public int getItemDamagePerUse(ItemStack itemStack) {
        return 2000;
    }

    @Override
    public int getMaxItemDamage() {
        return 8000;
    }

    @Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
        itemStack.stackTagCompound = new NBTTagCompound();
        savePosition(itemStack, player);
    }

    @Override
    protected boolean useTheFuckingItem(ItemStack itemStack, EntityPlayer player, World world) {

            if (player.isSneaking()) {
                savePosition(itemStack, player);
                if (!world.isRemote)
                    player.addChatComponentMessage(new ChatComponentText("Position set"));
            } else if(itemStack.stackTagCompound != null) {
                int[] position = loadPosition(itemStack);
                player.setPosition(position[0], position[1], position[2]);
                player.playSound("rododendron:surprise", 1.0F, 1.0F);
            }
        if(itemStack.stackTagCompound == null)
            return false;
        return true;
    }

    private void savePosition(ItemStack itemStack, Entity entity) {
        if (itemStack.stackTagCompound == null)
            itemStack.stackTagCompound = new NBTTagCompound();
        itemStack.stackTagCompound.setIntArray("targetPosition", new int[]{(int) entity.posX, (int) entity.posY, (int) entity.posZ});
    }

    private int[] loadPosition(ItemStack itemStack) {
        return itemStack.stackTagCompound.getIntArray("targetPosition");
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean p_77624_4_) {
        if (itemStack.stackTagCompound != null) {
            int[] position = loadPosition(itemStack);
            list.add(String.format("Saved position: %sX %sY %sZ ", position[0], position[1], position[2]));
        }
        super.addInformation(itemStack, player, list, p_77624_4_);
    }
}
