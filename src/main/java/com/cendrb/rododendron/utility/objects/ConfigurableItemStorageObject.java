package com.cendrb.rododendron.utility.objects;

import com.cendrb.rododendron.Rododendron;
import com.cendrb.rododendron.network.MessagePlayerInventoryItemChange;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;

/**
 * Created by cendr_000 on 8. 7. 2015.
 */
public class ConfigurableItemStorageObject {

    private Item item;
    private String propertyCodeName;
    private String propertyFullName;
    float propertyStep;
    float propertyMin;
    float propertyMax;
    boolean useInteger;

    public ConfigurableItemStorageObject(Item item, String propertyCodeName, String propertyFullName, float propertyStep, float propertyMin, float propertyMax, boolean useInteger) {
        this.item = item;
        this.propertyCodeName = propertyCodeName;
        this.propertyFullName = propertyFullName;
        this.propertyStep = propertyStep;
        this.propertyMin = propertyMin;
        this.propertyMax = propertyMax;
        this.useInteger = useInteger;
    }

    public void increment(EntityPlayer player)
    {
        ItemStack currentItemStack = player.inventory.getCurrentItem();
        if(useInteger) {
            int currentValue = currentItemStack.stackTagCompound.getInteger(propertyCodeName);
            int resultValue = currentValue + (int)propertyStep;
            if (resultValue <= (int)propertyMax) {
                currentItemStack.stackTagCompound.setInteger(propertyCodeName, resultValue);
                player.addChatComponentMessage(new ChatComponentText(String.format("%s increased to %s", propertyFullName, resultValue)));
                Rododendron.network.sendToServer(new MessagePlayerInventoryItemChange(currentItemStack, player, player.inventory.currentItem));
            } else {
                player.addChatComponentMessage(new ChatComponentText(String.format("%s of this tool can't be higher than %s", propertyFullName, (int)propertyMax)));
            }
        }
        else
        {
            float currentValue = currentItemStack.stackTagCompound.getFloat(propertyCodeName);
            float resultValue = currentValue + propertyStep;
            if (resultValue <= propertyMax) {
                currentItemStack.stackTagCompound.setFloat(propertyCodeName, resultValue);
                player.addChatComponentMessage(new ChatComponentText(String.format("%s increased to %.1f", propertyFullName, resultValue)));
                Rododendron.network.sendToServer(new MessagePlayerInventoryItemChange(currentItemStack, player, player.inventory.currentItem));
            } else {
                player.addChatComponentMessage(new ChatComponentText(String.format("%s of this tool can't be higher than %.1f", propertyFullName, propertyMax)));
            }
        }

    }

    public void decrement(EntityPlayer player)
    {
        ItemStack currentItemStack = player.inventory.getCurrentItem();
        if(useInteger) {
            int currentValue = currentItemStack.stackTagCompound.getInteger(propertyCodeName);
            int resultValue = currentValue - (int) propertyStep;
            if (resultValue > (int) propertyMin) {
                currentItemStack.stackTagCompound.setInteger(propertyCodeName, resultValue);
                player.addChatComponentMessage(new ChatComponentText(String.format("%s decreased to %s", propertyFullName, resultValue)));
                Rododendron.network.sendToServer(new MessagePlayerInventoryItemChange(currentItemStack, player, player.inventory.currentItem));
            } else {
                player.addChatComponentMessage(new ChatComponentText(String.format("%s of this tool can't be lower than %s", propertyFullName, (int) propertyMin)));
            }
        }
        else
        {
            float currentValue = currentItemStack.stackTagCompound.getFloat(propertyCodeName);
            float resultValue = currentValue - propertyStep;
            if (resultValue > propertyMin) {
                currentItemStack.stackTagCompound.setFloat(propertyCodeName, resultValue);
                player.addChatComponentMessage(new ChatComponentText(String.format("%s decreased to %.1f", propertyFullName, resultValue)));
                Rododendron.network.sendToServer(new MessagePlayerInventoryItemChange(currentItemStack, player, player.inventory.currentItem));
            } else {
                player.addChatComponentMessage(new ChatComponentText(String.format("%s of this tool can't be lower than %.1f", propertyFullName, propertyMin)));
            }
        }
    }
}
