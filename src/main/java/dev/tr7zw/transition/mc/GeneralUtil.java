package dev.tr7zw.transition.mc;

import lombok.experimental.UtilityClass;
import net.minecraft.resources.ResourceLocation;

@UtilityClass
public class GeneralUtil {

    public static ResourceLocation getResourceLocation(String namespace, String path) {
        //? if >= 1.21.0 {
        return ResourceLocation.fromNamespaceAndPath(namespace, path);
        //?} else {
        /*return new ResourceLocation(namespace, path);
        *///?}
    }

    public static ResourceLocation getResourceLocation(String key) {
        //? if >= 1.21.0 {
        return ResourceLocation.parse(key);
        //?} else {
        /*return new ResourceLocation(key);
        *///?}
    }

}
