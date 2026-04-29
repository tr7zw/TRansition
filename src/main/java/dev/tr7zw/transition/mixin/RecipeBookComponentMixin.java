package dev.tr7zw.transition.mixin;

import dev.tr7zw.transition.*;
import dev.tr7zw.transition.manager.*;
import net.minecraft.client.gui.screens.recipebook.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(RecipeBookComponent.class)
public class RecipeBookComponentMixin {

    @Inject(method = "pirateSpeechForThePeople", at = @At("HEAD"))
    private void processCode(final String searchTarget, CallbackInfo ci) {
        if (!ClientTRansitionMod.config.getConfig().disableRecipeBookCodes) {
            CodeManager.getInstance().executeCode(searchTarget);
        }
    }

}
