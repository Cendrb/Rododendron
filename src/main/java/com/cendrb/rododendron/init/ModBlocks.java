package com.cendrb.rododendron.init;

import com.cendrb.rododendron.block.*;
import com.cendrb.rododendron.block.danium.BlockEnergizedDanium;
import com.cendrb.rododendron.block.danium.BlockPinkDanium;
import com.cendrb.rododendron.block.rubber_dildo.BlockEnergizedRubberDildo;
import com.cendrb.rododendron.block.rubber_dildo.BlockPinkRubberDildo;
import com.cendrb.rododendron.block.speedy_semen.BlockEnergizedSpeedySemen;
import com.cendrb.rododendron.block.speedy_semen.BlockPinkSpeedySemen;
import com.cendrb.rododendron.reference.Names;
import com.cendrb.rododendron.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

    public static BlockGayHead  gayHead = new BlockGayHead();
    public static BlockAkbar akbar = new BlockAkbar();
    public static BlockAkbarCore akbarCore = new BlockAkbarCore();

    public static BlockScaffold scaffold = new BlockScaffold();

    public static BlockPinkDanium pinkDanium = new BlockPinkDanium();
    public static BlockEnergizedDanium energizedDanium = new BlockEnergizedDanium();

    public static BlockPinkSpeedySemen pinkSpeedySemen = new BlockPinkSpeedySemen();
    public static BlockEnergizedSpeedySemen energizedSpeedySemen = new BlockEnergizedSpeedySemen();

    public static BlockPinkRubberDildo pinkRubberDildo = new BlockPinkRubberDildo();
    public static BlockEnergizedRubberDildo energizedRubberDildo = new BlockEnergizedRubberDildo();

    public static BlockPinkIngot pinkIngotBlock = new BlockPinkIngot();
    public static BlockPinkCrystal pinkCrystalBlock = new BlockPinkCrystal();

    public static BlockCharger charger = new BlockCharger();
    public static BlockEnergizedIngot energizedIngot = new BlockEnergizedIngot();
    public static BlockEnergizedCrystal energizedCrystal = new BlockEnergizedCrystal();

    public static BlockAntinigga antinigga = new BlockAntinigga();
    public static BlockAkbarMasterRitualStone akbarMasterRitualStone = new BlockAkbarMasterRitualStone();

    public static void init()
    {
        GameRegistry.registerBlock(gayHead, Names.Blocks.gayHead);
        GameRegistry.registerBlock(akbar, Names.Blocks.akbar);
        GameRegistry.registerBlock(akbarCore, Names.Blocks.akbarCore);

        GameRegistry.registerBlock(scaffold, Names.Blocks.scaffold);

        GameRegistry.registerBlock(pinkDanium, Names.Blocks.pinkDanium);
        GameRegistry.registerBlock(energizedDanium, Names.Blocks.energizedDanium);

        GameRegistry.registerBlock(pinkSpeedySemen, Names.Blocks.pinkSpeedySemen);
        GameRegistry.registerBlock(energizedSpeedySemen, Names.Blocks.energizedSpeedySemen);

        GameRegistry.registerBlock(pinkRubberDildo, Names.Blocks.pinkRubberDildo);
        GameRegistry.registerBlock(energizedRubberDildo, Names.Blocks.energizedRubberDildo);

        GameRegistry.registerBlock(pinkIngotBlock, Names.Blocks.pinkIngotBlock);
        GameRegistry.registerBlock(pinkCrystalBlock, Names.Blocks.pinkCrystalBlock);

        GameRegistry.registerBlock(charger, Names.Blocks.charger);
        GameRegistry.registerBlock(energizedIngot, Names.Blocks.energizedIngotBlock);
        GameRegistry.registerBlock(energizedCrystal, Names.Blocks.energizedCrystalBlock);

        GameRegistry.registerBlock(antinigga, Names.Blocks.antinigga);
        GameRegistry.registerBlock(akbarMasterRitualStone, Names.Blocks.akbarMasterRitualStone);
    }
}
