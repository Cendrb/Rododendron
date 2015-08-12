package com.cendrb.rododendron.block.rubber_dildo;

import com.cendrb.rododendron.block.BlockGeneric;
import com.cendrb.rododendron.block.IBlockWithMultiplier;
import com.cendrb.rododendron.utility.WorldHelper;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * Created by cendr_000 on 4. 7. 2015.
 */
public abstract class BlockRubberDildo extends BlockGeneric implements IBlockWithMultiplier {

    public BlockRubberDildo(String name) {
        super();
        setBlockName(name);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {

        if (entity.motionY < 0)
            entity.motionY *=(-(1.2F + WorldHelper.getMultiplier(BlockRubberDildo.class, world, x, y, z, this)));
        entity.fallDistance = 0;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return AxisAlignedBB.getBoundingBox(x, y, z, x, y, z);
    }
}
