package com.cendrb.rododendron.proxy;

import com.cendrb.rododendron.client.handler.KeyInputEventHandler;
import com.cendrb.rododendron.client.model.ModelDildo;
import com.cendrb.rododendron.client.model.ModelDun;
import com.cendrb.rododendron.client.model.ModelInnocentVillager;
import com.cendrb.rododendron.client.model.bitch.ModelBitch;
import com.cendrb.rododendron.client.render.RenderDildo;
import com.cendrb.rododendron.client.render.RenderDun;
import com.cendrb.rododendron.client.render.RenderInnocentVillager;
import com.cendrb.rododendron.client.render.bitch.RenderBitch;
import com.cendrb.rododendron.client.render.bitch.RenderStepanek;
import com.cendrb.rododendron.client.settings.KeyBindings;
import com.cendrb.rododendron.entity.EntityDildo;
import com.cendrb.rododendron.entity.EntityDun;
import com.cendrb.rododendron.entity.EntityInnocentVillager;
import com.cendrb.rododendron.entity.bitch.*;
import com.cendrb.rododendron.reference.Reference;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;

/**
 * Created by cendr_000 on 3. 7. 2015.
 */
public class ClientProxy extends CommonProxy {
    @Override
    public void registerEntityRender() {
        RenderingRegistry.registerEntityRenderingHandler(EntityDildo.class, new RenderDildo(new ModelDildo(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityDun.class, new RenderDun(new ModelDun(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityYvonne.class, new RenderBitch(new ModelBitch(), 0.4F, new ResourceLocation(Reference.MOD_ID, "textures/entity/bitch.png"))); // float = shadow size
        RenderingRegistry.registerEntityRenderingHandler(EntityNinunu.class, new RenderBitch(new ModelBitch(), 0.4F, new ResourceLocation(Reference.MOD_ID, "textures/entity/bitch.png")));
        RenderingRegistry.registerEntityRenderingHandler(EntityMary.class, new RenderBitch(new ModelBitch(), 0.4F, new ResourceLocation(Reference.MOD_ID, "textures/entity/bitch.png")));
        RenderingRegistry.registerEntityRenderingHandler(EntitySakalka.class, new RenderBitch(new ModelBitch(), 0.4F, new ResourceLocation(Reference.MOD_ID, "textures/entity/bitch.png")));
        RenderingRegistry.registerEntityRenderingHandler(EntityStepanek.class, new RenderStepanek(new ModelBiped(), 0.1F));
        RenderingRegistry.registerEntityRenderingHandler(EntityInnocentVillager.class, new RenderInnocentVillager(new ModelInnocentVillager(), 0.5F));
    }

    @Override
    public void registerKeyBindings() {
        FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());
        ClientRegistry.registerKeyBinding(KeyBindings.increase);
        ClientRegistry.registerKeyBinding(KeyBindings.decrease);
        ClientRegistry.registerKeyBinding(KeyBindings.axis);
    }
}
