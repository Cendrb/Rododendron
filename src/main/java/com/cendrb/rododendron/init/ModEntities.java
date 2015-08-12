package com.cendrb.rododendron.init;

import com.cendrb.rododendron.Rododendron;
import com.cendrb.rododendron.entity.EntityAkbarCow;
import com.cendrb.rododendron.entity.EntityDildo;
import com.cendrb.rododendron.entity.EntityDun;
import com.cendrb.rododendron.entity.EntityMegaAkbarCow;
import com.cendrb.rododendron.entity.bitch.*;
import com.cendrb.rododendron.reference.Names;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;

/**
 * Created by cendr_000 on 4. 7. 2015.
 */
public class ModEntities {

    private static int modEntityId = 0;

    public static void init()
    {
        registerModEntityWithEgg(EntityAkbarCow.class, Names.Entities.akbarCow, 15795119, 9569520);
        registerModEntityWithEgg(EntityMegaAkbarCow.class, Names.Entities.megaAkbarCow, 15795119, 9569520);
        registerModEntityWithEgg(EntityDildo.class, Names.Entities.dildo, 15795119, 9569520);
        registerModEntityWithEgg(EntityDun.class, Names.Entities.dun, 15795119, 9569520);
        registerModEntityWithEgg(EntityYvonne.class, Names.Entities.yvonne, 15795119, 9569520);
        registerModEntityWithEgg(EntityMary.class, Names.Entities.mary, 15795119, 9569520);
        registerModEntityWithEgg(EntitySakalka.class, Names.Entities.sakalka, 15795119, 9569520);
        registerModEntityWithEgg(EntityStepanek.class, Names.Entities.stepanek, 15795119, 9569520);
        registerModEntityWithEgg(EntityNinunu.class, Names.Entities.ninunu, 15795119, 9569520);
    }

    private static void registerModEntityWithEgg(Class<? extends Entity> entityClass, String name, int eggBackColor, int eggForeColor)
    {
        EntityRegistry.registerModEntity(entityClass, name, ++modEntityId, Rododendron.instance, 80, 3, false);
    }
}
