package dev.tr7zw.transition.mc.entitywrapper;

import net.minecraft.world.entity.LivingEntity;
//#if MC >= 12102
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
//#endif

public class LivingEntityWrapper extends EntityWrapper {
    //#if MC >= 12102
    private final LivingEntityRenderState renderState;

    @Override
    public LivingEntityRenderState getRenderState() {
        return renderState;
    }

    public LivingEntityWrapper(LivingEntityRenderState renderState) {
        super(renderState);
        this.renderState = renderState;
    }
    //#else
    //$$private final LivingEntity entity;
    //$$
    //$$
    //$$public LivingEntityWrapper(LivingEntity entity) {
    //$$        super(entity);
    //$$        this.entity = entity;
    //$$}
    //#endif

    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) super.getEntity();
    }

}
