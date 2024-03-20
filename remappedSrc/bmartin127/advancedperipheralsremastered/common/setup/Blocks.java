package bmartin127.advancedperipheralsremastered.common.setup;

import bmartin127.advancedperipheralsremastered.APCreativeTab;
import dan200.computercraft.api.ComputerCraftAPI;
import bmartin127.advancedperipheralsremastered.Advancedperipherals;
import bmartin127.advancedperipheralsremastered.common.blocks.GeoScannerBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


import static bmartin127.advancedperipheralsremastered.Advancedperipherals.LOGGER;
import static bmartin127.advancedperipheralsremastered.Advancedperipherals.MODID;

import static bmartin127.advancedperipheralsremastered.integrations.cc.AdvancedPeripheralsBlockEntityTypes.GEO_SCANNER_BLOCK_ENTITY;


public class Blocks {

    public static final Block ENVIRONMENT_DETECTOR = registerBlock("environment_detector",
            new GeoScannerBlock(FabricBlockSettings.of(Material.METAL)), APCreativeTab.TAB);
    public static final Block CHAT_BOX = registerBlock("chat_box",
            new GeoScannerBlock(FabricBlockSettings.of(Material.METAL)), APCreativeTab.TAB);
    public static final Block PLAYER_DETECTOR = registerBlock("player_detector",
            new GeoScannerBlock(FabricBlockSettings.of(Material.METAL)), APCreativeTab.TAB);
    public static final Block ME_BRIDGE = registerBlock("me_bridge",
            new GeoScannerBlock(FabricBlockSettings.of(Material.METAL)), APCreativeTab.TAB);
    public static final Block RS_BRIDGE = registerBlock("rs_bridge",
            new GeoScannerBlock(FabricBlockSettings.of(Material.METAL)), APCreativeTab.TAB);
    public static final Block ENERGY_DETECTOR = registerBlock("energy_detector",
            new GeoScannerBlock(FabricBlockSettings.of(Material.METAL)), APCreativeTab.TAB);
    public static final Block PERIPHERAL_CASING = registerBlock("peripheral_casing",
            new GeoScannerBlock(FabricBlockSettings.of(Material.METAL)), APCreativeTab.TAB);
    public static final Block INVENTORY_MANAGER = registerBlock("inventory_manager",
            new GeoScannerBlock(FabricBlockSettings.of(Material.METAL)), APCreativeTab.TAB);
    public static final Block REDSTONE_INTEGRATOR = registerBlock("redstone_integrator",
            new GeoScannerBlock(FabricBlockSettings.of(Material.METAL)), APCreativeTab.TAB);
    public static final Block BLOCK_READER = registerBlock("block_reader",
            new GeoScannerBlock(FabricBlockSettings.of(Material.METAL)), APCreativeTab.TAB);
    public static final Block GEO_SCANNER = registerBlock("geo_scanner",
            new GeoScannerBlock(FabricBlockSettings.of(Material.METAL)), APCreativeTab.TAB);
    public static final Block COLONY_INTEGRATOR = registerBlock("colony_integrator",
            new GeoScannerBlock(FabricBlockSettings.of(Material.METAL)), APCreativeTab.TAB);
    public static final Block NBT_STORAGE = registerBlock("nbt_storage",
            new GeoScannerBlock(FabricBlockSettings.of(Material.METAL)), APCreativeTab.TAB);
    public static final Block AR_CONTROLLER = registerBlock("ar_controller",
            new GeoScannerBlock(FabricBlockSettings.of(Material.METAL)), APCreativeTab.TAB);




    private static Block registerBlockNoItem(String name, Block block, ItemGroup group){
        return Registry.register(Registry.BLOCK, new Identifier(MODID,name), block);
    }
    private static Block registerBlock(String name, Block block, ItemGroup group) {
        Item item = Registry.register(Registry.ITEM, new Identifier(MODID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
        return registerBlockNoItem(name,block,group);

    }
    public static ItemStack getBlockItemStack(Block block) {
        Item blockItem = Item.BLOCK_ITEMS.get(block);
        return new ItemStack(blockItem);
    }
    public static void registerModBlocks() {
        LOGGER.info("Registering Blocks for " + MODID);
        LOGGER.info("Registered" + GEO_SCANNER);
        LOGGER.info("Block Entity " + GEO_SCANNER_BLOCK_ENTITY);
    }
    public static void registerModItems() {
    //    ComputerCraftAPI.registerPocketUpgrade(IPocketUpgrade);
    }
}
