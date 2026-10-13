package dev.tr7zw.transition.manager;

import java.util.*;
import java.util.function.Function;

import net.minecraft.client.gui.screens.Screen;

public class ConfigScreenManager {

    private final List<Function<Screen, Screen>> configScreens = Collections.synchronizedList(new ArrayList<>());

    public void registerConfigScreen(Function<Screen, Screen> configScreen) {
        configScreens.add(configScreen);
    }

    public List<Function<Screen, Screen>> getConfigScreens() {
        return configScreens;
    }

}
