package dev.tr7zw.transition.mixin;

import org.spongepowered.asm.mixin.Mixin;

import dev.tr7zw.transition.mc.entitywrapper.EntityRenderStateExtender;
import lombok.Getter;
import lombok.Setter;
//? if >= 1.21.2 {
import net.minecraft.world.entity.Entity;

@Mixin(targets = "net.minecraft.client.renderer.entity.state.EntityRenderState")
public class EntityRenderStateMixin implements EntityRenderStateExtender {

    @Getter
    @Setter
    private Entity transitionEntity;

}
//?} else {
/*@Mixin(targets = "net.minecraft.client.Minecraft") // dummy for older versions
public class EntityRenderStateMixin {}
*///?}
