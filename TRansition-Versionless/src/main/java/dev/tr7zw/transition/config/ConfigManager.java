package dev.tr7zw.transition.config;

import com.google.gson.*;
import org.apache.logging.log4j.*;

import java.io.*;
import java.nio.file.*;
import java.util.function.*;

public class ConfigManager<T> {

    private static final Logger LOGGER = LogManager.getLogger();
    private T config;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final File configFile;
    private final Supplier<T> instanceProvider;
    private final ConfigUpgrader configUpgrader;

    public ConfigManager(String name, Supplier<T> instanceProvider, ConfigUpgrader<T> configUpgrader) {
        this.instanceProvider = instanceProvider;
        this.configUpgrader = configUpgrader;
        this.configFile = new File("config", name + ".json");
        if (configFile.exists()) {
            try {
                config = (T) gson.fromJson(Files.readString(configFile.toPath()), instanceProvider.get().getClass());
            } catch (JsonSyntaxException | IOException exception) {
                LOGGER.warn("Error while loading config: {}", exception.getMessage());
                LOGGER.warn("A new configuration will be created!");
            }
        }

        if (config == null) {
            reset();
        } else {
            if (configUpgrader != null && configUpgrader.upgradeConfig(config)) {
                writeConfig(); // Config got modified
            }
        }
    }

    public T getConfig() {
        return config;
    }

    public void reset() {
        config = instanceProvider.get();
        writeConfig();
    }

    public void writeConfig() {
        if (configFile.exists() && !configFile.delete()) {
            LOGGER.warn("Config {} could not be deleted before writing to it!", configFile.getName());
        }

        try {
            Files.writeString(configFile.toPath(), gson.toJson(config));
        } catch (IOException ioException) {
            LOGGER.warn("Error when writing config file {}!", configFile.getName(), ioException);
        }
    }

}
