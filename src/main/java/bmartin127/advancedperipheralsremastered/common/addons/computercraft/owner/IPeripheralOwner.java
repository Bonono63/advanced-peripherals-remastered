package bmartin127.advancedperipheralsremastered.common.addons.computercraft.owner;

import net.minecraft.block.enums.JigsawOrientation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.function.Function;

public interface IPeripheralOwner {

    @Nullable String getCustomName();

    @Nullable World getWorld();

    @NotNull BlockPos getPos();

    @NotNull Direction getFacing();

    @NotNull JigsawOrientation getOrientation();

    @Nullable PlayerEntity getOwner();

    @NotNull NbtCompound getDataStorage();

    void markDataStorageDirty();

    <T> T withPlayer(Function<de.srendi.advancedperipherals.common.util.fakeplayer.APFakePlayer, T> function);

    ItemStack getToolInMainHand();

    ItemStack storeItem(ItemStack stored);

    void destroyUpgrade();

    boolean isMovementPossible(@NotNull World world, @NotNull BlockPos pos);

    boolean move(@NotNull World world, @NotNull BlockPos pos);

    <T extends de.srendi.advancedperipherals.common.addons.computercraft.owner.IOwnerAbility> void attachAbility(PeripheralOwnerAbility<T> ability, T abilityImplementation);

    @Nullable <T extends IOwnerAbility> T getAbility(PeripheralOwnerAbility<T> ability);

    Collection<IOwnerAbility> getAbilities();

    default void attachOperation(IPeripheralOperation<?>... operations) {
        OperationAbility operationAbility = new OperationAbility(this);
        attachAbility(PeripheralOwnerAbility.OPERATION, operationAbility);
        for (IPeripheralOperation<?> operation : operations)
            operationAbility.registerOperation(operation);
    }

    default void attachOperation(Collection<IPeripheralOperation<?>> operations) {
        OperationAbility operationAbility = new OperationAbility(this);
        attachAbility(PeripheralOwnerAbility.OPERATION, operationAbility);
        for (IPeripheralOperation<?> operation : operations)
            operationAbility.registerOperation(operation);
    }
}
