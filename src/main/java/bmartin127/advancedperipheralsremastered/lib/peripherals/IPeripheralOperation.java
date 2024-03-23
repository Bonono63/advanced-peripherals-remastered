package bmartin127.advancedperipheralsremastered.lib.peripherals;


import bmartin127.advancedperipheralsremastered.lib.misc.IConfigHandler;

import java.util.Map;

public interface IPeripheralOperation<T> extends IConfigHandler {
    int getInitialCooldown();

    int getCooldown(T context);

    int getCost(T context);

    Map<String, Object> computerDescription();
}
