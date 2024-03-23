package bmartin127.advancedperipheralsremastered.client;

import bmartin127.advancedperipheralsremastered.AdvancedPeripherals;
import dan200.computercraft.api.client.ComputerCraftAPIClient;
import dan200.computercraft.api.client.turtle.TurtleUpgradeModeller;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;

public class ClientRegistry implements ClientModInitializer {

    private static final String[] TURTLE_MODELS = new String[]{"turtle_chat_box_upgrade_left", "turtle_chat_box_upgrade_right", "turtle_environment_upgrade_left", "turtle_environment_upgrade_right", "turtle_player_upgrade_left", "turtle_player_upgrade_right", "turtle_geoscanner_upgrade_left", "turtle_geoscanner_upgrade_right"};

    public static void registerModels(ModelEvent.RegisterAdditional event) {
        for (String model : TURTLE_MODELS) {
            event.register(new ModelResourceLocation(new Identifier(AdvancedPeripherals.MOD_ID, model), "inventory"));
        }
    }

    //public static void onClientSetup(RegisterKeyMappingsEvent event) {
    //    KeyBindings.register(event);
    //}

    @Override
    public void onInitializeClient() {
        HandledScreens.register(de.srendi.advancedperipherals.common.setup.ContainerTypes.INVENTORY_MANAGER_CONTAINER.get(), InventoryManagerScreen::new);

        ComputerCraftAPIClient.registerTurtleUpgradeModeller(CCRegistration.CHUNKY_TURTLE.get(), TurtleUpgradeModeller.flatItem());
        ComputerCraftAPIClient.registerTurtleUpgradeModeller(CCRegistration.COMPASS_TURTLE.get(), TurtleUpgradeModeller.flatItem());
        ComputerCraftAPIClient.registerTurtleUpgradeModeller(CCRegistration.CHAT_BOX_TURTLE.get(), TurtleUpgradeModeller.sided(new ModelResourceLocation(AdvancedPeripherals.getRL("turtle_chat_box_upgrade_left"), "inventory"), new ModelResourceLocation(AdvancedPeripherals.getRL("turtle_chat_box_upgrade_right"), "inventory")));
        ComputerCraftAPIClient.registerTurtleUpgradeModeller(CCRegistration.ENVIRONMENT_TURTLE.get(), TurtleUpgradeModeller.sided(new ModelResourceLocation(AdvancedPeripherals.getRL("turtle_environment_upgrade_left"), "inventory"), new ModelResourceLocation(AdvancedPeripherals.getRL("turtle_environment_upgrade_right"), "inventory")));
        ComputerCraftAPIClient.registerTurtleUpgradeModeller(CCRegistration.GEO_SCANNER_TURTLE.get(), TurtleUpgradeModeller.sided(new ModelResourceLocation(AdvancedPeripherals.getRL("turtle_geoscanner_upgrade_left"), "inventory"), new ModelResourceLocation(AdvancedPeripherals.getRL("turtle_geoscanner_upgrade_right"), "inventory")));
        ComputerCraftAPIClient.registerTurtleUpgradeModeller(CCRegistration.PLAYER_DETECTOR_TURTLE.get(), TurtleUpgradeModeller.sided(new ModelResourceLocation(AdvancedPeripherals.getRL("turtle_player_upgrade_left"), "inventory"), new ModelResourceLocation(AdvancedPeripherals.getRL("turtle_player_upgrade_right"), "inventory")));
        ComputerCraftAPIClient.registerTurtleUpgradeModeller(CCRegistration.OP_END_TURTLE.get(), new MetaTurtleUpgradeModeller<>());
        ComputerCraftAPIClient.registerTurtleUpgradeModeller(CCRegistration.OP_HUSBANDRY_TURTLE.get(), new MetaTurtleUpgradeModeller<>());
        ComputerCraftAPIClient.registerTurtleUpgradeModeller(CCRegistration.OP_WEAK_TURTLE.get(), new MetaTurtleUpgradeModeller<>());
        ComputerCraftAPIClient.registerTurtleUpgradeModeller(CCRegistration.HUSBANDRY_TURTLE.get(), new MetaTurtleUpgradeModeller<>());
        ComputerCraftAPIClient.registerTurtleUpgradeModeller(CCRegistration.END_TURTLE.get(), new MetaTurtleUpgradeModeller<>());
        ComputerCraftAPIClient.registerTurtleUpgradeModeller(CCRegistration.WEAK_TURTLE.get(), new MetaTurtleUpgradeModeller<>());
    }

    //TODO change the icon of the curio icon
    /*@SubscribeEvent
    public static void onTextureStitching(TextureStitchEvent.Pre event) {
        event.addSprite(new ResourceLocation(AdvancedPeripherals.MOD_ID, "item/empty_glasses_slot"));
    }*/
}
