package com.cendrb.rododendron.init;

import com.cendrb.rododendron.item.charger.ItemChargeableTool;
import com.cendrb.rododendron.item.charger.ItemChargerFuel;
import com.cendrb.rododendron.utility.objects.ChargeableRecipeResult;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cendr_000 on 7. 7. 2015.
 */
public class ChargerRecipes {

    static HashMap<Item, ChargeableRecipeResult> recipes = new HashMap<Item, ChargeableRecipeResult>();
    static List<ItemChargeableTool> chargeableTools = new ArrayList<ItemChargeableTool>();
    static List<ItemChargerFuel> chargerFuels = new ArrayList<ItemChargerFuel>();

    public static void setupRecipes()
    {
        addRecipe(ModItems.pinkCookie, new ItemStack(ModItems.overchargedCookie), 5000);
    }

    public static void addRecipe(Item input, ItemStack output, int chargeTime) {
        recipes.put(input, new ChargeableRecipeResult(output, chargeTime));
    }

    public static ItemStack getChargingResult(ItemStack input) {
        if (isChargeableItem(input))
            return recipes.get(input.getItem()).getResult();
        else if(isChargeableTool(input))
            return new ItemStack(input.getItem());
        else
            return null;
    }

    public static int getChargeTime(ItemStack input) {
        if (isChargeableItem(input))
            return recipes.get(input.getItem()).getChargeTime();
        else if (isChargeableTool(input))
            return input.getItemDamage();
        else
            return 0;
    }

    public static void addChargeableTool(ItemChargeableTool item)
    {
        chargeableTools.add(item);
    }

    public static void addChargerFuel(ItemChargerFuel item)
    {
        chargerFuels.add(item);
    }

    public static boolean canBeCharged(ItemStack item)
    {
        return isChargeableItem(item) || isChargeableTool(item);
    }

    private static boolean isChargeableTool(ItemStack item)
    {
        return chargeableTools.contains(item.getItem()) && item.getItemDamage() > 0;
    }

    private static boolean isChargeableItem(ItemStack item)
    {
        return recipes.containsKey(item.getItem());
    }

    public static boolean isChargerFuel(ItemStack itemStack)
    {
        if(itemStack == null)
            return false;
        return chargerFuels.contains(itemStack.getItem());
    }

    public static int getItemBurnTime(ItemStack itemStack)
    {
        if(itemStack == null)
            return 0;
        if(isChargerFuel(itemStack))
            return ((ItemChargerFuel)itemStack.getItem()).getChargerFuelValue();
        else
            return 0;
    }
}
