package dev.tr7zw.transition.mc.entitywrapper;

import net.minecraft.world.entity.Entity;

public interface EntityRenderStateExtender {

    @Deprecated
    Entity getTransitionEntity();

    @Deprecated
    void setTransitionEntity(Entity entity);

}
