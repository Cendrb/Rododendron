package com.cendrb.rododendron.tile_entity;

import com.cendrb.rododendron.entity.EntityAkbarCow;
import com.cendrb.rododendron.entity.EntityMegaAkbarCow;
import com.cendrb.rododendron.init.ModBlocks;
import com.cendrb.rododendron.reference.Names;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by cendr_000 on 4. 7. 2015.
 */
public class TileEntityAkbarCore extends TileEntity {

    private final String TICKS_PASSED = "ticksPassed";
    private final String ARMED = "armed";

    private int ticksPassed = -1;
    private boolean armed = false;

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setInteger(TICKS_PASSED, ticksPassed);
        nbtTagCompound.setBoolean(ARMED, armed);
        super.writeToNBT(nbtTagCompound);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        ticksPassed = nbtTagCompound.getInteger(TICKS_PASSED);
        armed = nbtTagCompound.getBoolean(ARMED);
        super.readFromNBT(nbtTagCompound);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        writeToNBT(nbtTagCompound);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbtTagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());
    }

    public boolean getRunning() {
        return ticksPassed != -1;
    }

    public boolean getArmed()
    {
        return armed;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if(!worldObj.isRemote && ticksPassed == -1 && !armed)
        {
            if(worldObj.getBlock(xCoord, yCoord + 1, zCoord) == Blocks.tnt) {
                armed = true;
                worldObj.playSoundEffect(xCoord, yCoord, zCoord, "rododendron:akbarArmed", 64.0F, 1.0F);
            }
        }
        if(!worldObj.isRemote && armed && ticksPassed == -1)
        {
            if(!(worldObj.getBlock(xCoord, yCoord + 1, zCoord) == Blocks.tnt)) {
                armed = false;
                ticksPassed = 0;
                worldObj.playSoundEffect(xCoord, yCoord, zCoord, "rododendron:akbarRiot", 64.0F, 1.0F);
                worldObj.playSoundEffect(xCoord, yCoord, zCoord, "rododendron:akbarSong", 64.0F, 1.0F);
            }
        }
        if (!getWorldObj().isRemote && ticksPassed != -1) {
            ticksPassed++;

            if (ticksPassed % 3 == 0) {
                World world = getWorldObj();
                Random random = new Random();
                EntityAkbarCow akbarCow = new EntityAkbarCow(world);

                float randomizer = random.nextFloat();

                if (randomizer > 0.5F) {
                    akbarCow.motionY = 1 + random.nextFloat() * 2;
                    akbarCow.motionX = (0.5 - random.nextFloat()) * 4;
                    akbarCow.motionZ = (0.5 - random.nextFloat()) * 4;
                } else {
                    if (randomizer > 0.3F) {
                        akbarCow.motionY = 1 + random.nextFloat() * 2;
                        akbarCow.motionX = (0.5 - random.nextFloat()) * 4 * (0.5 + random.nextFloat());
                        akbarCow.motionZ = (0.5 - random.nextFloat()) * 4 * (0.5 + random.nextFloat());
                    } else {
                        if (randomizer > 0.1F) {
                            akbarCow.motionY = 1 + random.nextFloat() * 2;
                            akbarCow.motionX = (0.5 - random.nextFloat()) * 4 * (1 + random.nextFloat());
                            akbarCow.motionZ = (0.5 - random.nextFloat()) * 4 * (1 + random.nextFloat());
                        } else {
                            akbarCow.motionY = 1 + random.nextFloat() * 2;
                            akbarCow.motionX = (0.5 - random.nextFloat()) * 4 * (1.5 + random.nextFloat());
                            akbarCow.motionZ = (0.5 - random.nextFloat()) * 4 * (1.5 + random.nextFloat());
                        }

                    }

                    akbarCow.setPosition(xCoord, yCoord + 1, zCoord);

                    world.spawnEntityInWorld(akbarCow);
                }
            }

            if (ticksPassed % 300 == 0) {
                World world = getWorldObj();
                Random random = new Random();
                EntityMegaAkbarCow akbarCow = new EntityMegaAkbarCow(world);

                float randomizer = random.nextFloat();

                if (randomizer > 0.5F) {
                    akbarCow.motionY = 1 + random.nextFloat() * 2;
                    akbarCow.motionX = (0.5 - random.nextFloat()) * 4;
                    akbarCow.motionZ = (0.5 - random.nextFloat()) * 4;
                } else {
                    if (randomizer > 0.3F) {
                        akbarCow.motionY = 1 + random.nextFloat() * 2;
                        akbarCow.motionX = (0.5 - random.nextFloat()) * 4 * (0.5 + random.nextFloat());
                        akbarCow.motionZ = (0.5 - random.nextFloat()) * 4 * (0.5 + random.nextFloat());
                    } else {
                        if (randomizer > 0.1F) {
                            akbarCow.motionY = 1 + random.nextFloat() * 2;
                            akbarCow.motionX = (0.5 - random.nextFloat()) * 4 * (1 + random.nextFloat());
                            akbarCow.motionZ = (0.5 - random.nextFloat()) * 4 * (1 + random.nextFloat());
                        } else {
                            akbarCow.motionY = 1 + random.nextFloat() * 2;
                            akbarCow.motionX = (0.5 - random.nextFloat()) * 4 * (1.5 + random.nextFloat());
                            akbarCow.motionZ = (0.5 - random.nextFloat()) * 4 * (1.5 + random.nextFloat());
                        }

                    }

                    akbarCow.setPosition(xCoord, yCoord + 1, zCoord);

                    world.spawnEntityInWorld(akbarCow);
                }
            }

            if (3600 < ticksPassed) {
                World world = getWorldObj();

                Random random = new Random();

                for (int count = 0; count < 1000; count++) {
                    EntityAkbarCow akbarCow = new EntityAkbarCow(world);

                    float randomizer = random.nextFloat();

                    if (randomizer > 0.5F) {
                        akbarCow.motionY = 1 + random.nextFloat() * 40;
                        akbarCow.motionX = (0.5 - random.nextFloat()) * 4;
                        akbarCow.motionZ = (0.5 - random.nextFloat()) * 4;
                    } else {
                        if (randomizer > 0.3F) {
                            akbarCow.motionY = 1 + random.nextFloat() * 40;
                            akbarCow.motionX = (0.5 - random.nextFloat()) * 4 * (0.5 + random.nextFloat());
                            akbarCow.motionZ = (0.5 - random.nextFloat()) * 4 * (0.5 + random.nextFloat());
                        } else {
                            if (randomizer > 0.1F) {
                                akbarCow.motionY = 1 + random.nextFloat() * 40;
                                akbarCow.motionX = (0.5 - random.nextFloat()) * 4 * (1 + random.nextFloat());
                                akbarCow.motionZ = (0.5 - random.nextFloat()) * 4 * (1 + random.nextFloat());
                            } else {
                                akbarCow.motionY = 1 + random.nextFloat() * 40;
                                akbarCow.motionX = (0.5 - random.nextFloat()) * 4 * (1.5 + random.nextFloat());
                                akbarCow.motionZ = (0.5 - random.nextFloat()) * 4 * (1.5 + random.nextFloat());
                            }

                        }

                        akbarCow.setPosition(xCoord, yCoord + 1, zCoord);

                        world.spawnEntityInWorld(akbarCow);

                    }
                }
                world.setBlockToAir(xCoord, yCoord, zCoord);
            }

        }
    }
}
