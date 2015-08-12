package com.cendrb.rododendron.utility.objects;

import com.cendrb.rododendron.Rododendron;
import com.cendrb.rododendron.network.MessagePlayerInventoryItemChange;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;

/**
 * Created by cendr_000 on 9. 7. 2015.
 */
public class SwitchableItemStorageObject {

    private Item item;
    private String switchableCodeName;
    private String switchableFullName;
    private String switchableTrue;
    private String switchableFalse;

    public SwitchableItemStorageObject(Item item, String switchableCodeName, String switchableFullName, String switchableTrue, String switchableFalse) {
        this.item = item;
        this.switchableCodeName = switchableCodeName;
        this.switchableFullName = switchableFullName;
        this.switchableTrue = switchableTrue;
        this.switchableFalse = switchableFalse;
    }

    public void switchBy(EntityPlayer player)
    {
        ItemStack currentItemStack = player.inventory.getCurrentItem();
        boolean resultValue = !currentItemStack.stackTagCompound.getBoolean(switchableCodeName);
        currentItemStack.stackTagCompound.setBoolean(switchableCodeName, resultValue);
        player.addChatComponentMessage(new ChatComponentText(String.format("%s changed to %s", switchableFullName, resultValue ? switchableTrue : switchableFalse)));
        Rododendron.network.sendToServer(new MessagePlayerInventoryItemChange(currentItemStack, player, player.inventory.currentItem));
    }
}
