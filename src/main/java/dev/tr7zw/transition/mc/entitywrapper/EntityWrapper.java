package dev.tr7zw.transition.mc.entitywrapper;

import net.minecraft.world.entity.Entity;
import dev.tr7zw.transition.mc.extending.ExtensionHolder;
//#if MC >= 12102
import net.minecraft.client.renderer.entity.state.EntityRenderState;
//#endif

public class EntityWrapper {

    private final Entity entity;
    //#if MC >= 12102
    private final EntityRenderState renderState;

    public EntityRenderState getRenderState() {
        return renderState;
    }

    public EntityWrapper(EntityRenderState renderState) {
        this.renderState = renderState;
        if (renderState instanceof EntityRenderStateExtender extender) {
            entity = extender.getTransitionEntity();
        } else {
            entity = null;
        }
    }
    //#else
    //$$
    //$$public EntityWrapper(Entity entity) {
    //$$        this.entity = entity;
    //$$}
    //#endif

    public Entity getEntity() {
        return entity;
    }

    public boolean isAvailable() {
        return entity instanceof ExtensionHolder;
    }

    public ExtensionHolder getExtensionHolder() {
        return ((ExtensionHolder) entity);
    }

}
