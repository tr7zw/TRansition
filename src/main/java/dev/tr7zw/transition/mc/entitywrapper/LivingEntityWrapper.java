package dev.tr7zw.transition.mc.entitywrapper;

import net.minecraft.world.entity.LivingEntity;
//? if >= 1.21.2 {
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
//?}

public class LivingEntityWrapper extends EntityWrapper {
    //? if >= 1.21.2 {
    private final LivingEntityRenderState renderState;

    @Override
    public LivingEntityRenderState getRenderState() {
        return renderState;
    }

    public LivingEntityWrapper(LivingEntityRenderState renderState) {
        super(renderState);
        this.renderState = renderState;
    }
    //?} else {
    /*private final LivingEntity entity;
    
    
    public LivingEntityWrapper(LivingEntity entity) {
            super(entity);
            this.entity = entity;
    }
    *///?}

    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) super.getEntity();
    }

}
