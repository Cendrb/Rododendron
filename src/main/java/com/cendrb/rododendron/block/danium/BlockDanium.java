package com.cendrb.rododendron.block.danium;

import com.cendrb.rododendron.block.BlockGeneric;
import com.cendrb.rododendron.block.IBlockWithMultiplier;
import com.cendrb.rododendron.utility.WorldHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * Created by cendr_000 on 3. 7. 2015.
 */
public abstract class BlockDanium extends BlockGeneric implements IBlockWithMultiplier {

    public BlockDanium(String name) {
        super();
        setBlockName(name);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {

        if (entity instanceof EntityLivingBase) {
            EntityLivingBase living = (EntityLivingBase) entity;
            living.addPotionEffect(new PotionEffect(Potion.jump.id, 1, (int) WorldHelper.getMultiplier(BlockDanium.class, world, x, y, z, this)));
        }
        entity.fallDistance = 0;
    }


    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return AxisAlignedBB.getBoundingBox(x, y, z, (double) x + 1.0D, (double) y + 0.7, (double) z + 1.0D);
    }
}
