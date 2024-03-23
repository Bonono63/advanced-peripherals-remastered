package bmartin127.advancedperipheralsremastered.common.configuration;

import bmartin127.advancedperipheralsremastered.lib.LibConfig;
import net.minecraft.util.annotation.ClientFieldsAreNonnullByDefault;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

@ClientFieldsAreNonnullByDefault
public class GeneralConfig implements IAPConfig {

    public final ForgeConfigSpec.BooleanValue enableDebugMode;
    private final ForgeConfigSpec configSpec;

    GeneralConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("Config to adjust general mod settings").push("General");

        enableDebugMode = builder.comment("Enables the debug mode. Only enable it if needed.").define("enableDebugMode", false);

        builder.pop();
        builder.push("Core");

        LibConfig.build(builder);

        builder.pop();

        configSpec = builder.build();
    }


    @Override
    public ForgeConfigSpec getConfigSpec() {
        return configSpec;
    }

    @Override
    public String getFileName() {
        return "general";
    }

    @Override
    public ModConfig.Type getType() {
        return ModConfig.Type.COMMON;
    }
}
