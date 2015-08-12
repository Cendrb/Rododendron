package com.cendrb.rododendron.item.charger.chargeable_tools;

import com.cendrb.rododendron.item.charger.ItemChargeableTool;
import com.cendrb.rododendron.reference.Names;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cendr_000 on 8. 7. 2015.
 */
public class ItemBuildersStaff extends ItemChargeableTool {

    public ItemBuildersStaff() {
        super();
        setUnlocalizedName(Names.Items.buildersStaff);
    }

    int lastEnergyConsumption = -1;

    @Override
    public void onCreated(ItemStack itemStack, World p_77622_2_, EntityPlayer p_77622_3_) {
        initTag(itemStack);
    }

    @Override
    public int getItemDamagePerUse(ItemStack itemStack) {
        if (itemStack.stackTagCompound == null)
            initTag(itemStack);
        if (lastEnergyConsumption != -1) {
            int damage = lastEnergyConsumption;
            lastEnergyConsumption = -1;
            return damage;
        } else
            return 20 * itemStack.stackTagCompound.getInteger("range");
    }

    @Override
    public int getMaxItemDamage() {
        return 10000;
    }

    @Override
    protected boolean useTheFuckingItem(ItemStack itemStack, EntityPlayer player, World world) {
        if (itemStack.stackTagCompound == null) {
            initTag(itemStack);
        }
        if (!world.isRemote) {

            int x = MathHelper.floor_double(player.posX);
            int y = MathHelper.floor_double(player.posY - 0.2D - (double)player.yOffset);
            int z = MathHelper.floor_double(player.posZ);

            int sourceBlockSlot = player.inventory.currentItem + 27;
            ItemStack sourceBlockItem = player.inventory.getStackInSlot(sourceBlockSlot);
            if(sourceBlockItem == null || sourceBlockItem.stackSize == 0 || Block.getBlockFromItem(sourceBlockItem.getItem()) == null)
            {
                player.addChatComponentMessage(new ChatComponentText("Supplied item cannot be placed. Put your blocks in the slot above your tool."));
                return false;
            }
            Block sourceBlock = Block.getBlockFromItem(sourceBlockItem.getItem());

            int range = itemStack.stackTagCompound.getInteger("range");
            boolean vertical = itemStack.stackTagCompound.getBoolean("verticalAxis");

            // 0 +z; 1 -x; 2 -z; 3 +x; 4 -y; 5 +y
            int direction;
            if(vertical)
                direction = (player.rotationPitch > 0) ? 4 : 5;
            else
                direction = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
            ArrayList<ChunkCoordinates> coordinatesArrayList = new ArrayList<ChunkCoordinates>();
            for (int i = 1; i <= range; i++) {
                ChunkCoordinates coordinates;
                if (direction == 0)
                    coordinates = new ChunkCoordinates(x, y, z + i);
                else if (direction == 1)
                    coordinates = new ChunkCoordinates(x - i, y, z);
                else if (direction == 2)
                    coordinates = new ChunkCoordinates(x, y, z - i);
                else if(direction == 3)
                    coordinates = new ChunkCoordinates(x + i, y, z);
                else if(direction == 4)
                    coordinates = new ChunkCoordinates(x, y - i, z);
                else
                    coordinates = new ChunkCoordinates(x, y + (i + 3), z);

                Block targetBlock = world.getBlock(coordinates.posX, coordinates.posY, coordinates.posZ);
                if (targetBlock == Blocks.air || targetBlock == sourceBlock) {
                    if (player.inventory.consumeInventoryItem(sourceBlockItem.getItem()))
                        coordinatesArrayList.add(coordinates);
                    else {
                        player.addChatComponentMessage(new ChatComponentText("Structure incomplete. Not enough blocks."));
                        break;
                    }
                }
                else
                    break;

                lastEnergyConsumption = coordinatesArrayList.size() * 10;

                placeBlocks(world, coordinatesArrayList, sourceBlock, sourceBlockItem.getItemDamage());
            }
        }
        return true;
    }

    private void placeBlocks(World world, ArrayList<ChunkCoordinates> coordinatesArrayList, Block block, int metadata) {
        for (ChunkCoordinates coordinates : coordinatesArrayList) {
            world.setBlock(coordinates.posX, coordinates.posY, coordinates.posZ, block, metadata, 2);
        }
    }

    private void initTag(ItemStack itemStack) {
        itemStack.stackTagCompound = new NBTTagCompound();
        itemStack.stackTagCompound.setInteger("range", 10);
        itemStack.stackTagCompound.setBoolean("verticalAxis", false);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean p_77624_4_) {
        if (itemStack.stackTagCompound != null) {
            list.add(String.format("Range: %s (change with + and - keys)", itemStack.stackTagCompound.getInteger("range")));
            list.add(String.format("Vertical: %s (change with * key)", itemStack.stackTagCompound.getBoolean("verticalAxis")));
        }
        super.addInformation(itemStack, player, list, p_77624_4_);
    }
}
