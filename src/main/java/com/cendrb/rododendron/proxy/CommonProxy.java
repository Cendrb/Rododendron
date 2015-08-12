package com.cendrb.rododendron.proxy;

import com.cendrb.rododendron.entity.EntityDildo;
import com.cendrb.rododendron.utility.LogHelper;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * Created by cendr_000 on 3. 7. 2015.
 */
public abstract class CommonProxy implements IProxy, IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }
}
