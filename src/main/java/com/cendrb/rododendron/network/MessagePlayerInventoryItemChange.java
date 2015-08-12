package com.cendrb.rododendron.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

/**
 * Created by cendr_000 on 8. 7. 2015.
 */
public class MessagePlayerInventoryItemChange implements IMessage {

    private ItemStack itemStack;
    private EntityPlayer player;
    private int slotId;

    public MessagePlayerInventoryItemChange()
    {

    }

    public MessagePlayerInventoryItemChange(ItemStack itemStack, EntityPlayer player, int slotId)
    {
        this.itemStack = itemStack;
        this.player = player;
        this.slotId = slotId;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        slotId = ByteBufUtils.readVarShort(buf);
        itemStack = ByteBufUtils.readItemStack(buf);
        player = MinecraftServer.getServer().getConfigurationManager().func_152612_a(ByteBufUtils.readUTF8String(buf));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeVarShort(buf, slotId);
        ByteBufUtils.writeItemStack(buf, itemStack);
        ByteBufUtils.writeUTF8String(buf, player.getDisplayName());
    }

    public static class Handler implements IMessageHandler<MessagePlayerInventoryItemChange, IMessage>
    {

        @Override
        public IMessage onMessage(MessagePlayerInventoryItemChange message, MessageContext ctx) {
            message.player.inventory.setInventorySlotContents(message.slotId, message.itemStack);
            return null; // no response
        }
    }
}
