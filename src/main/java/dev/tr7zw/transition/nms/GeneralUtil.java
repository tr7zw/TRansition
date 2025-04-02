package dev.tr7zw.transition.nms;

import lombok.experimental.UtilityClass;
import net.minecraft.resources.ResourceLocation;

@UtilityClass
public class GeneralUtil {

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

}
