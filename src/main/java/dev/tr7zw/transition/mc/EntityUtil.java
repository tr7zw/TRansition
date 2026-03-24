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

    //? if >= 1.21.2 {

    public static Optional<ExtensionHolder> getExtensionHolder(
            net.minecraft.client.renderer.entity.state.EntityRenderState renderState) {
        if (renderState instanceof EntityRenderStateExtender extender
                && extender.getTransitionEntity() instanceof ExtensionHolder holder) {
            return Optional.of(holder);
        }
        return Optional.empty();
    }
    //? }

    public static float getXRot(Entity ent) {
        //$ get_entity_x_rot
        return ent.getXRot();
    }

    public static float getYRot(Entity ent) {
        //$ get_entity_y_rot
        return ent.getYRot();
    }

    public static void setXRot(Entity ent, float xRot) {
        //$ set_entity_x_rot
        ent.setXRot(xRot);
    }

    public static void setYRot(Entity ent, float yRot) {
        //$ set_entity_y_rot
        ent.setYRot(yRot);
    }

}
