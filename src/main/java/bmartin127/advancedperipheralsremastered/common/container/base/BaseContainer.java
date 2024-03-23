package bmartin127.advancedperipheralsremastered.common.container.base;

import bmartin127.advancedperipheralsremastered.common.blocks.base.PeripheralBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.Nullable;

public abstract class BaseContainer extends ScreenHandler {

    private final IItemHandler inventory;
    protected PeripheralBlockEntity<?> tileEntity;

    protected BaseContainer(@Nullable ScreenHandlerType<?> type, int id, Inventory inventory, BlockPos pos, World world) {
        super(type, id);
        this.inventory = new InvWrapper(inventory);
        if (world != null)
            this.tileEntity = (PeripheralBlockEntity<?>) world.getBlockEntity(pos);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index >= 36) {
                if (!this.insertItem(itemstack, 0, 36, false) && !tileEntity.canTakeItemThroughFace(index, itemstack, Direction.NORTH)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemstack, itemstack1);
            } else {
                if (!this.moveItemStackTo(itemstack, 36, getStacks().size(), false) && !tileEntity.canPlaceItemThroughFace(index, itemstack, Direction.NORTH)) {
                    return ItemStack.EMPTY;
                }
            }

            if (itemstack.getCount() == 0) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            slot.onTake(playerIn, itemstack);
        }

        return itemstack;
    }


    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int i = 0; i < verAmount; i++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    public void layoutPlayerInventorySlots(int leftCol, int topRow) {
        addSlotBox(inventory, 9, leftCol, topRow, 9, 18, 3, 18);

        topRow += 58;
        addSlotRange(inventory, 0, leftCol, topRow, 9, 18);
    }

    class InvWrapper
    {
        private final Inventory inventory;

        public InvWrapper(Inventory inv)
        {
            inventory = inv;
        }



    }

}
