package com.cendrb.rododendron.block.speedy_semen;

import com.cendrb.rododendron.block.BlockGeneric;
import com.cendrb.rododendron.block.IBlockWithMultiplier;
import com.cendrb.rododendron.utility.WorldHelper;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by cendr_000 on 4. 7. 2015.
 */
public abstract class BlockSpeedySemen extends BlockGeneric implements IBlockWithMultiplier {

    public BlockSpeedySemen(String name)
    {
        super();
        setBlockName(name);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        float multiplier = WorldHelper.getMultiplier(BlockSpeedySemen.class, world, x, y, z, this);

        entity.motionX *= (1 + multiplier);
        entity.motionZ *= (1 + multiplier);
    }

    @Override
    public int quantityDropped(Random p_149745_1_) {
        return 1;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return AxisAlignedBB.getBoundingBox(x, y, z, (double) x + 1.0D, (double) y + 0.1, (double) z + 1.0D);
    }
}
