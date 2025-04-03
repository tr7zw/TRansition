package dev.tr7zw.transition.mc.entitywrapper;

import net.minecraft.world.entity.Entity;

public interface EntityRenderStateExtender {

    public Entity getTransitionEntity();

    public void setTransitionEntity(Entity entity);

}
