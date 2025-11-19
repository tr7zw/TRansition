package dev.tr7zw.transition.mixin;

import net.minecraft.world.entity.*;
import net.minecraft.world.phys.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin(Entity.class)
public interface EntityAccessor {

    @Accessor("position")
    Vec3 transition$getRawPosition();

    @Accessor("position")
    void transition$setRawPosition(Vec3 position);

}
