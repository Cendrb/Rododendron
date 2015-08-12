package com.cendrb.rododendron.utility;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;

import java.util.Random;

public class GenericHelpers {
	static Random random = new Random();
	public static float randomFloatInRange(float min, float max)
	{
		return (float) ((random.nextFloat() * max) + min);
	}

	public static void changeVelocityToTarget(double xTarget, double yTarget, double zTarget, Entity projectile, double xSource, double ySource, double zSource, float distance)
	{
		double d0 = xTarget - xSource;
		double d1 = yTarget - ySource;
		double d2 = zTarget - zSource;

		float f1 = MathHelper.sqrt_float(distance) * 0.5F;

		projectile.motionX = d0 * f1 * random.nextGaussian();
		projectile.motionY = d1;
		projectile.motionZ = d2 * f1 * random.nextGaussian();
	}

	public static ChunkCoordinates getblockBellowEntity(Entity entity)
    {
        
    }
}
