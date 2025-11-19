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

    public static /*? >= 1.21.11 {*/ Identifier /*?} else {*//* ResourceLocation *//*?}*/ getResourceLocation(
            String namespace, String path) {
        //? if >= 1.21.11 {

        return Identifier.fromNamespaceAndPath(namespace, path);
        //? } else if >= 1.21.0 {
        /*
        return ResourceLocation.fromNamespaceAndPath(namespace, path);
        *///? } else {
        /*
         return new ResourceLocation(namespace, path);
        *///? }
    }

    public static /*? >= 1.21.11 {*/ Identifier /*?} else {*//* ResourceLocation *//*?}*/ getResourceLocation(
            String key) {
        //? if >= 1.21.11 {

        return Identifier.parse(key);
        //? } else if >= 1.21.0 {
        /*
        return ResourceLocation.parse(key);
        *///? } else {
        /*
         return new ResourceLocation(key);
        *///? }
    }

    public static KeyMapping createKeyMapping(String keyName, int defaultKey, String category) {
        //? if >= 1.21.9 {

        return new KeyMapping(keyName, defaultKey, categoryCache.computeIfAbsent(category,
                c -> new net.minecraft.client.KeyMapping.Category(getResourceLocation(c))));
        //? } else {
        /*
         return new KeyMapping(keyName, defaultKey, category);
        *///? }
    }

    public static LocalPlayer getPlayer() {
        return Minecraft.getInstance().player;
    }

    public static Entity getCameraEntity() {
        //? if >= 1.21.9 {

        return Minecraft.getInstance().getCameraEntity();
        //? } else {
        /*
         return Minecraft.getInstance().cameraEntity;
        *///? }
    }

    public static Level getWorld() {
        return Minecraft.getInstance().level;
    }

}
