package com.cendrb.rododendron.container;

import com.cendrb.rododendron.init.ChargerRecipes;
import com.cendrb.rododendron.slot.SlotChargerFuel;
import com.cendrb.rododendron.slot.SlotChargerInput;
import com.cendrb.rododendron.slot.SlotChargerOutput;
import com.cendrb.rododendron.tile_entity.TileEntityCharger;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by cendr_000 on 6. 7. 2015.
 */

public class ContainerCharger extends Container {
    private final TileEntityCharger chargerTileEntity;
    private final int inventorySize;

    public int lastRemainingBurnEnergy;
    public int lastCurrentItemBurnEnergy;
    public int lastCurrentItemElapsedEnergy;
    public int lastRequiredItemElapsedEnergy;
    public int lastChargerEnergy;
    public int lastChargerTier;

    public ContainerCharger(InventoryPlayer inventoryPlayer, IInventory chargerTileEntity) {
        super();
        this.chargerTileEntity = (TileEntityCharger) chargerTileEntity;
        this.inventorySize = chargerTileEntity.getSizeInventory();

        addSlotToContainer(new SlotChargerInput(chargerTileEntity, TileEntityCharger.slotEnum.INPUT.ordinal(), 56, 35));
        addSlotToContainer(new SlotChargerFuel(chargerTileEntity, TileEntityCharger.slotEnum.FUEL.ordinal(), 8, 62));
        addSlotToContainer(new SlotChargerOutput(chargerTileEntity, TileEntityCharger.slotEnum.OUTPUT.ordinal(), 116, 35));

        // add player inventory slots
        // note that the slot numbers are within the player inventory so can
        // be same as the tile entity inventory
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
                        8 + j * 18, 84 + i * 18));
            }
        }

        // add hotbar slots
        for (i = 0; i < 9; ++i) {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18,
                    142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return chargerTileEntity.isUseableByPlayer(player);
    }

    @Override
    public void addCraftingToCrafters(ICrafting iCrafting) {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, chargerTileEntity.currentItemElapsedEnergy);
        iCrafting.sendProgressBarUpdate(this, 1, chargerTileEntity.currentItemBurnEnergy);
        iCrafting.sendProgressBarUpdate(this, 2, chargerTileEntity.remainingBurnEnergy);
        iCrafting.sendProgressBarUpdate(this, 3, chargerTileEntity.requiredItemElapsedEnergy);
        iCrafting.sendProgressBarUpdate(this, 4, chargerTileEntity.chargerEnergy);
        iCrafting.sendProgressBarUpdate(this, 5, chargerTileEntity.chargerTier);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < crafters.size(); i++) {
            ICrafting iCrafting = (ICrafting) crafters.get(i);

            if (lastCurrentItemElapsedEnergy != chargerTileEntity.currentItemElapsedEnergy)
                iCrafting.sendProgressBarUpdate(this, 0, chargerTileEntity.currentItemElapsedEnergy);

            if (lastCurrentItemBurnEnergy != chargerTileEntity.currentItemBurnEnergy)
                iCrafting.sendProgressBarUpdate(this, 1, chargerTileEntity.currentItemBurnEnergy);

            if (lastRemainingBurnEnergy != chargerTileEntity.remainingBurnEnergy)
                iCrafting.sendProgressBarUpdate(this, 2, chargerTileEntity.remainingBurnEnergy);

            if (lastRequiredItemElapsedEnergy != chargerTileEntity.requiredItemElapsedEnergy)
                iCrafting.sendProgressBarUpdate(this, 3, chargerTileEntity.requiredItemElapsedEnergy);

            if(lastChargerEnergy != chargerTileEntity.chargerEnergy)
                iCrafting.sendProgressBarUpdate(this, 4, chargerTileEntity.chargerEnergy);

            if(lastChargerTier != chargerTileEntity.chargerTier)
                iCrafting.sendProgressBarUpdate(this, 5, chargerTileEntity.chargerTier);
        }

        lastCurrentItemElapsedEnergy = chargerTileEntity.currentItemElapsedEnergy;
        lastCurrentItemBurnEnergy = chargerTileEntity.currentItemBurnEnergy;
        lastRemainingBurnEnergy = chargerTileEntity.remainingBurnEnergy;
        lastRequiredItemElapsedEnergy = chargerTileEntity.requiredItemElapsedEnergy;
        lastChargerEnergy = chargerTileEntity.chargerEnergy;
        lastChargerTier = chargerTileEntity.chargerTier;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value) {
        super.updateProgressBar(id, value);

        switch (id) {
            case 0:
                chargerTileEntity.currentItemElapsedEnergy = value;
                break;
            case 1:
                chargerTileEntity.currentItemBurnEnergy = value;
                break;
            case 2:
                chargerTileEntity.remainingBurnEnergy = value;
                break;
            case 3:
                chargerTileEntity.requiredItemElapsedEnergy = value;
                break;
            case 4:
                chargerTileEntity.chargerEnergy = value;
                break;
            case 5:
                chargerTileEntity.chargerTier = value;
                break;
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack itemStack = null;
        Slot slot = (Slot) inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack1 = slot.getStack();
            itemStack = itemStack1.copy();

            if (index == 2) {
                if (!mergeItemStack(itemStack1, 3, 39, true))
                    return null;

                slot.onSlotChange(itemStack1, itemStack);

            } else if (index != 1 && index != 0) {
                if (ChargerRecipes.canBeCharged(itemStack1))
                {
                    if(!mergeItemStack(itemStack1, 0, 1, false))
                        return null;
                }
                else if (ChargerRecipes.isChargerFuel(itemStack1))
                {
                    if(!mergeItemStack(itemStack1, 1, 2, false))
                        return null;
                }
                else if(index >= 3 && index < 30)
                {
                    if(!mergeItemStack(itemStack1, 30, 39, false))
                        return null;
                }
                else if (index >= 30 && index <39 && !mergeItemStack(itemStack1, 3, 30, false))
                    return null;
            }
            else if(!mergeItemStack(itemStack1, 3, 39, false))
                return null;

            if(itemStack1.stackSize == 0)
                slot.putStack((ItemStack)null);
            else
                slot.onSlotChanged();

            if(itemStack1.stackSize == itemStack.stackSize)
                return null;

            slot.onPickupFromSlot(player, itemStack1);
        }

        return itemStack;
    }
}
