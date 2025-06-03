package dev.tr7zw.transition.mc;

//#if MC >= 12106
import com.mojang.blaze3d.platform.Lighting.Entry;
//#else
//$$ import com.mojang.blaze3d.platform.Lighting;
//#endif

import net.minecraft.client.Minecraft;

public class LightingUtil {

    public static void prepareLightingEntity() {
        //#if MC >= 12106
        Minecraft.getInstance().gameRenderer.getLighting().setupFor(Entry.ENTITY_IN_UI);
        //#elseif MC >= 11700
        //$$ Lighting.setupForEntityInInventory();
        //#else
        //$$ Lighting.setupForFlatItems();
        //#endif
    }

}
