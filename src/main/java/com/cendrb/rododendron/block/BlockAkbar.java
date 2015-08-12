package com.cendrb.rododendron.block;

import com.cendrb.rododendron.reference.Names;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

/**
 * Created by cendr_000 on 3. 7. 2015.
 */
public class BlockAkbar extends BlockGeneric {

    public BlockAkbar() {
        super();
        setBlockName(Names.Blocks.akbar);
    }


    @Override
    public int getMobilityFlag() {
        return 0;
    }

    @Override
     public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (!world.isRemote) {

            entity.motionY = 1.0F;

            world.createExplosion(null, x, y, z, 5.0F, true);

        }
    }

    @Override
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion) {
        if (!world.isRemote) {

            world.createExplosion(null, x, y, z, 5.0F, true);

        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return AxisAlignedBB.getBoundingBox((double) x - 0.1D, (double) y - 0.1D, (double) z - 0.1D, (double) x + 0.9D, (double) y + 0.9, (double) z + 0.9D);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (!world.isRemote && player.getCurrentEquippedItem() == null) {
            Block block = world.getBlock(x, y - 1, z);
            if (block instanceof BlockAkbarCore) {
                if (((BlockAkbarCore) block).setupMultiblockAkbar(world, x, y - 1, z))
                    player.addChatMessage(new ChatComponentText("Created AKBAR 420!!! Right click for some fun."));
                else
                    player.addChatMessage(new ChatComponentText("AKBAR 420: Requires 3X3X3 structure of Akbar blocks. Akbar core in the middle. Then click on top middle block."));
            } else {
                player.addChatMessage(new ChatComponentText("AKBAR 420: Requires 3X3X3 structure of Akbar blocks. Akbar core in the middle. Then click on top middle block."));
            }
        }
        return false;
    }
}
