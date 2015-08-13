package com.cendrb.rododendron.utility.objects;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import org.apache.logging.log4j.core.helpers.KeyValuePair;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by cendr_000 on 13.08.2015.
 */
public class Ritual {

    Class<? extends Block>[][][] schema;
    int xCenter;
    int yCenter;
    int zCenter;

    public Ritual(int xSize, int ySize, int zSize, int xCenter, int yCenter, int zCenter)
    {
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.zCenter = zCenter;
        schema = new Class[xSize][ySize][zSize];
    }

    public void setBlock(int x, int y, int z, Class<? extends Block> block)
    {
        schema[x + xCenter][y + yCenter][z + zCenter] = block;
    }

    public boolean ritualReady(int realWorldX, int realWorldY, int realWorldZ, World world)
    {
        for (int x = 0; x != schema.length; x++)
            for (int y = 0; y != schema[0].length; y++)
                for (int z = 0; z != schema[0][0].length; z++)
                {
                    if(schema[x][y][z] != null && !schema[x][y][z].isInstance(world.getBlock((x - xCenter) + realWorldX, (y - yCenter) + realWorldY, (z - zCenter) + realWorldZ)))
                        return false;
                }
        return true;
    }
}
