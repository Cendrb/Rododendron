package com.cendrb.rododendron.block.danium;

import com.cendrb.rododendron.reference.Names;

/**
 * Created by cendr_000 on 7. 7. 2015.
 */
public class BlockEnergizedDanium extends BlockDanium {


    public BlockEnergizedDanium() {
        super(Names.Blocks.energizedDanium);
    }

    @Override
    public float getMultiplier() {
        return 5.0F;
    }
}
