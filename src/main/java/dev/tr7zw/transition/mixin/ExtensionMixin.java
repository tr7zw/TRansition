package dev.tr7zw.transition.mixin;

import java.util.HashMap;
import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;

import dev.tr7zw.transition.mc.extending.ExtensionHolder;
import net.minecraft.world.entity.Entity;

@Mixin(Entity.class)
public class ExtensionMixin implements ExtensionHolder {

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
