package dev.tr7zw.transition.mc;

import java.util.Optional;

import dev.tr7zw.transition.mc.entitywrapper.EntityRenderStateExtender;
import dev.tr7zw.transition.mc.extending.ExtensionHolder;
import lombok.experimental.UtilityClass;
import net.minecraft.world.entity.Entity;

@UtilityClass
public class EntityUtil {

    public static Optional<ExtensionHolder> getExtensionHolder(Entity ent) {
        if (ent instanceof ExtensionHolder holder) {
            return Optional.of(holder);
        }
        return Optional.empty();
    }

    //#if MC >= 12102
    public static Optional<ExtensionHolder> getExtensionHolder(
            net.minecraft.client.renderer.entity.state.EntityRenderState renderState) {
        if (renderState instanceof EntityRenderStateExtender extender
                && extender.getTransitionEntity() instanceof ExtensionHolder holder) {
            return Optional.of(holder);
        }
        return Optional.empty();
    }
    //#endif

    public static float getXRot(Entity ent) {
        //#if MC >= 11700
        return ent.getXRot();
        //#else
        //$$ return ent.xRot;
        //#endif
    }

    public static float getYRot(Entity ent) {
        //#if MC >= 11700
        return ent.getYRot();
        //#else
        //$$ return ent.yRot;
        //#endif
    }

    public static void setXRot(Entity ent, float xRot) {
        //#if MC >= 11700
        ent.setXRot(xRot);
        //#else
        //$$ ent.xRot = xRot;
        //#endif
    }

    public static void setYRot(Entity ent, float yRot) {
        //#if MC >= 11700
        ent.setYRot(yRot);
        //#else
        //$$ ent.yRot = yRot;
        //#endif
    }

}
