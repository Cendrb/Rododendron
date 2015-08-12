package com.cendrb.rododendron.utility;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.HashSet;

/**
 * Created by cendr_000 on 4. 7. 2015.
 */
public class SemenExplosion extends Explosion {

    World worldObj;

    public SemenExplosion(World p_i1948_1_, Entity p_i1948_2_, double p_i1948_3_, double p_i1948_5_, double p_i1948_7_, float p_i1948_9_) {
        super(p_i1948_1_, p_i1948_2_, p_i1948_3_, p_i1948_5_, p_i1948_7_, p_i1948_9_);
        worldObj = p_i1948_1_;
    }

    @Override
    public void doExplosionA() {
        float power = explosionSize;

        HashSet hashset = new HashSet();

        for (float x = 0; x < power; x++)
            for (float y = 0; y < power; y++)
                for (float z = 0; z < power; z++) {
                    if (x == 0 || x == power || y == 0 || y == power || z == 0 || z == power)
                        LogHelper.info(String.format("%s X %s X %s", x, y, z));
                    double fX = explosionX;
                    double fY = explosionY;
                    double fZ = explosionZ;

                    int ifX = MathHelper.floor_double(fX);
                    int ifY = MathHelper.floor_double(fY);
                    int ifZ = MathHelper.floor_double(fZ);
                    Block block = worldObj.getBlock(ifX, ifY, ifZ);
                    if(block.getMaterial() != Material.air)
                    {
                        // add to hash
                        hashset.add(new ChunkPosition(ifX, ifY, ifZ));
                    }
                }

        affectedBlockPositions.addAll(hashset);
    }
}
