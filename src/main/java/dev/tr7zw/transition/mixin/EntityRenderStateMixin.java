package dev.tr7zw.transition.mixin;

import java.util.HashMap;
import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;

import dev.tr7zw.transition.mc.entitywrapper.EntityRenderStateExtender;
import dev.tr7zw.transition.mc.extending.ExtensionHolder;
import lombok.Getter;
import lombok.Setter;
//? if >= 1.21.2 {

import net.minecraft.world.entity.Entity;

@Mixin(targets = "net.minecraft.client.renderer.entity.state.EntityRenderState")
public class EntityRenderStateMixin implements EntityRenderStateExtender, ExtensionHolder {

    @Getter
    @Setter
    private Entity transitionEntity;

    private Map<Object, Object> extensionMap = new HashMap<>();

    @Override
    public void setExtension(Object key, Object value) {
        extensionMap.put(key, value);
    }

    @Override
    public <T> T getExtension(Object key, Class<T> type) {
        return type.cast(extensionMap.get(key));
    }

}
//? } else {
/*
 @Mixin(targets = "net.minecraft.client.Minecraft") // dummy for older versions
 public class EntityRenderStateMixin {}
*///? }
