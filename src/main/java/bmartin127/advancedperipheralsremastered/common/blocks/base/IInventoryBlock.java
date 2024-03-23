package bmartin127.advancedperipheralsremastered.common.blocks.base;


import bmartin127.advancedperipheralsremastered.common.container.base.BaseContainer;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Used to assign a container to a TileEntity
 *
 * @param <T> The container related to this inventory
 * @deprecated Will be merged with the APBlock in 0.9
 */

@Deprecated(since = "0.7.16", forRemoval = true)
public interface IInventoryBlock<T extends BaseContainer> {

    Component getDisplayName();

    T createContainer(int id, Inventory playerInventory, BlockPos pos, World world);

    int getInvSize();
}
