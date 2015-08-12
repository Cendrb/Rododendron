package com.cendrb.rododendron.item.charger.chargeable_tools;

import com.cendrb.rododendron.init.ModBlocks;
import com.cendrb.rododendron.item.charger.ItemChargeableTool;
import com.cendrb.rododendron.reference.Names;
import com.cendrb.rododendron.utility.GenericHelpers;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cendr_000 on 8. 7. 2015.
 */
public class ItemScaffoldingBuilder extends ItemChargeableTool {

    public ItemScaffoldingBuilder() {
        super();
        setUnlocalizedName(Names.Items.scaffoldingBuilder);
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
        return 8000;
    }

    @Override
    protected boolean useTheFuckingItem(ItemStack itemStack, EntityPlayer player, World world) {
        if (itemStack.stackTagCompound == null) {
            initTag(itemStack);
        }

        if (!world.isRemote) {

            ChunkCoordinates baseCoordinates = GenericHelpers.getBlockBellowEntity(player);
            int x = baseCoordinates.posX;
            int y = baseCoordinates.posY;
            int z = baseCoordinates.posZ;


            int range = itemStack.stackTagCompound.getInteger("range");
            boolean vertical = itemStack.stackTagCompound.getBoolean("verticalAxis");

            // 0 +z; 1 -x; 2 -z; 3 +x; 4 -y; 5 +y
            int direction;
            if (vertical)
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
                else if (direction == 3)
                    coordinates = new ChunkCoordinates(x + i, y, z);
                else if (direction == 4)
                    coordinates = new ChunkCoordinates(x, y - (i - 1), z);
                else
                    coordinates = new ChunkCoordinates(x, y + (i + 3), z);

                Block targetBlock = world.getBlock(coordinates.posX, coordinates.posY, coordinates.posZ);
                if (targetBlock == Blocks.air || targetBlock == ModBlocks.scaffold)
                    coordinatesArrayList.add(coordinates);
                else
                    break;

            }

            lastEnergyConsumption = coordinatesArrayList.size() * 10;

            placeBlocks(world, coordinatesArrayList, itemStack);
        }
        return true;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack itemStack, EntityPlayer player, Entity entity) {
        if(itemStack.stackTagCompound == null)
            initTag(itemStack);
        World world = entity.worldObj;
        int[] xArray = itemStack.stackTagCompound.getIntArray("xBlocks");
        int[] yArray = itemStack.stackTagCompound.getIntArray("yBlocks");
        int[] zArray = itemStack.stackTagCompound.getIntArray("zBlocks");

        for(int i = 0; i < xArray.length; i++) {
            if(world.getBlock(xArray[i], yArray[i], zArray[i]) == ModBlocks.scaffold)
                world.setBlockToAir(xArray[i], yArray[i], zArray[i]);
        }

        itemStack.stackTagCompound.setIntArray("xBlocks", new int[]{});
        itemStack.stackTagCompound.setIntArray("yBlocks", new int[]{});
        itemStack.stackTagCompound.setIntArray("zBlocks", new int[]{});
        return false;
    }

    private void placeBlocks(World world, ArrayList<ChunkCoordinates> coordinatesArrayList, ItemStack itemStack) {
        int[] xArray = itemStack.stackTagCompound.getIntArray("xBlocks");
        int[] yArray = itemStack.stackTagCompound.getIntArray("yBlocks");
        int[] zArray = itemStack.stackTagCompound.getIntArray("zBlocks");

        int size = xArray.length + coordinatesArrayList.size();

        int[] xList = new int[size];
        int[] yList = new int[size];
        int[] zList = new int[size];

        int counter = 0;

        for (ChunkCoordinates coordinates : coordinatesArrayList) {
            world.setBlock(coordinates.posX, coordinates.posY, coordinates.posZ, ModBlocks.scaffold);
            xList[counter] = coordinates.posX;
            yList[counter] = coordinates.posY;
            zList[counter] = coordinates.posZ;
            counter++;
        }

        for (int i = 0; i < xArray.length; i++) {
            xList[i + counter] = xArray[i];
            yList[i + counter] = yArray[i];
            zList[i + counter] = zArray[i];
        }

        itemStack.stackTagCompound.setIntArray("xBlocks", xList);
        itemStack.stackTagCompound.setIntArray("yBlocks", yList);
        itemStack.stackTagCompound.setIntArray("zBlocks", zList);
    }

    private void initTag(ItemStack itemStack) {
        itemStack.stackTagCompound = new NBTTagCompound();
        itemStack.stackTagCompound.setIntArray("xBlocks", new int[]{});
        itemStack.stackTagCompound.setIntArray("yBlocks", new int[]{});
        itemStack.stackTagCompound.setIntArray("zBlocks", new int[]{});
        itemStack.stackTagCompound.setInteger("range", 10);
        itemStack.stackTagCompound.setBoolean("verticalAxis", false);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean p_77624_4_) {
        if (itemStack.stackTagCompound != null) {
            list.add(String.format("Blocks placed: %s (punch an entity to remove all)", itemStack.stackTagCompound.getIntArray("xBlocks").length));
            list.add(String.format("Range: %s (change with + and - keys)", itemStack.stackTagCompound.getInteger("range")));
            list.add(String.format("Vertical: %s (change with * key)", itemStack.stackTagCompound.getBoolean("verticalAxis")));
        }
        super.addInformation(itemStack, player, list, p_77624_4_);
    }
}
