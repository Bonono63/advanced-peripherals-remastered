package bmartin127.advancedperipheralsremastered;

import bmartin127.advancedperipheralsremastered.common.setup.Blocks;
import bmartin127.advancedperipheralsremastered.common.setup.Items;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;

import static bmartin127.advancedperipheralsremastered.Advancedperipherals.MODID;


public class APCreativeTab {
        
        public static final ItemGroup TAB = FabricItemGroupBuilder.create(new Identifier(MODID, "tab"))
                .icon(() -> new ItemStack(Blocks.CHAT_BOX))
                .build();

        public static void registerCreativeTab() {
        }

        public APCreativeTab() {}
}
