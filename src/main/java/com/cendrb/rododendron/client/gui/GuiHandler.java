package com.cendrb.rododendron.client.gui;

import com.cendrb.rododendron.Rododendron;
import com.cendrb.rododendron.container.ContainerCharger;
import com.cendrb.rododendron.tile_entity.TileEntityCharger;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by cendr_000 on 6. 7. 2015.
 */
public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity != null) {
            if(ID == Rododendron.GuiEnum.CHARGER.ordinal())
            {
                if(tileEntity instanceof TileEntityCharger)
                    return new ContainerCharger(player.inventory, (IInventory)tileEntity);
            }
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity != null) {
            if(ID == Rododendron.GuiEnum.CHARGER.ordinal())
            {
                if(tileEntity instanceof TileEntityCharger)
                    return new GuiCharger(player.inventory, (IInventory)tileEntity);
            }
        }

        return null;
    }
}
