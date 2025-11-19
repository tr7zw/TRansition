package dev.tr7zw.transition.logic.tempholders;

import dev.tr7zw.transition.logic.*;
import dev.tr7zw.transition.mixin.*;
import lombok.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.phys.*;

@Getter
public class PositionHolder implements TemporaryStateScope.TemporalHolder<Entity> {

    private Vec3 pos = null;
    private double xO, yO, zO, xOld, yOld, zOld;

    @Override
    public void prepareState(Entity ent) {
        pos = ((EntityAccessor) ent).transition$getRawPosition();
        xO = ent.xo;
        yO = ent.yo;
        zO = ent.zo;
        xOld = ent.xOld;
        yOld = ent.yOld;
        zOld = ent.zOld;
    }

    @Override
    public void revertSate(Entity ent) {
        ((EntityAccessor) ent).transition$setRawPosition(pos);
        ent.xo = xO;
        ent.yo = yO;
        ent.zo = zO;
        ent.xOld = xOld;
        ent.yOld = yOld;
        ent.zOld = zOld;
    }
}
