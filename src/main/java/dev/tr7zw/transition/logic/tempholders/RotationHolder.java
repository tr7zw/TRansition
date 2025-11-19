package dev.tr7zw.transition.logic.tempholders;

import dev.tr7zw.transition.logic.*;
import dev.tr7zw.transition.mc.*;
import net.minecraft.world.entity.*;

public class RotationHolder implements TemporaryStateScope.TemporalHolder<Entity> {

    private float yRotO, xRotO, xRot, yRot, yBodyRot, yBodyRotO, yHeadRot, yHeadRotO;

    @Override
    public void prepareState(Entity ent) {
        yRotO = ent.yRotO;
        xRotO = ent.xRotO;
        xRot = EntityUtil.getXRot(ent);
        yRot = EntityUtil.getYRot(ent);
        if (ent instanceof LivingEntity livingEntity) {
            yBodyRot = livingEntity.yBodyRot;
            yBodyRotO = livingEntity.yBodyRotO;
            yHeadRot = livingEntity.yHeadRot;
            yHeadRotO = livingEntity.yHeadRotO;
        }
    }

    @Override
    public void revertSate(Entity ent) {
        ent.yRotO = yRotO;
        ent.xRotO = xRotO;
        EntityUtil.setXRot(ent, xRot);
        EntityUtil.setYRot(ent, yRot);
        if (ent instanceof LivingEntity livingEntity) {
            livingEntity.yBodyRot = yBodyRot;
            livingEntity.yBodyRotO = yBodyRotO;
            livingEntity.yHeadRot = yHeadRot;
            livingEntity.yHeadRotO = yHeadRotO;
        }
    }
}
