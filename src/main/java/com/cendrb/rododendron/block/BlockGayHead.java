package com.cendrb.rododendron.block;

import com.cendrb.rododendron.entity.EntityDun;
import com.cendrb.rododendron.init.ModBlocks;
import com.cendrb.rododendron.reference.Names;
import net.minecraft.world.World;

/**
 * Created by cendr_000 on 6. 7. 2015.
 */
public class BlockGayHead extends BlockGeneric {

    public BlockGayHead()
    {
        super();
        setBlockName(Names.Blocks.gayHead);
    }

    @Override
    public void onPostBlockPlaced(World world, int x, int y, int z, int p_149714_5_) {

        if(!world.isRemote && world.getBlock(x, y - 1, z) == ModBlocks.pinkIngotBlock && world.getBlock(x, y - 2, z) == ModBlocks.pinkIngotBlock)
        {
            world.setBlockToAir(x, y, z);
            world.setBlockToAir(x, y - 1, z);
            world.setBlockToAir(x, y - 2, z);

            EntityDun dun = new EntityDun(world);
            dun.setPosition(x, y - 2, z);

            world.spawnEntityInWorld(dun);
        }

        super.onPostBlockPlaced(world, x, y, z, p_149714_5_);
    }
}
