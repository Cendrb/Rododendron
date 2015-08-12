package com.cendrb.rododendron.item.charger.chargeable_tools;

import com.cendrb.rododendron.entity.EntityAkbarCow;
import com.cendrb.rododendron.init.ModBlocks;
import com.cendrb.rododendron.item.charger.ItemChargeableTool;
import com.cendrb.rododendron.reference.Names;
import com.cendrb.rododendron.utility.WorldHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by cendr_000 on 8. 7. 2015.
 */
public class ItemAkbarGun extends ItemChargeableTool {

    public ItemAkbarGun() {
        super();
        setUnlocalizedName(Names.Items.akbarGun);
    }

    @Override
    public void onCreated(ItemStack itemStack, World p_77622_2_, EntityPlayer p_77622_3_) {
        initTag(itemStack);
    }

    private void initTag(ItemStack itemStack) {
        itemStack.stackTagCompound = new NBTTagCompound();
        itemStack.stackTagCompound.setFloat("multiplier", 2.0F);
    }

    @Override
    public int getItemDamagePerUse(ItemStack itemStack) {
        return 50;
    }

    @Override
    public int getMaxItemDamage() {
        return 6000;
    }

    @Override
    protected boolean useTheFuckingItem(ItemStack itemStack, EntityPlayer player, World world) {
        if(itemStack.stackTagCompound == null)
            initTag(itemStack);

        if (player.inventory.hasItem(Item.getItemFromBlock(ModBlocks.akbar))) {
            player.inventory.consumeInventoryItem(Item.getItemFromBlock(ModBlocks.akbar));
            if(!world.isRemote) {
                EntityAkbarCow akbarCow = new EntityAkbarCow(world);
                akbarCow.setPosition(player.posX, player.posY, player.posZ);
                WorldHelper.launchEntity(player, akbarCow, itemStack.stackTagCompound.getFloat("multiplier"));
                world.spawnEntityInWorld(akbarCow);
            }
            return true;
        } else {
            if (!world.isRemote)
                player.addChatComponentMessage(new ChatComponentText("Out of ammo (Akbar block)"));
            return false;
        }
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean p_77624_4_) {
        if (itemStack.stackTagCompound != null)
        {
            list.add(String.format("Speed multiplier: %.1f (change with + and - keys)", itemStack.stackTagCompound.getFloat("multiplier")));
        }
        super.addInformation(itemStack, player, list, p_77624_4_);
    }
}
