package com.cendrb.rododendron.client.gui;

import com.cendrb.rododendron.handler.ConfigurationHandler;
import com.cendrb.rododendron.reference.Reference;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

/**
 * Created by cendr_000 on 3. 7. 2015.
 */
public class RododendronGuiConfig extends GuiConfig {
    public RododendronGuiConfig(GuiScreen guiScreen) {
        super(guiScreen,
                new ConfigElement(ConfigurationHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                Reference.MOD_ID,
                true,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));
    }
}
