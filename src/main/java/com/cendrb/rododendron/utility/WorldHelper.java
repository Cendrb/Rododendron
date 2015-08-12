package com.cendrb.rododendron.utility;

import com.cendrb.rododendron.block.IBlockWithMultiplier;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * Created by cendr_000 on 4. 7. 2015.
 */
public class WorldHelper {

    public static boolean surroundedBy(World world, int x, int y, int z, Class<? extends Block> block)
    {
        for(int xOff = -1; xOff != 1; xOff++)
            for(int yOff = -1; yOff != 1; yOff++)
                for(int zOff = -1; zOff != 1; zOff++)
                    if(!(xOff == 0 && yOff == 0 && zOff == 0))
                    {
                        if(!block.isInstance(world.getBlock(x + xOff, y + yOff, z + zOff)))
                            return false;
                    }
        return true;
    }


    public static void createExplosion(World world, Entity entity, double x, double y, double z, float power)
    {
        Explosion explosion = new RodoExplosion(world, entity, x, y, z, power);
        explosion.isFlaming = false;
        explosion.isSmoking = true;
        if (net.minecraftforge.event.ForgeEventFactory.onExplosionStart(world, explosion)) return;
        explosion.doExplosionA();
        explosion.doExplosionB(true);
    }

    public static void createCustomExplosion(World world, Entity entity, double x, double y, double z, int power)
    {
        double step = 7;
        for(double yOff = power; (-yOff) < power; yOff -= step)
        {
            double sidePower = power - Math.abs(yOff);
            double sideMultiplier = Math.pow(sidePower, 1.12) / sidePower;
            sidePower = sidePower * sideMultiplier;
            double sideStep = step * (1 / sideMultiplier);

            LogHelper.info(sidePower);
            LogHelper.info(sideMultiplier);
            LogHelper.info(sideStep);

            for (double xOff = sidePower; (-xOff) < sidePower; xOff -= sideStep)
                for (double zOff = sidePower; (-zOff) < sidePower; zOff -= sideStep)
                {
                    WorldHelper.createExplosion(world, entity, x + xOff, y + yOff, z + zOff, 15);
                    //LogHelper.info(String.format("%s %s %s", x + xOff, y + yOff, z + zOff));
                }
        }
    }

    public static float getMultiplier(Class<? extends IBlockWithMultiplier> baseBlockClass, World world, int x, int y, int z, Block senderBlock)
    {
        float multiplier = 0;
        int pos = 1;
        Block block = senderBlock;
        while (baseBlockClass.isInstance(block)) {
            pos++;
            multiplier += baseBlockClass.cast(block).getMultiplier();
            block = world.getBlock(x, y - pos, z);
        }
        return multiplier;
    }

    public static Entity launchEntity(Entity source, Entity target, float multiplier)
    {
        return launchEntity(source, target, multiplier, multiplier, multiplier);
    }

    public static Entity launchEntity(Entity source, Entity target, float multiplierX, float multiplierY, float multiplierZ)
    {
        Vec3 vector = source.getLookVec();
        target.motionX = vector.xCoord * multiplierX;
        target.motionY = vector.yCoord * multiplierY;
        target.motionZ = vector.zCoord * multiplierZ;
        //target.setVelocity(vector.xCoord * multiplierX, vector.yCoord * multiplierY, vector.zCoord * multiplierZ);
        return target;
    }

    public static void spawnFlyingEntities(World world, int count, Class<? extends Entity> entity, double posX, double posY, double posZ)
    {
        Random random = new Random();

        for (int x = 0; x <= count; x++) {
            Entity akbarCow = null;
            try {
                akbarCow = entity.getConstructor(World.class).newInstance(world);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            float randomizer = random.nextFloat();

            if (randomizer > 0.5F) {
                akbarCow.motionY = 1 + random.nextFloat() * 69 + 5;
                akbarCow.motionX = (0.5 - random.nextFloat()) * 4;
                akbarCow.motionZ = (0.5 - random.nextFloat()) * 4;
            } else {
                if (randomizer > 0.3F) {
                    akbarCow.motionY = 1 + random.nextFloat() * 40 + 5;
                    akbarCow.motionX = (0.5 - random.nextFloat()) * 4 * (0.5 + random.nextFloat());
                    akbarCow.motionZ = (0.5 - random.nextFloat()) * 4 * (0.5 + random.nextFloat());
                } else {
                    if (randomizer > 0.1F) {
                        akbarCow.motionY = 1 + random.nextFloat() * 40 + 5;
                        akbarCow.motionX = (0.5 - random.nextFloat()) * 4 * (1 + random.nextFloat());
                        akbarCow.motionZ = (0.5 - random.nextFloat()) * 4 * (1 + random.nextFloat());
                    } else {
                        akbarCow.motionY = 1 + random.nextFloat() * 40 + 5;
                        akbarCow.motionX = (0.5 - random.nextFloat()) * 4 * (1.5 + random.nextFloat());
                        akbarCow.motionZ = (0.5 - random.nextFloat()) * 4 * (1.5 + random.nextFloat());
                    }

                }

                akbarCow.setPosition(posX, posY + 1, posZ);

                world.spawnEntityInWorld(akbarCow);

            }
        }
    }
}

