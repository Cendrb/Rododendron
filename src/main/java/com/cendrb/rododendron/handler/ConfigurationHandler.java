package com.cendrb.rododendron.handler;

import com.cendrb.rododendron.reference.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler {
    public static Configuration configuration;
    public static boolean randomAllahuAkbar = true;

    public static void init(File configFile)
    {
        if(configuration == null)
        {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }

    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if(event.modID.equalsIgnoreCase(Reference.MOD_ID))
        {
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        randomAllahuAkbar = configuration.getBoolean("randomAllahuAkbar", Configuration.CATEGORY_GENERAL, false, "Responsible for random Allahu Akbar");

        if(configuration.hasChanged())
            configuration.save();
    }
}
