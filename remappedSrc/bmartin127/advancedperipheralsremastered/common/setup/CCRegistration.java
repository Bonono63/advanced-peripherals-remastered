package bmartin127.advancedperipheralsremastered.common.setup;

import bmartin127.advancedperipheralsremastered.Advancedperipherals;
import bmartin127.advancedperipheralsremastered.common.item.pocket.PocketGeoScannerUpgrade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import dan200.computercraft.api.ComputerCraftAPI;
import static bmartin127.advancedperipheralsremastered.Advancedperipherals.MODID;
import static bmartin127.advancedperipheralsremastered.common.setup.Blocks.getBlockItemStack;
import static dan200.computercraft.shared.Registry.ModItems.POCKET_COMPUTER_NORMAL;

import dan200.computercraft.shared.PocketUpgrades;

public class CCRegistration {
    public static PocketGeoScannerUpgrade geoScannerUpgrade = new PocketGeoScannerUpgrade(CCRegistration.ID.GEOSCANNER_POCKET, getBlockItemStack(Blocks.GEO_SCANNER));



    public static class ID {
        public static final Identifier GEOSCANNER_POCKET = new Identifier(MODID, "geoscanner_pocket");
    }
    public static void registerModPocketItems() {
        Advancedperipherals.LOGGER.info("Registering pocket upgrades for advancedperipherals");
        Advancedperipherals.LOGGER.info(String.valueOf(geoScannerUpgrade));
        //--------------------


    }
}
