package bmartin127.advancedperipheralsremastered;

import de.srendi.advancedperipherals.common.addons.APAddons;
import de.srendi.advancedperipherals.common.configuration.APConfig;
import de.srendi.advancedperipherals.common.setup.Registration;

import de.srendi.advancedperipherals.network.APNetworking;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Identifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class AdvancedPeripherals implements ModInitializer {

    public static final String MOD_ID = "advancedperipherals";
    public static final String NAME = "Advanced Peripherals";
    public static final Logger LOGGER = LogManager.getLogger(NAME);
    public static final Random RANDOM = new Random();

    public AdvancedPeripherals() {
        LOGGER.info("AdvancedPeripherals says hello!");
        //IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        //APConfig.register(ModLoadingContext.get());

        //modBus.addListener(this::commonSetup);

        //MinecraftForge.EVENT_BUS.register(this);
    }

    /*
    public static void debug(String message) {
        if (APConfig.GENERAL_CONFIG.enableDebugMode.get())
            LOGGER.debug("[DEBUG] {}", message);
    }

    public static void debug(String message, Level level) {
        if (APConfig.GENERAL_CONFIG.enableDebugMode.get())
            LOGGER.log(level, "[DEBUG] {}", message);
    }*/

    public static Identifier getID(String resource) {
        return new Identifier(MOD_ID, resource);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        APAddons.commonSetup();
        APNetworking.init();
    }

    @Override
    public void onInitialize() {
        Registration.register();
    }
}
