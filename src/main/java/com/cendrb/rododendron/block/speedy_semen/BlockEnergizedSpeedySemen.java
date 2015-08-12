package com.cendrb.rododendron.block.speedy_semen;

import com.cendrb.rododendron.reference.Names;

/**
 * Created by cendr_000 on 7. 7. 2015.
 */
public class BlockEnergizedSpeedySemen extends BlockSpeedySemen {

    public BlockEnergizedSpeedySemen() {
        super(Names.Blocks.energizedSpeedySemen);
    }

    @Override
    public float getMultiplier() {
        return 0.25F;
    }
}
