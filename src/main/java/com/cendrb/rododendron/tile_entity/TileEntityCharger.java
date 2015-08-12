package com.cendrb.rododendron.tile_entity;

import com.cendrb.rododendron.block.BlockCharger;
import com.cendrb.rododendron.init.ChargerRecipes;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by cendr_000 on 6. 7. 2015.
 */
public class TileEntityCharger extends TileEntity implements IUpdatePlayerListBox, ISidedInventory {

    @Override
    public void update() {

    }

    public enum slotEnum {
        INPUT, FUEL, OUTPUT
    }

    private static final int[] slotsTop = new int[]{slotEnum.INPUT.ordinal()};
    private static final int[] slotsBottom = new int[]{slotEnum.OUTPUT.ordinal()};
    private static final int[] slotsSides = new int[]{slotEnum.FUEL.ordinal()};

    public int requiredItemElapsedEnergy;

    /**
     * The number of ticks that the furnace will keep burning
     */
    public int remainingBurnEnergy;

    /**
     * The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for
     */
    public int currentItemBurnEnergy;

    /**
     * The number of ticks that the current item has been cooking for
     */
    public int currentItemElapsedEnergy;

    public int chargerEnergy;

    public int chargerTier = 0;

    private final int chargerSpeed = 16;

    private final int chargerBaseEnergyAcceptRate = 2;

    private final int maxChargerEnergy = 20000;

    private ItemStack[] chargerItemstacks = new ItemStack[3];

    private String customName;

    public TileEntityCharger() {
        super();
    }

    @Override
    public boolean shouldRefresh(Block oldBlock, Block newBlock, int oldMeta, int newMeta, World world, int x, int y, int z) {
        return false;
    }

