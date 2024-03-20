package bmartin127.advancedperipheralsremastered.common.item.pocket;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import dan200.computercraft.api.pocket.IPocketAccess;
import bmartin127.advancedperipheralsremastered.common.peripherals.GeoScannerPeripheral;
import dan200.computercraft.ComputerCraftAPIImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PocketGeoScannerUpgrade extends BasePocketUpgrade<GeoScannerPeripheral> {

    public PocketGeoScannerUpgrade(Identifier id, ItemStack stack) {

        super(id, stack);
    }

    @Nullable
    @Override
    public GeoScannerPeripheral getPeripheral(@NotNull IPocketAccess iPocketAccess) {
        return new GeoScannerPeripheral(iPocketAccess);
    }
}
