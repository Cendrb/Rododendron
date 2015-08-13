package com.cendrb.rododendron.block;

import com.cendrb.rododendron.entity.EntityDildo;
import com.cendrb.rododendron.entity.EntityInnocentVillager;
import com.cendrb.rododendron.init.Rituals;
import com.cendrb.rododendron.reference.Names;
import com.cendrb.rododendron.structure.StructureAkbar;
import com.cendrb.rododendron.utility.LogHelper;
import com.cendrb.rododendron.utility.WorldHelper;
import com.cendrb.rododendron.utility.objects.Ritual;
import ibxm.Player;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

/**
 * Created by cendr_000 on 13.08.2015.
 */
public class BlockAkbarMasterRitualStone extends BlockGeneric {

    public BlockAkbarMasterRitualStone()
    {
        super();
        setBlockName(Names.Blocks.akbarMasterRitualStone);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if(!world.isRemote)
        {
            if(Rituals.AkbarTemple.ritualReady(x, y, z, world))
                spawnAkbarTemple(world, x, y, z, player);
            else
                player.addChatMessage(new ChatComponentText("Eh, blocks still missing"));
        }
        return super.onBlockActivated(world, x, y, z, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
    }

    public void spawnAkbarTemple(World world, int x, int y, int z, EntityPlayer player)
    {
        player.addChatMessage(new ChatComponentText("Spawning in Akbar Temple..."));
        player.addChatMessage(new ChatComponentText("Lag incoming, do NOT right-click the block again!!!"));
        StructureAkbar.spawn(world, x - 13, y, z - 13);

        for(int w = 0; w != 40; w++)
        {
            Entity villager;
            if(world.rand.nextBoolean())
                villager = new EntityVillager(world);
            else
                villager = new EntityInnocentVillager(world);
            villager.setPosition(x, y + 15, z);
            world.spawnEntityInWorld(villager);
        }

        for(int w = 0; w != 30; w++)
        {
            Entity villager;
            if(world.rand.nextBoolean())
                villager = new EntityVillager(world);
            else
                villager = new EntityInnocentVillager(world);
            villager.setPosition(x, y + 1, z);
            world.spawnEntityInWorld(villager);
        }
    }
}
