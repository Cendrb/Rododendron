package com.cendrb.rododendron.block.danium;

import com.cendrb.rododendron.reference.Names;

/**
 * Created by cendr_000 on 7. 7. 2015.
 */
public class BlockPinkDanium extends BlockDanium {
    public BlockPinkDanium() {
        super(Names.Blocks.pinkDanium);
    }

    @Override
    public float getMultiplier() {
        return 2.0F;
    }
}
