package com.cendrb.rododendron.item.food;

import com.cendrb.rododendron.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * Created by cendr_000 on 8. 7. 2015.
 */
public class ItemPinkCookie extends ItemGenericFood {
    public ItemPinkCookie() {
        super(1, 0.3f, false);
        setUnlocalizedName(Names.Items.pinkCookie);
        setAlwaysEdible();
    }

    @Override
    protected void onFoodEaten(ItemStack p_77849_1_, World world, EntityPlayer player) {
        super.onFoodEaten(p_77849_1_, world, player);
        if(!world.isRemote)
        {
            player.setAbsorptionAmount(10);
        }
    }
}
