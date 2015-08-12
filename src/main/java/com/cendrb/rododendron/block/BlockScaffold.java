package com.cendrb.rododendron.block;

import com.cendrb.rododendron.reference.Names;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * Created by cendr_000 on 8. 7. 2015.
 */
public class BlockScaffold extends BlockGeneric {

    public BlockScaffold()
    {
        super();
        setBlockName(Names.Blocks.scaffold);
        setHardness(0.1F);
        setHarvestLevel("pickaxe", 0);
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return null;
    }
}
