package bmartin127.advancedperipheralsremastered.common.configuration;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.fml.config.ConfigFileTypeHandler;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;

import static net.minecraftforge.ForgeConfigAPIPort.CORE;

public class APConfig extends ModConfig {

    public static final ConfigFileHandler CONFIG_FILE_HANDLER = new ConfigFileHandler();

    public static final GeneralConfig GENERAL_CONFIG = new GeneralConfig();
    public static final PeripheralsConfig PERIPHERALS_CONFIG = new PeripheralsConfig();
    public static final MetaphysicsConfig METAPHYSICS_CONFIG = new MetaphysicsConfig();
    public static final WorldConfig WORLD_CONFIG = new WorldConfig();

    private static final Logger LOGGER = LogManager.getLogger();

    public APConfig(IAPConfig config, ModContainer container) {
        super(config.getType(), config.getConfigSpec(), container, "Advancedperipherals/" + config.getFileName() + ".toml");
    }

    public static void register(ModLoadingContext context) {
        //Creates the config folder
        getOrCreateGameRelativePath(FabricLoader.getInstance().getConfigDir(), "Advancedperipherals");

        ModContainer modContainer = context.getActiveContainer();
        modContainer.addConfig(new APConfig(GENERAL_CONFIG, modContainer));
        modContainer.addConfig(new APConfig(PERIPHERALS_CONFIG, modContainer));
        modContainer.addConfig(new APConfig(METAPHYSICS_CONFIG, modContainer));
        modContainer.addConfig(new APConfig(WORLD_CONFIG, modContainer));
    }

    /*
    *   getOrCreateGameRelativePath() and getOrCreateDirectory() are taken directly from the FileUtils.java below from the forge project
    *   https://github.com/MinecraftForge/MinecraftForge/blob/1.18.x/fmlloader/src/main/java/net/minecraftforge/fml/loading/FileUtils.java
    *
    *   I just didn't feel like rewriting something that already exists...
    * */
    public static Path getOrCreateGameRelativePath(Path path, String name) {
        return getOrCreateDirectory(FabricLoader.getInstance().getGameDir().resolve(path), name);
    }

    public static Path getOrCreateDirectory(Path dirPath, String dirLabel) {
        if (!Files.isDirectory(dirPath.getParent())) {
            getOrCreateDirectory(dirPath.getParent(), "parent of "+dirLabel);
        }
        if (!Files.isDirectory(dirPath))
        {
            LOGGER.debug(CORE,"Making {} directory : {}", dirLabel, dirPath);
            try {
                Files.createDirectory(dirPath);
            } catch (IOException e) {
                if (e instanceof FileAlreadyExistsException) {
                    LOGGER.fatal(CORE,"Failed to create {} directory - there is a file in the way", dirLabel);
                } else {
                    LOGGER.fatal(CORE,"Problem with creating {} directory (Permissions?)", dirLabel, e);
                }
                throw new RuntimeException("Problem creating directory", e);
            }
            LOGGER.debug(CORE,"Created {} directory : {}", dirLabel, dirPath);
        } else {
            LOGGER.debug(CORE,"Found existing {} directory : {}", dirLabel, dirPath);
        }
        return dirPath;
    }

    @Override
    public ConfigFileTypeHandler getHandler() {
        return CONFIG_FILE_HANDLER;
    }

    public static class ConfigFileHandler extends ConfigFileTypeHandler {

        public static Path getPath(Path path) {
            if (path.endsWith("serverconfig")) return FabricLoader.getInstance().getConfigDir();

            return path;
        }

        @Override
        public Function<ModConfig, CommentedFileConfig> reader(Path configBasePath) {
            return super.reader(getPath(configBasePath));
        }

        @Override
        public void unload(Path configBasePath, ModConfig config) {
            super.unload(getPath(configBasePath), config);
        }
    }
}
