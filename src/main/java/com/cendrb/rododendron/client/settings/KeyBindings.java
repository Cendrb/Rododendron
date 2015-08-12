package com.cendrb.rododendron.client.settings;

import com.cendrb.rododendron.reference.Names;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

/**
 * Created by cendr_000 on 8. 7. 2015.
 */
public class KeyBindings
{
    public static KeyBinding increase = new KeyBinding(Names.Keys.INCREASE, Keyboard.KEY_ADD, Names.Keys.CATEGORY);
    public static KeyBinding decrease = new KeyBinding(Names.Keys.DECREASE, Keyboard.KEY_SUBTRACT, Names.Keys.CATEGORY);
    public static KeyBinding axis = new KeyBinding(Names.Keys.AXIS, Keyboard.KEY_MULTIPLY, Names.Keys.CATEGORY);
}
