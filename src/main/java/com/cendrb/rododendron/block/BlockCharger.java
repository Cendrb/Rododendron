package com.cendrb.rododendron.block;

import com.cendrb.rododendron.Rododendron;
import com.cendrb.rododendron.init.ModBlocks;
import com.cendrb.rododendron.init.ModItems;
import com.cendrb.rododendron.network.MessagePlayerInventoryItemChange;
import com.cendrb.rododendron.reference.Names;
import com.cendrb.rododendron.tile_entity.TileEntityCharger;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by cendr_000 on 6. 7. 2015.
 */
public class BlockCharger extends BlockGeneric implements ITileEntityProvider {

    private boolean burning;

    public BlockCharger() {
        super();
        setBlockName(Names.Blocks.charger);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (!world.isRemote) {
            ItemStack currentItemStack = player.inventory.getCurrentItem();
            if(currentItemStack != null && currentItemStack.getItem() == Item.getItemFromBlock(ModBlocks.akbarCore))
            {
                TileEntityCharger tileEntityCharger =  (TileEntityCharger)world.getTileEntity(x, y, z);
                if(tileEntityCharger.upgradeTier())
                    player.inventory.consumeInventoryItem(Item.getItemFromBlock(ModBlocks.akbarCore));
                else
                    player.addChatComponentMessage(new ChatComponentText("Max tier reached"));
            }
            else
                player.openGui(Rododendron.instance, Rododendron.GuiEnum.CHARGER.ordinal(), world, x, y, z);
        }
        return true;
    }

    public static void updateBurningState(boolean burning, World worldObj, int xCoord, int yCoord, int zCoord, TileEntityCharger tileEntityCharger) {
        int metadata = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int p_149749_6_) {
        TileEntityCharger tileEntityCharger = (TileEntityCharger) world.getTileEntity(x, y, z);

        if (tileEntityCharger != null) {
            for (int i1 = 0; i1 < tileEntityCharger.getSizeInventory(); ++i1) {
                ItemStack itemstack = tileEntityCharger.getStackInSlot(i1);

                if (itemstack != null) {
                    EntityItem entityItem = new EntityItem(world, x, y, z, itemstack);
                    world.spawnEntityInWorld(entityItem);
                }
            }

            if(tileEntityCharger.chargerTier > 0)
            {
                EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ModBlocks.akbarCore, tileEntityCharger.chargerTier));
                world.spawnEntityInWorld(entityItem);
            }
        }

        super.breakBlock(world, x, y, z, p_149749_5_, p_149749_6_);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityCharger();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        TileEntityCharger tileEntityCharger = (TileEntityCharger) world.getTileEntity(x, y, z);
        if(tileEntityCharger != null && tileEntityCharger.isBurning())
        {
            world.spawnParticle("smoke", x, y + 1.2F, z, 0, 0, 0);
            world.spawnParticle("flame", x, y + 1.3F, z, 0, 0, 0);
            world.spawnParticle("heart", x, y + 1.4F, z, 0, 0, 0);
        }
        super.randomDisplayTick(world, x, y, z, random);
    }
}
