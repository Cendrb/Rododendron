package com.cendrb.rododendron.init;

import com.cendrb.rododendron.reference.Names;
import com.cendrb.rododendron.reference.Reference;
import com.cendrb.rododendron.tile_entity.TileEntityAkbarCore;
import com.cendrb.rododendron.tile_entity.TileEntityCharger;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by cendr_000 on 4. 7. 2015.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModTileEntities {

    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntityAkbarCore.class, Names.TileEntities.akbarCore);
        GameRegistry.registerTileEntity(TileEntityCharger.class, Names.TileEntities.charger);
    }
}
