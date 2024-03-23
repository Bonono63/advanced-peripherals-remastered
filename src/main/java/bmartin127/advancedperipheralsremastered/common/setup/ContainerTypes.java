package bmartin127.advancedperipheralsremastered.common.setup;

import bmartin127.advancedperipheralsremastered.common.container.InventoryManagerContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.RegistryObject;

public class ContainerTypes {

    public static final RegistryObject<MenuType<InventoryManagerContainer>> INVENTORY_MANAGER_CONTAINER = Registration.CONTAINER_TYPES.register("memory_card_container", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        World level = inv.player.getCommandSenderWorld();
        return new InventoryManagerContainer(windowId, inv, pos, level);
    }));

    public static void register() {
    }
}
