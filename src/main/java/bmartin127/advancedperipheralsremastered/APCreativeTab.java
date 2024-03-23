package bmartin127.advancedperipheralsremastered;

import de.srendi.advancedperipherals.common.setup.Blocks;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class APCreativeTab {

    public static final ItemGroup ADVANCED_PERIPHERALS = FabricItemGroupBuilder.create(new Identifier(AdvancedPeripherals.MOD_ID, "advanced_peripherals"))
            .icon(() -> new ItemStack(Blocks.CHAT_BOX))
            .build();

/*
    public static void populateCreativeTabBuilder(CreativeModeTab.Builder builder) {
        builder.displayItems((set, out) -> {
            Registration.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(out::accept);
            out.acceptAll(pocketUpgrade(CCRegistration.ID.COLONY_POCKET));
            out.acceptAll(pocketUpgrade(CCRegistration.ID.CHATTY_POCKET));
            out.acceptAll(pocketUpgrade(CCRegistration.ID.PLAYER_POCKET));
            out.acceptAll(pocketUpgrade(CCRegistration.ID.ENVIRONMENT_POCKET));
            out.acceptAll(pocketUpgrade(CCRegistration.ID.GEOSCANNER_POCKET));

            out.acceptAll(turtleUpgrade(CCRegistration.ID.CHATTY_TURTLE));
            out.acceptAll(turtleUpgrade(CCRegistration.ID.CHUNKY_TURTLE));
            out.acceptAll(turtleUpgrade(CCRegistration.ID.COMPASS_TURTLE));
            out.acceptAll(turtleUpgrade(CCRegistration.ID.PLAYER_TURTLE));
            out.acceptAll(turtleUpgrade(CCRegistration.ID.ENVIRONMENT_TURTLE));
            out.acceptAll(turtleUpgrade(CCRegistration.ID.GEOSCANNER_TURTLE));

            out.acceptAll(turtleUpgrade(CCRegistration.ID.WEAK_AUTOMATA));
            out.acceptAll(turtleUpgrade(CCRegistration.ID.OP_WEAK_AUTOMATA));
            out.acceptAll(turtleUpgrade(CCRegistration.ID.HUSBANDRY_AUTOMATA));
            out.acceptAll(turtleUpgrade(CCRegistration.ID.OP_HUSBANDRY_AUTOMATA));
            out.acceptAll(turtleUpgrade(CCRegistration.ID.END_AUTOMATA));
            out.acceptAll(turtleUpgrade(CCRegistration.ID.OP_END_AUTOMATA));
        });
        builder.icon(() -> new ItemStack(Blocks.CHAT_BOX.get()));
        builder.title(Component.translatable("advancedperipherals.name"));
    }

    private static Collection<ItemStack> pocketUpgrade(ResourceLocation pocketId) {
        return Set.of(ItemUtil.makePocket(ItemUtil.POCKET_NORMAL, pocketId.toString()),
                ItemUtil.makePocket(ItemUtil.POCKET_ADVANCED, pocketId.toString()));
    }

    private static Collection<ItemStack> turtleUpgrade(ResourceLocation pocketId) {
        return Set.of(ItemUtil.makeTurtle(ItemUtil.TURTLE_NORMAL, pocketId.toString()),
                ItemUtil.makeTurtle(ItemUtil.TURTLE_ADVANCED, pocketId.toString()));
    }*/



}
