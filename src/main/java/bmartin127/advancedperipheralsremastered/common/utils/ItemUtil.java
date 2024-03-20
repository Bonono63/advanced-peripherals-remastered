package bmartin127.advancedperipheralsremastered.common.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.item.Item;
import dan200.computercraft.shared.Registry;


public class ItemUtil {


    public static ItemStack makePocket(Item pocket, String upgrade) {
        ItemStack stack = new ItemStack(pocket);
        stack.getOrCreateNbt().putString("Upgrade", upgrade);
        return stack;
    }

    public static final Item POCKET_NORMAL = Registry.ModItems.POCKET_COMPUTER_NORMAL;
    public static final Item POCKET_ADVANCED = Registry.ModItems.POCKET_COMPUTER_ADVANCED;

    public static void addComputerItemToTab(Identifier pocketID, DefaultedList<ItemStack> items) {

        if (pocketID != null) {
            items.add(makePocket(POCKET_ADVANCED, pocketID.toString()));
            items.add(makePocket(POCKET_NORMAL, pocketID.toString()));
        }
    }

}
