package com.cendrb.rododendron.init;

import com.cendrb.rododendron.block.BlockAkbar;
import com.cendrb.rododendron.block.BlockAkbarCore;
import com.cendrb.rododendron.reference.Reference;
import com.cendrb.rododendron.utility.objects.Ritual;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockQuartz;
import net.minecraft.block.BlockSandStone;

/**
 * Created by cendr_000 on 13.08.3015.
 */
public class Rituals {

    public static Ritual AkbarTemple;

    public static void init()
    {
        AkbarTemple = new Ritual(11, 4, 11, 5, 0, 5);
        AkbarTemple.setBlock(1, 0, 0, BlockAkbar.class);
        AkbarTemple.setBlock(-1, 0, 0, BlockAkbar.class);
        AkbarTemple.setBlock(0, 0, 1, BlockAkbar.class);
        AkbarTemple.setBlock(0, 0, -1, BlockAkbar.class);

        AkbarTemple.setBlock(2, 0, 0, BlockAkbar.class);
        AkbarTemple.setBlock(-2, 0, 0, BlockAkbar.class);
        AkbarTemple.setBlock(0, 0, 2, BlockAkbar.class);
        AkbarTemple.setBlock(0, 0, -2, BlockAkbar.class);


        AkbarTemple.setBlock(3, 0, 1, BlockAkbar.class);
        AkbarTemple.setBlock(3, 0, -1, BlockAkbar.class);
        AkbarTemple.setBlock(-3, 0, 1, BlockAkbar.class);
        AkbarTemple.setBlock(-3, 0, -1, BlockAkbar.class);
        AkbarTemple.setBlock(1, 0, 3, BlockAkbar.class);
        AkbarTemple.setBlock(-1, 0, 3, BlockAkbar.class);
        AkbarTemple.setBlock(1, 0, -3, BlockAkbar.class);
        AkbarTemple.setBlock(-1, 0, -3, BlockAkbar.class);

        AkbarTemple.setBlock(3, 0, 2, BlockAkbar.class);
        AkbarTemple.setBlock(3, 0, -2, BlockAkbar.class);
        AkbarTemple.setBlock(-3, 0, 2, BlockAkbar.class);
        AkbarTemple.setBlock(-3, 0, -2, BlockAkbar.class);
        AkbarTemple.setBlock(2, 0, 3, BlockAkbar.class);
        AkbarTemple.setBlock(-2, 0, 3, BlockAkbar.class);
        AkbarTemple.setBlock(2, 0, -3, BlockAkbar.class);
        AkbarTemple.setBlock(-2, 0, -3, BlockAkbar.class);

        AkbarTemple.setBlock(3, 0, 3, BlockAkbar.class);
        AkbarTemple.setBlock(3, 0, -3, BlockAkbar.class);
        AkbarTemple.setBlock(-3, 0, 3, BlockAkbar.class);
        AkbarTemple.setBlock(-3, 0, -3, BlockAkbar.class);
        AkbarTemple.setBlock(3, 1, 3, BlockAkbar.class);
        AkbarTemple.setBlock(3, 1, -3, BlockAkbar.class);
        AkbarTemple.setBlock(-3, 1, 3, BlockAkbar.class);
        AkbarTemple.setBlock(-3, 1, -3, BlockAkbar.class);
        AkbarTemple.setBlock(3, 2, 3, BlockAkbarCore.class);
        AkbarTemple.setBlock(3, 2, -3, BlockAkbarCore.class);
        AkbarTemple.setBlock(-3, 2, 3, BlockAkbarCore.class);
        AkbarTemple.setBlock(-3, 2, -3, BlockAkbarCore.class);
    }
}
