package bmartin127.advancedperipheralsremastered.common.container;

import bmartin127.advancedperipheralsremastered.common.container.base.BaseContainer;
import bmartin127.advancedperipheralsremastered.common.setup.ContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.jetbrains.annotations.NotNull;

public class InventoryManagerContainer extends BaseContainer {

    public InventoryManagerContainer(int id, Inventory inventory, BlockPos pos, World world) {
        super(ContainerTypes.INVENTORY_MANAGER_CONTAINER.get(), id, inventory, pos, world);
        layoutPlayerInventorySlots(7, 84);
        if (tileEntity != null) {
            tileEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                addSlot(new SlotInputHandler(handler, 0, 79, 29, new SlotCondition().setNeededItem(Items.MEMORY_CARD.get()))); //Input
            });
        }
    }

    @Override
    public boolean stillValid(@NotNull PlayerEntity playerIn) {
        return true;
    }

    @NotNull
    @Override
    public ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index >= 36) {
                if (!this.moveItemStackTo(itemstack1, 0, 36, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (index <= 35) {
                if (itemstack1.getItem().equals(Items.MEMORY_CARD.get())) {
                    if (!this.moveItemStackTo(itemstack1, 36, 37, true)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, 36, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }
}
