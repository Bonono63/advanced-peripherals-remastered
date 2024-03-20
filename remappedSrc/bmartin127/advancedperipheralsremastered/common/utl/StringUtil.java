package bmartin127.advancedperipheralsremastered.common.utils;

import static bmartin127.advancedperipheralsremastered.Advancedperipherals.MODID;

public class StringUtil {
    public static String pocket(String name) {
        return String.format("pocket.%s.%s", MODID, name);
    }
}
