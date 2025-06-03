package dev.tr7zw.transition.manager;

import java.util.List;
import java.util.function.Function;

import net.minecraft.client.gui.screens.Screen;

public class ConfigScreenManager {

    private final List<Function<Screen, Screen>> configScreens = new java.util.ArrayList<>();

    public void registerConfigScreen(Function<Screen, Screen> configScreen) {
        configScreens.add(configScreen);
    }

    public List<Function<Screen, Screen>> getConfigScreens() {
        return configScreens;
    }

}
