package com.cendrb.rododendron.client.handler;

import com.cendrb.rododendron.client.settings.KeyBindings;
import com.cendrb.rododendron.init.ConfigurableItems;
import com.cendrb.rododendron.reference.Key;
import com.cendrb.rododendron.utility.objects.ConfigurableItemStorageObject;
import com.cendrb.rododendron.utility.objects.SwitchableItemStorageObject;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;


/**
 * Created by cendr_000 on 8. 7. 2015.
 */
public class KeyInputEventHandler {

    private static Key getPressedKey() {
        if (KeyBindings.increase.isPressed())
            return Key.INCREASE;
        else if (KeyBindings.decrease.isPressed())
            return Key.DECREASE;
        else if (KeyBindings.axis.isPressed())
            return Key.AXIS;
        return Key.UNKNOWN;
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent keyInputEvent) {
        Key key = getPressedKey();

        if (key == Key.INCREASE || key == Key.DECREASE || key == Key.AXIS) {
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            ItemStack currentItemStack = player.inventory.getCurrentItem();
            if(currentItemStack != null) {
                ConfigurableItemStorageObject configurableItemStorageObject = ConfigurableItems.getConfigurable(currentItemStack.getItem());
                if (configurableItemStorageObject != null)
                    if (key == Key.INCREASE) {
                        configurableItemStorageObject.increment(player);
                    } else if (key == Key.DECREASE) {
                        configurableItemStorageObject.decrement(player);
                    }

                SwitchableItemStorageObject switchableItemStorageObject = ConfigurableItems.getSwitchable(currentItemStack.getItem());
                if (switchableItemStorageObject != null)
                    if (key == Key.AXIS) {
                        switchableItemStorageObject.switchBy(player);
                    }
            }
        }
    }
}