    @Override
    public int getSizeInventory() {
        return chargerItemstacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return chargerItemstacks[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int diff) {
        if (chargerItemstacks[index] != null) {
            ItemStack itemstack;

            if (chargerItemstacks[index].stackSize <= diff) {
                itemstack = chargerItemstacks[index];
                chargerItemstacks[index] = null;
                return itemstack;
            } else {
                itemstack = chargerItemstacks[index].splitStack(diff);

                if (chargerItemstacks[index].stackSize == 0) {
                    chargerItemstacks[index] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack itemStack) {
        boolean isSameItemStackAlreadyInSlot = itemStack != null && chargerItemstacks[index] != null
                && itemStack.isItemEqual(chargerItemstacks[index])
                && ItemStack.areItemStackTagsEqual(itemStack,
                chargerItemstacks[index]);
        chargerItemstacks[index] = itemStack;

        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
            itemStack.stackSize = getInventoryStackLimit();
        }

        // if input slot, reset the charging timers
        if (index == slotEnum.INPUT.ordinal()
                && !isSameItemStackAlreadyInSlot) {
            markDirty();
        }
    }

    @Override
    public String getInventoryName() {
        return hasCustomInventoryName() ? customName : "container.charger";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return customName != null && customName.length() > 0;
    }

    public void setCustomInventoryName(String name) {
        customName = name;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        NBTTagList nbtTagList = nbtTagCompound.getTagList("Items", 10);
        chargerItemstacks = new ItemStack[getSizeInventory()];

        for (int i = 0; i < nbtTagList.tagCount(); ++i) {
            NBTTagCompound compound = nbtTagList.getCompoundTagAt(i);
            byte b0 = compound.getByte("Slot");

            if (b0 >= 0 && b0 < chargerItemstacks.length) {
                chargerItemstacks[b0] = ItemStack.loadItemStackFromNBT(
                        compound);
            }
        }

        if (nbtTagCompound.hasKey("CustomName", 8)) {
            customName = nbtTagCompound.getString("CustomName");
        }

        chargerEnergy = nbtTagCompound.getInteger("ChargerEnergy");
        chargerTier = nbtTagCompound.getInteger("ChargerTier");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < chargerItemstacks.length; ++i) {
            if (chargerItemstacks[i] != null) {
                NBTTagCompound nbtTagCompound = new NBTTagCompound();
                nbtTagCompound.setByte("Slot", (byte) i);
                chargerItemstacks[i].writeToNBT(nbtTagCompound);
                nbttaglist.appendTag(nbtTagCompound);
            }
        }

        compound.setTag("Items", nbttaglist);

        if (hasCustomInventoryName()) {
            compound.setString("CustomName", customName);
        }

        compound.setInteger("ChargerEnergy", chargerEnergy);
        compound.setInteger("ChargerTier", chargerTier);
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public boolean receiveClientEvent(int p_145842_1_, int p_145842_2_) {
        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        if (slot == slotEnum.INPUT.ordinal())
            return ChargerRecipes.canBeCharged(itemStack);
        else if (slot == slotEnum.FUEL.ordinal())
            return ChargerRecipes.isChargerFuel(itemStack);
        else
            return false;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        ForgeDirection direction = ForgeDirection.getOrientation(side);
        if (direction == ForgeDirection.UP)
            return slotsTop;
        else if (direction == ForgeDirection.DOWN)
            return slotsBottom;
        else
            return slotsSides;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, int direction) {
        return isItemValidForSlot(slot, itemStack);
    }

    @Override
    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return true;
    }

    @Override
    public void updateEntity() {
        boolean burningInTheBeginning = isBurning();
        boolean itemsChanged = false;

        if (!worldObj.isRemote) {
            if (isBurning()) {
                // if burning
                // keep adding energy to internal buffer
                remainingBurnEnergy -= getEnergyAcceptRate();
                if(chargerEnergy + getEnergyAcceptRate() <= maxChargerEnergy)
                    chargerEnergy += getEnergyAcceptRate();
            }

            if (!isBurning() && canAcceptEnergy()) {
                // if not burning and energy buffer not full
                // start burning another fuel item
                currentItemBurnEnergy = remainingBurnEnergy = ChargerRecipes.getItemBurnTime(chargerItemstacks[slotEnum.FUEL.ordinal()]);

                if (isBurning()) {
                    // added fuel can produce energy
                    // consume item
                    itemsChanged = true;

                    if (chargerItemstacks[slotEnum.FUEL.ordinal()] != null) {
                        chargerItemstacks[slotEnum.FUEL.ordinal()].stackSize--;

                        if (chargerItemstacks[slotEnum.FUEL.ordinal()].stackSize == 0)
                            chargerItemstacks[slotEnum.FUEL.ordinal()] = chargerItemstacks[slotEnum.FUEL.ordinal()].getItem().getContainerItem(chargerItemstacks[slotEnum.FUEL.ordinal()]);
                    }
                }
            }

            if (hasEnergy(chargerSpeed) && canBeCharged()) {
                currentItemElapsedEnergy += chargerSpeed;
                chargerEnergy -= chargerSpeed;

                requiredItemElapsedEnergy = ChargerRecipes.getChargeTime(chargerItemstacks[slotEnum.INPUT.ordinal()]);

                if (currentItemElapsedEnergy >= requiredItemElapsedEnergy) {
                    currentItemElapsedEnergy = 0;
                    chargeItem();
                    itemsChanged = true;
                }
            } else
                currentItemElapsedEnergy = 0;

            if (burningInTheBeginning != isBurning()) {
                itemsChanged = true;
                BlockCharger.updateBurningState(isBurning(), worldObj, xCoord, yCoord, zCoord, this);
            }
        }
        if (itemsChanged) {
            markDirty();
        }
    }

    private int getEnergyAcceptRate() {
        return (int)Math.pow(chargerBaseEnergyAcceptRate, chargerTier);
    }

    private void chargeItem() {
        if (canBeCharged()) {
            ItemStack input = chargerItemstacks[slotEnum.INPUT.ordinal()];

            if (ChargerRecipes.canBeCharged(input)) {
                ItemStack result = ChargerRecipes.getChargingResult(input);
                if (chargerItemstacks[slotEnum.OUTPUT.ordinal()] == null)
                    chargerItemstacks[slotEnum.OUTPUT.ordinal()] = result.copy();
                else if (chargerItemstacks[slotEnum.OUTPUT.ordinal()].isItemEqual(result))
                    chargerItemstacks[slotEnum.OUTPUT.ordinal()].stackSize += result.stackSize;
            }
            input.stackSize--;

            if (input.stackSize <= 0) {
                chargerItemstacks[slotEnum.INPUT.ordinal()] = null;
            }

            //worldObj.playSoundEffect(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, "rododendron:gei", 1.0F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }
    }

    private boolean canAcceptEnergy()
    {
        int itemEnergy = ChargerRecipes.getItemBurnTime(chargerItemstacks[slotEnum.FUEL.ordinal()]);
        return chargerEnergy + itemEnergy <= maxChargerEnergy;
    }

    private boolean hasEnergy(int required)
    {
        return chargerEnergy - required > 0;
    }

    private boolean canBeCharged() {
        ItemStack input = chargerItemstacks[slotEnum.INPUT.ordinal()];
        if (input == null)
            return false;
        else {
            ItemStack result = ChargerRecipes.getChargingResult(input);
            if (result == null)
                return false;
            else if (chargerItemstacks[slotEnum.OUTPUT.ordinal()] == null)
                return true;
            else if (!chargerItemstacks[slotEnum.OUTPUT.ordinal()].isItemEqual(result))
                return false;

            int finalStackSize = chargerItemstacks[slotEnum.OUTPUT.ordinal()].stackSize + result.stackSize;

            return (finalStackSize <= getInventoryStackLimit() && finalStackSize <= result.getMaxStackSize());
        }
    }

    public boolean isBurning() {
        return remainingBurnEnergy > 0;
    }

    public int getChargerEnergyScaled(int i) {
        return chargerEnergy * i / maxChargerEnergy;
    }

    public int getChargeProgressScaled(int i) {
        if (requiredItemElapsedEnergy == 0)
            return 0;
        else
            return currentItemElapsedEnergy * i / requiredItemElapsedEnergy;
    }

    public String getChargerTier()
    {
        return String.format("T%s (%s SEO/t)", chargerTier, getEnergyAcceptRate());
    }

    public String getChargerEnergy() {
        return String.format("%s/%s SEU", String.valueOf(chargerEnergy), String.valueOf(maxChargerEnergy));
    }

    public boolean upgradeTier()
    {
        if(chargerTier >= 4)
            return false;
        else
        {
            chargerTier++;
            return true;
        }
    }
}
