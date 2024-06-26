package bmartin127.advancedperipheralsremastered.lib.peripherals;

import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.core.asm.PeripheralMethod;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BoundMethod {
    private final Object target;
    private final String name;
    private final PeripheralMethod method;

    public BoundMethod(@NotNull Object target, @NotNull String name, @NotNull PeripheralMethod method) {
        this.target = target;
        this.name = name;
        this.method = method;
    }

    @NotNull
    public MethodResult apply(@NotNull IComputerAccess access, @NotNull ILuaContext context, @NotNull IArguments arguments) throws LuaException {
        return method.apply(target, context, access, arguments);
    }

    @NotNull
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoundMethod boundMethod)) return false;
        return target.equals(boundMethod.target) && name.equals(boundMethod.name) && method.equals(boundMethod.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(target, name, method);
    }
}
