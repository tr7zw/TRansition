package dev.tr7zw.transition.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//#if MC >= 12102
import net.minecraft.world.entity.Entity;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import dev.tr7zw.transition.mc.entitywrapper.EntityRenderStateExtender;

@Mixin(targets = "net.minecraft.client.renderer.entity.EntityRenderer")
public class EntityRendererMixin {

    @Inject(method = "extractRenderState", at = @At("RETURN"))
    public void extractRenderState(Entity entity, EntityRenderState reusedState, float partialTick, CallbackInfo ci) {
        if (reusedState instanceof EntityRenderStateExtender extender) {
            extender.setTransitionEntity(entity);
        }
    }

}
//#else
//$$ @Mixin(targets = "net.minecraft.client.Minecraft") // dummy for older versions
//$$ public class EntityRendererMixin {}
//#endif
