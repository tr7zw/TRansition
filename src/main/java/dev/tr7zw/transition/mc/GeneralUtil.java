package dev.tr7zw.transition.mc;

import java.util.Map;

import lombok.experimental.UtilityClass;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

@UtilityClass
public class GeneralUtil {

    //? if >= 1.21.9 {

    private static final Map<String, net.minecraft.client.KeyMapping.Category> categoryCache = new java.util.HashMap<>();
    //? }

    public static Identifier getResourceLocation(String namespace, String path) {
        //$ get_resource_location_namespace_path
        return Identifier.fromNamespaceAndPath(namespace, path);
    }

    public static Identifier getResourceLocation(String key) {
        //$ get_resource_location_string
        return Identifier.parse(key);
    }

    public static KeyMapping createKeyMapping(String keyName, int defaultKey, String category) {
        // spotless:off
        //$ create_keymapping
        return new KeyMapping(keyName, defaultKey, categoryCache.computeIfAbsent(category, c -> new net.minecraft.client.KeyMapping.Category(getResourceLocation(c))));
        // spotless:on
    }

    public static LocalPlayer getPlayer() {
        return Minecraft.getInstance().player;
    }

    public static Entity getCameraEntity() {
        //$ get_camera_entity
        return Minecraft.getInstance().getCameraEntity();
    }

    public static Level getWorld() {
        return Minecraft.getInstance().level;
    }

}
