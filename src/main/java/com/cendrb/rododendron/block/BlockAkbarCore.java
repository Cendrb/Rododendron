package com.cendrb.rododendron.block;

import com.cendrb.rododendron.reference.Names;
import com.cendrb.rododendron.tile_entity.TileEntityAkbarCore;
import com.cendrb.rododendron.utility.WorldHelper;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

/**
 * Created by cendr_000 on 4. 7. 2015.
 */
public class BlockAkbarCore extends BlockGeneric implements ITileEntityProvider {

    private int akbarClicks = 5;

    public BlockAkbarCore() {
        super();
        setBlockName(Names.Blocks.akbarCore);
        setResistance(1000);
    }


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (!world.isRemote && player.getCurrentEquippedItem() == null && !((TileEntityAkbarCore) world.getTileEntity(x, y, z)).getRunning()) {
            if (world.getBlockMetadata(x, y, z) == 0)
                player.addChatMessage(new ChatComponentText("AKBAR 420: Requires 3X3X3 structure of Akbar blocks. Akbar core in the middle. Then click on top middle block."));
            else if(!((TileEntityAkbarCore) world.getTileEntity(x, y, z)).getArmed())
                player.addChatMessage(new ChatComponentText("Place TNT on top to arm"));
            else
                player.addChatMessage(new ChatComponentText("Destroy TNT on top to start"));
        }
        return true;
    }

    public boolean setupMultiblockAkbar(World world, int x, int y, int z) {
        if (WorldHelper.surroundedBy(world, x, y, z, BlockAkbar.class)) {
            world.setBlockMetadataWithNotify(x, y, z, 1, 2);

            world.setBlockToAir(x + 1, y + 1, z);
            world.setBlockToAir(x, y + 1, z + 1);
            world.setBlockToAir(x + 1, y + 1, z + 1);
            world.setBlockToAir(x - 1, y + 1, z);
            world.setBlockToAir(x, y + 1, z - 1);
            world.setBlockToAir(x - 1, y + 1, z - 1);
            world.setBlockToAir(x + 1, y + 1, z - 1);
            world.setBlockToAir(x - 1, y + 1, z + 1);
            world.setBlockToAir(x, y + 1, z);

            world.setBlockToAir(x + 1, y, z);
            world.setBlockToAir(x, y, z + 1);
            world.setBlockToAir(x + 1, y, z + 1);
            world.setBlockToAir(x - 1, y, z);
            world.setBlockToAir(x, y, z - 1);
            world.setBlockToAir(x - 1, y, z - 1);
            world.setBlockToAir(x + 1, y, z - 1);
            world.setBlockToAir(x - 1, y, z + 1);

            world.setBlockToAir(x + 1, y - 1, z);
            world.setBlockToAir(x, y - 1, z + 1);
            world.setBlockToAir(x + 1, y - 1, z + 1);
            world.setBlockToAir(x - 1, y - 1, z);
            world.setBlockToAir(x, y - 1, z - 1);
            world.setBlockToAir(x - 1, y - 1, z - 1);
            world.setBlockToAir(x + 1, y - 1, z - 1);
            world.setBlockToAir(x - 1, y - 1, z + 1);
            world.setBlockToAir(x, y - 1, z);

            return true;
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityAkbarCore();
    }
}
