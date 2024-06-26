package bmartin127.advancedperipheralsremastered.lib.peripherals;

import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

public interface IPeripheralPlugin {
    default List<BoundMethod> getMethods() {
        return ServerContext.get(ServerLifecycleHooks.getCurrentServer()).peripheralMethods().getSelfMethods(this).entrySet().stream().map(
                entry -> new BoundMethod(this, entry.getKey(), entry.getValue())
        ).collect(Collectors.toList());
    }

    default @Nullable IPeripheralOperation<?>[] getOperations() {
        return null;
    }

    default boolean isSuitable(IPeripheral peripheral) {
        return true;
    }
}
