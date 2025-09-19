package dev.tr7zw.transition.mc;

import java.util.Map;

import lombok.experimental.UtilityClass;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

@UtilityClass
public class GeneralUtil {

    //#if MC >= 12109
    private static final Map<String, net.minecraft.client.KeyMapping.Category> categoryCache = new java.util.HashMap<>();
    //#endif
    
    public static ResourceLocation getResourceLocation(String namespace, String path) {
        //#if MC >= 12100
        return ResourceLocation.fromNamespaceAndPath(namespace, path);
        //#else
        //$$ return new ResourceLocation(namespace, path);
        //#endif
    }

    public static ResourceLocation getResourceLocation(String key) {
        //#if MC >= 12100
        return ResourceLocation.parse(key);
        //#else
        //$$ return new ResourceLocation(key);
        //#endif
    }

    public static KeyMapping createKeyMapping(String keyName, int defaultKey, String category) {
        //#if MC >= 12109
        return new KeyMapping(keyName, defaultKey, categoryCache.computeIfAbsent(category, c -> new net.minecraft.client.KeyMapping.Category(getResourceLocation(c))));
        //#else
        //$$ return new KeyMapping(keyName, defaultKey, category);
        //#endif
    }

    public static LocalPlayer getPlayer() {
        return Minecraft.getInstance().player;
    }

    public static Entity getCameraEntity() {
        //#if MC >= 12109
        return Minecraft.getInstance().getCameraEntity();
        //#else
        //$$ return Minecraft.getInstance().cameraEntity;
        //#endif
    }

    public static Level getWorld() {
        return Minecraft.getInstance().level;
    }

}
