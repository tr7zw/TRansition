package dev.tr7zw.transition.mixin;

import java.util.HashMap;
import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
//? if >= 1.21.9 {

import dev.tr7zw.transition.mc.extending.ExtensionHolder;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;

@Mixin(BlockEntityRenderState.class)
public class BlockEntityRenderStateMixin implements ExtensionHolder {

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
 public class BlockEntityRenderStateMixin {}
*///? }
