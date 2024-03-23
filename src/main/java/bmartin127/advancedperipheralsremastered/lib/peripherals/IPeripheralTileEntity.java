package bmartin127.advancedperipheralsremastered.lib.peripherals;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public interface IPeripheralTileEntity {
    NbtCompound getPeripheralSettings();

    void markSettingsChanged();

    default <T extends BlockEntity> void handleTick(World world, BlockState state, BlockEntityType<T> type) {

    }

}
