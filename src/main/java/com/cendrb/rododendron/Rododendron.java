package com.cendrb.rododendron;

import com.cendrb.rododendron.client.gui.GuiHandler;
import com.cendrb.rododendron.client.handler.KeyInputEventHandler;
import com.cendrb.rododendron.entity.EntityDildo;
import com.cendrb.rododendron.handler.ConfigurationHandler;
import com.cendrb.rododendron.init.*;
import com.cendrb.rododendron.network.MessagePlayerInventoryItemChange;
import com.cendrb.rododendron.proxy.IProxy;
import com.cendrb.rododendron.reference.Reference;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.ArrayList;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class Rododendron {

    public static SimpleNetworkWrapper network;

    public enum GuiEnum { CHARGER }

    @Mod.Instance(Reference.MOD_ID)
    public static Rododendron instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void pre(FMLPreInitializationEvent event)
    {
        network = NetworkRegistry.INSTANCE.newSimpleChannel("Rododendron");
        network.registerMessage(MessagePlayerInventoryItemChange.Handler.class, MessagePlayerInventoryItemChange.class, 0, Side.SERVER);

        // config
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        proxy.registerKeyBindings();

        // blocks, items, entities
        ModItems.init();
        ModBlocks.init();
        ModEntities.init();


        NetworkRegistry.INSTANCE.registerGuiHandler(Rododendron.instance, new GuiHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        // recipes, gui
        Recipes.init();

        ModTileEntities.init();

        proxy.registerEntityRender();
    }

    @Mod.EventHandler
    public void post(FMLPostInitializationEvent event)
    {
        ChargerRecipes.setupRecipes();
        ConfigurableItems.setupItems();
        // ore dictionary
        //for(String name:OreDictionary.getOreNames())
        //    LogHelper.debug(name);
    }
}
