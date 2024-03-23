package bmartin127.advancedperipheralsremastered.common.configuration;

import bmartin127.advancedperipheralsremastered.lib.misc.IConfigHandler;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public interface IAPConfig {

    ForgeConfigSpec getConfigSpec();

    String getFileName();

    ModConfig.Type getType();

    default void register(IConfigHandler[] data, final ForgeConfigSpec.Builder builder) {
        for (IConfigHandler handler : data) {
            handler.addToConfig(builder);
        }
    }

    default void pop(String name, ForgeConfigSpec.Builder builder) {
        builder.pop();
        builder.push(name);
    }
}
