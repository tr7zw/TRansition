package dev.tr7zw.transition.mc;

import lombok.experimental.UtilityClass;
import net.minecraft.world.entity.Entity;

@UtilityClass
public class EntityUtil {

    public static float getXRot(Entity ent) {
        //? if >= 1.17.0 {
        return ent.getXRot();
        //?} else {
        /*return ent.xRot;
        *///?}
    }

    public static float getYRot(Entity ent) {
        //? if >= 1.17.0 {
        return ent.getYRot();
        //?} else {
        /*return ent.yRot;
        *///?}
    }

    public static void setXRot(Entity ent, float xRot) {
        //? if >= 1.17.0 {
        ent.setXRot(xRot);
        //?} else {
        /*ent.xRot = xRot;
        *///?}
    }

    public static void setYRot(Entity ent, float yRot) {
        //? if >= 1.17.0 {
        ent.setYRot(yRot);
        //?} else {
        /*ent.yRot = yRot;
        *///?}
    }

}
