package name.modid.common.utl;

import static name.modid.Advancedperipherals.MODID;

public class StringUtil {
    public static String pocket(String name) {
        return String.format("pocket.%s.%s", MODID, name);
    }
}
