package bmartin127.advancedperipheralsremastered.common.item.pocket;


import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.pocket.AbstractPocketUpgrade;
import dan200.computercraft.api.pocket.IPocketAccess;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import static bmartin127.advancedperipheralsremastered.common.utils.StringUtil.pocket;

public abstract class BasePocketUpgrade<T extends IPeripheral> extends AbstractPocketUpgrade{

    private T peripheral;


    protected BasePocketUpgrade(Identifier id,  ItemStack stack) {

        super(id, pocket(id.getPath()) , stack);
    }

    protected abstract T getPeripheral(IPocketAccess access);




    @Nullable
    @Override
    public IPeripheral createPeripheral(@NotNull IPocketAccess access) {
        peripheral = getPeripheral(access);
        return peripheral;
    }
}
