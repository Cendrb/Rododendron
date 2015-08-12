package com.cendrb.rododendron.init;

import com.cendrb.rododendron.utility.objects.ConfigurableItemStorageObject;
import com.cendrb.rododendron.utility.objects.SwitchableItemStorageObject;
import net.minecraft.item.Item;

import java.util.HashMap;

/**
 * Created by cendr_000 on 8. 7. 2015.
 */
public class ConfigurableItems {

    private static HashMap<Item, ConfigurableItemStorageObject> configurableItems = new HashMap<Item, ConfigurableItemStorageObject>();
    private static HashMap<Item, SwitchableItemStorageObject> switchableItems = new HashMap<Item, SwitchableItemStorageObject>();

    public static void setupItems()
    {
        addConfigurable(ModItems.psimStaff, "multiplier", "Psim multiplier", 0.1F, 0, 5, false);
        addConfigurable(ModItems.buildersStaff, "range", "Range", 1, 0, 50, true);
        addConfigurable(ModItems.scaffoldingBuilder, "range", "Range", 1, 0, 50, true);
        addConfigurable(ModItems.akbarGun, "multiplier", "Speed multiplier", 0.5F, 0, 10, false);
        addSwitchable(ModItems.buildersStaff, "verticalAxis", "Working axis", "vertical", "horizontal");
        addSwitchable(ModItems.scaffoldingBuilder, "verticalAxis", "Working axis", "vertical", "horizontal");
    }

    private static void addConfigurable(Item item, String configuredPropertyCodeName, String configuredPropertyFullName, float propertyStep, float propertyMin, float propertyMax, boolean useInteger)
    {
        configurableItems.put(item, new ConfigurableItemStorageObject(item, configuredPropertyCodeName, configuredPropertyFullName, propertyStep, propertyMin, propertyMax, useInteger));
    }

    private static void addSwitchable(Item item, String switchableCodeName, String switchableFullName, String switchableTrue, String switchableFalse)
    {
        switchableItems.put(item, new SwitchableItemStorageObject(item, switchableCodeName, switchableFullName, switchableTrue, switchableFalse));
    }

    public static ConfigurableItemStorageObject getConfigurable(Item item)
    {
        if(configurableItems.containsKey(item))
        {
            return configurableItems.get(item);
        }
        else
            return null;
    }

    public static SwitchableItemStorageObject getSwitchable(Item item)
    {
        if(switchableItems.containsKey(item))
        {
            return switchableItems.get(item);
        }
        else
            return null;
    }
}
