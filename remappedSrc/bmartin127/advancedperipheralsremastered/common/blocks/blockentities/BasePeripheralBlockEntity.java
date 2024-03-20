package bmartin127.advancedperipheralsremastered.common.blocks.blockentities;


import dan200.computercraft.api.peripheral.IComputerAccess;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static bmartin127.advancedperipheralsremastered.Advancedperipherals.LOGGER;


public class BasePeripheralBlockEntity extends BlockEntity {

    public final List<IComputerAccess> computers = new ArrayList<>();
    private final Map<String, Object> events = new HashMap<>();
    public BasePeripheralBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);

    }

    public <T extends BlockEntity> void tick(World level, BlockPos pos, BlockState state, T be) {
        if (level.isClient()) return;

        if (events.isEmpty()) return;

        for (IComputerAccess computer : computers) {
            computer.queueEvent((String) events.keySet().toArray()[events.size() - 1], events.values().toArray()[events.size() - 1]);
        }

        events.clear();
        queueEvent(level, pos);
    }

    public void queueEvent(World level, BlockPos pos) {
        // Implementation of queueEvent
    }
}
