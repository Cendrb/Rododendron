package com.cendrb.rododendron.creative_tab;

import com.cendrb.rododendron.init.ModItems;
import com.cendrb.rododendron.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by cendr_000 on 3. 7. 2015.
 */
public class CreativeTabRodo {
    public static final CreativeTabs RODODENDRON_TAB = new CreativeTabs(Reference.MOD_ID) {
        @Override
        public Item getTabIconItem() {
            return ModItems.pinkCrystal;
        }
    };
}
