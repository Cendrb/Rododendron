package com.cendrb.rododendron.init;

import com.cendrb.rododendron.item.*;
import com.cendrb.rododendron.item.bitch.ItemRightHand;
import com.cendrb.rododendron.item.bitch.ItemUselessWater;
import com.cendrb.rododendron.item.bitch.ItemVibrator;
import com.cendrb.rododendron.item.charger.chargeable_tools.*;
import com.cendrb.rododendron.item.food.ItemOverchargedCookie;
import com.cendrb.rododendron.item.food.ItemPinkCookie;
import com.cendrb.rododendron.item.motion_core.ItemEnergizedMotionCore;
import com.cendrb.rododendron.item.motion_core.ItemPinkMotionCore;
import com.cendrb.rododendron.reference.Names;
import com.cendrb.rododendron.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {

    public static final ItemEnergizedIngot energizedIngot = new ItemEnergizedIngot();
    public static final ItemEnergizedCrystal energizedCrystal = new ItemEnergizedCrystal();

    public static final ItemPsimStaff psimStaff = new ItemPsimStaff();
    public static final ItemHomeSweetHome homeSweetHome = new ItemHomeSweetHome();
    public static final ItemScaffoldingBuilder scaffoldingBuilder = new ItemScaffoldingBuilder();
    public static final ItemAkbarGun akbarGun = new ItemAkbarGun();
    public static final ItemBuildersStaff buildersStaff = new ItemBuildersStaff();

    public static final ItemEvilSlimeball evilSlimeball = new ItemEvilSlimeball();
    public static final ItemGaySemen gaySemen = new ItemGaySemen();
    public static final ItemEnrichedGaySemen enrichedGaySemen = new ItemEnrichedGaySemen();
    public static final ItemPinkCrystal pinkCrystal = new ItemPinkCrystal();
    public static final ItemPinkDust pinkDust = new ItemPinkDust();
    public static final ItemPinkIngot pinkIngot = new ItemPinkIngot();
    public static final ItemUnstableSemenMash unstableSemenMash = new ItemUnstableSemenMash();
    public static final ItemPinkMotionCore pinkMotionCore = new ItemPinkMotionCore();
    public static final ItemEnergizedMotionCore energizedMotionCore = new ItemEnergizedMotionCore();
    public static final ItemDunPie dunPie = new ItemDunPie();
    public static final ItemPinkCookie pinkCookie = new ItemPinkCookie();
    public static final ItemOverchargedCookie overchargedCookie = new ItemOverchargedCookie();

    public static final ItemVibrator vibrator = new ItemVibrator();
    public static final ItemRightHand rightHand = new ItemRightHand();
    public static final ItemUselessWater uselessWater = new ItemUselessWater();


    public static void init()
    {
        GameRegistry.registerItem(evilSlimeball, Names.Items.evilSlimeball);
        GameRegistry.registerItem(gaySemen, Names.Items.gaySemen);
        GameRegistry.registerItem(enrichedGaySemen, Names.Items.enrichedGaySemen);
        GameRegistry.registerItem(pinkCrystal, Names.Items.pinkCrystal);
        GameRegistry.registerItem(pinkDust, Names.Items.pinkDust);
        GameRegistry.registerItem(pinkIngot, Names.Items.pinkIngot);
        GameRegistry.registerItem(unstableSemenMash, Names.Items.unstableSemenMash);
        GameRegistry.registerItem(pinkMotionCore, Names.Items.pinkMotionCore);
        GameRegistry.registerItem(energizedMotionCore, Names.Items.energizedMotionCore);
        GameRegistry.registerItem(dunPie, Names.Items.dunPie);
        GameRegistry.registerItem(pinkCookie, Names.Items.pinkCookie);
        GameRegistry.registerItem(overchargedCookie, Names.Items.overChargedCookie);

        GameRegistry.registerItem(energizedIngot, Names.Items.energizedIngot);
        GameRegistry.registerItem(energizedCrystal, Names.Items.energizedCrystal);

        GameRegistry.registerItem(psimStaff, Names.Items.psimStaff);
        GameRegistry.registerItem(homeSweetHome, Names.Items.homeSweetHome);
        GameRegistry.registerItem(scaffoldingBuilder, Names.Items.scaffoldingBuilder);
        GameRegistry.registerItem(akbarGun, Names.Items.akbarGun);
        GameRegistry.registerItem(buildersStaff, Names.Items.buildersStaff);

        GameRegistry.registerItem(vibrator, Names.Items.vibrator);
        GameRegistry.registerItem(rightHand, Names.Items.rightHand);
        GameRegistry.registerItem(uselessWater, Names.Items.uselessWater);
    }
}
