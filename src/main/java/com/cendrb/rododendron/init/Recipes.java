package com.cendrb.rododendron.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by cendr_000 on 3. 7. 2015.
 */
public class Recipes {
    public static void init()
    {
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.akbar), "GDG", "DGD", "GDG", 'G', Items.gunpowder, 'D', ModItems.pinkDust);
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.akbarCore), "ACA", "CAC", "ACA", 'A', Item.getItemFromBlock(ModBlocks.akbar), 'C', ModItems.pinkCrystal);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pinkIngot), Items.iron_ingot, ModItems.pinkDust);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.pinkCrystal), "CCC", "CDC", "CCC", 'D', Items.diamond, 'C', ModItems.pinkDust);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.enrichedGaySemen), "DDD", "DSD", "DDD", 'D', ModItems.pinkDust, 'S', ModItems.gaySemen);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.pinkCookie), "DDD", "DCD", "DDD", 'D', ModItems.pinkDust, 'C', Items.cookie);

        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.gayHead), "IBI", "BCB", "IBI", 'I', ModItems.pinkIngot, 'B', Item.getItemFromBlock(ModBlocks.pinkIngotBlock), 'C', Item.getItemFromBlock(ModBlocks.pinkCrystalBlock));
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.dunPie, 2), "ICI", "CBC", "ICI", 'I', ModItems.pinkIngot, 'C', ModItems.pinkCrystal, 'B', Items.bowl);

        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.pinkCrystalBlock), "CCC", "CCC", "CCC", 'C', ModItems.pinkCrystal);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pinkCrystal, 9), Item.getItemFromBlock(ModBlocks.pinkCrystalBlock));

        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.pinkIngotBlock), "III", "III", "III", 'I', ModItems.pinkIngot);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pinkIngot, 9), Item.getItemFromBlock(ModBlocks.pinkIngotBlock));

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.pinkMotionCore, 4), " I ", "IPI", " I ", 'I', ModItems.pinkIngot, 'P', Item.getItemFromBlock(Blocks.piston));
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.pinkDanium), "SSS", " M ", "PPP", 'M', ModItems.pinkMotionCore, 'S', Items.slime_ball, 'P', Item.getItemFromBlock(Blocks.piston));
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.pinkRubberDildo, 2), "SSS", "SSS", "PMP", 'M', ModItems.pinkMotionCore, 'S', Items.slime_ball, 'P', Item.getItemFromBlock(Blocks.piston));
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.pinkSpeedySemen), "SSS", "UUU", "UMU", 'S', Items.slime_ball, 'U', Items.sugar, 'M', ModItems.pinkMotionCore);

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.energizedMotionCore, 4), " I ", "IPI", " I ", 'I', ModItems.energizedIngot, 'P', Item.getItemFromBlock(Blocks.piston));
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.energizedDanium), "SSS", " M ", "PPP", 'M', ModItems.energizedMotionCore, 'S', Items.slime_ball, 'P', Item.getItemFromBlock(Blocks.piston));
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.energizedRubberDildo, 2), "SSS", "SSS", "PMP", 'M', ModItems.energizedMotionCore, 'S', Items.slime_ball, 'P', Item.getItemFromBlock(Blocks.piston));
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.energizedSpeedySemen), "SSS", "UUU", "UMU", 'S', Items.slime_ball, 'U', Items.sugar, 'M', ModItems.energizedMotionCore);

        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.charger), "CCC", "CAC", "BGB", 'C', ModItems.pinkCrystal, 'B', ModBlocks.pinkIngotBlock, 'G', Blocks.glowstone, 'A', ModBlocks.akbarCore);

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.unstableSemenMash), "SIS", "IAI", "SIS", 'S', ModItems.enrichedGaySemen, 'I', ModItems.energizedIngot, 'A', ModBlocks.akbarCore);

        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.energizedCrystal), "CCC", "CCC", "CCC", 'C', ModItems.energizedCrystal);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.energizedCrystal, 9), Item.getItemFromBlock(ModBlocks.energizedCrystal));

        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.energizedIngot), "III", "III", "III", 'I', ModItems.energizedIngot);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.energizedIngot, 9), Item.getItemFromBlock(ModBlocks.energizedIngot));

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.psimStaff), " CC", " PC", "B  ", 'C', ModItems.energizedCrystal, 'P', Items.ender_pearl, 'B', ModBlocks.energizedIngot);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.homeSweetHome), " CC", " PC", "B  ", 'C', ModItems.energizedCrystal, 'P', Items.bed, 'B', ModBlocks.energizedIngot);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.scaffoldingBuilder), " CC", " PC", "B  ", 'C', ModItems.energizedCrystal, 'P', Blocks.cobblestone, 'B', ModBlocks.energizedIngot);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.akbarGun), " CC", " PC", "B  ", 'C', ModItems.energizedCrystal, 'P', ModBlocks.akbar, 'B', ModBlocks.energizedIngot);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.buildersStaff), " CC", " PC", "B  ", 'C', ModItems.energizedCrystal, 'P', Blocks.brick_block, 'B', ModBlocks.energizedIngot);

        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.antinigga, 8), "SSS", "SIS", "SSS", 'S', Blocks.stone, 'I', Items.iron_ingot);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.adhanPipe), " GA", "GSG", "SG ", 'S', Items.stick, 'A', ModBlocks.akbar, 'G', Items.gold_nugget);
    }
}
