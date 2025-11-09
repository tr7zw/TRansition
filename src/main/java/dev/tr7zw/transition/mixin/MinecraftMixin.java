package dev.tr7zw.transition.mixin;

import dev.tr7zw.transition.sentry.*;
import net.minecraft.*;
import net.minecraft.client.*;
import net.minecraft.client.resources.language.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Inject(method = "fillReport", at = @At("HEAD"), require = 0)
    public void fillReport(CrashReport theCrash, CallbackInfoReturnable<CrashReport> ci) {
        SentryInstance.maybeReport("CrashReport", Thread.currentThread().getName(), theCrash.getExceptionMessage(),
                theCrash.getException());
        SentryInstance.forceFlush();
    }

    //? if >= 1.18.0 {

    @Inject(method = "fillReport(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/resources/language/LanguageManager;Ljava/lang/String;Lnet/minecraft/client/Options;Lnet/minecraft/CrashReport;)V", at = @At("HEAD"), require = 0)
    //? } else {
    /*
    @Inject(method = "fillReport(Lnet/minecraft/client/resources/language/LanguageManager;Ljava/lang/String;Lnet/minecraft/client/Options;Lnet/minecraft/CrashReport;)V", at = @At("HEAD"), require = 0)
    *///? }
    private static void fillReport(
            //? if  >= 1.18.0 {

            Minecraft minecraft,
            //? }
            LanguageManager languageManager, String launchVersion, Options options, CrashReport report,
            CallbackInfo ci) {
        SentryInstance.maybeReport("CrashReport", Thread.currentThread().getName(), report.getExceptionMessage(),
                report.getException());
        SentryInstance.forceFlush();
    }

}
