package bmartin127.advancedperipheralsremastered.common.setup;

import bmartin127.advancedperipheralsremastered.APCreativeTab;
import bmartin127.advancedperipheralsremastered.Advancedperipherals;
import bmartin127.advancedperipheralsremastered.common.item.pocket.PocketGeoScannerUpgrade;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import static bmartin127.advancedperipheralsremastered.Advancedperipherals.MODID;

public class Items {

    public static final Item CHUNK_CONTROLLER = registerItem("chunk_controller",
            new Item(new FabricItemSettings().group(APCreativeTab.TAB)));

    public static final Item AR_GOGGLES = registerItem("ar_goggles",
            new Item(new FabricItemSettings().group(APCreativeTab.TAB)));
    public static final Item COMPUTER_TOOL = registerItem("computer_tool",
            new Item(new FabricItemSettings().group(APCreativeTab.TAB)));
    public static final Item MEMORY_CARD = registerItem("memory_card",
            new Item(new FabricItemSettings().group(APCreativeTab.TAB)));
    public static final Item END_AUTOMATA_CORE = registerItem("end_automata_core",
            new Item(new FabricItemSettings().group(APCreativeTab.TAB)));
    public static final Item HUSBANDRY_AUTOMATA_CORE = registerItem("husbandry_automata_core",
            new Item(new FabricItemSettings().group(APCreativeTab.TAB)));
    public static final Item WEAK_AUTOMATA_CORE = registerItem("weak_automata_core",
            new Item(new FabricItemSettings().group(APCreativeTab.TAB)));
    public static final Item OVERPOWERED_WEAK_AUTOMATA_CORE = registerItem("overpowered_weak_automata_core",
            new Item(new FabricItemSettings().group(APCreativeTab.TAB)));
    public static final Item OVERPOWERED_END_AUTOMATA_CORE  = registerItem("overpowered_end_automata_core",
            new Item(new FabricItemSettings().group(APCreativeTab.TAB)));
    public static final Item OVERPOWERED_HUSBANDRY_AUTOMATA_CORE  = registerItem("overpowered_husbandry_automata_core",
            new Item(new FabricItemSettings().group(APCreativeTab.TAB)));






    public static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(MODID, name), item);
    }



    public static void registerModItems() {
        Advancedperipherals.LOGGER.info("Registering Mod items for advancedperipherals");
    }
}
