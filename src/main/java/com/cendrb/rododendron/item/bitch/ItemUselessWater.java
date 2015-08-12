package com.cendrb.rododendron.item.bitch;

import com.cendrb.rododendron.item.ItemGeneric;
import com.cendrb.rododendron.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Cendrb on 28. 8. 2014.
 */
public class ItemUselessWater extends ItemGeneric {
    public ItemUselessWater()
    {
        super();
        setUnlocalizedName(Names.Items.uselessWater);
        setMaxStackSize(1);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if(!world.isRemote) {
            switch (side) {
                case 0:
                    y -= 1;
                    break;
                case 1:
                    y += 1;
                    break;
                case 2:
                    z -= 1;
                    break;
                case 3:
                    z += 1;
                    break;
                case 4:
                    x -= 1;
                    break;
                case 5:
                    x += 1;
                    break;
            }
            world.setBlock(x, y, z, Blocks.flowing_water, 0, 3);
            return true;
        }
        return false;
    }
}
