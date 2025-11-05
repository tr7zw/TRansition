package dev.tr7zw.transition.sentry;

import dev.tr7zw.transition.*;
import dev.tr7zw.transition.mc.*;
import lombok.*;
import net.minecraft.*;
import net.minecraft.client.*;

import java.util.*;

public class SentryDataProviderImpl implements SentryDataProvider {

    @Getter
    //? if >= 1.21.6 {

    private final String minecraftVersion = DetectedVersion.tryDetectVersion().name();
    //? } else {
    /*
    private final String minecraftVersion = DetectedVersion.tryDetectVersion().getName();
    *///? }

    @Override
    public boolean userConsentedToCrashReports() {
        return ClientTRansitionMod.config.getConfig().userConsentedToSendCrashReports;
    }

    @Override
    public String getModloader() {
        //? if fabric {

        return "Fabric";
        //? } else if forge {
        /*
        return "Forge";
        *///? } else if neoforge {
        /*
        return "NeoForge";
         *///? }
    }

    @Override
    public Collection<String> getLoadedMods() {
        //? if fabric {

        return net.fabricmc.loader.api.FabricLoader.getInstance().getAllMods().stream()
                .map(m -> m.getMetadata().getId() + ":" + m.getMetadata().getVersion().getFriendlyString()).toList();
        //? } else if forge {
        /*
        //? if >= 1.21.4 {
        
        return net.minecraftforge.fml.ModList.get().getLoadedMods().stream().map(m -> m.getModId() + ":" + m.getModInfo().getVersion()).toList();
        //? } else {
        /^
        return net.minecraftforge.fml.ModList.get().getMods().stream().map(m -> m.getModId() + ":" + m.getVersion())
                .toList();
        ^///? }
        *///? } else if neoforge {
        /*
        return net.neoforged.fml.ModList.get().getMods().stream().map(m -> m.getModId() + ":" + m.getVersion()).toList();
         *///? }
    }

    @Override
    public String getModVersion() {
        //? if fabric {

        return net.fabricmc.loader.api.FabricLoader.getInstance().getModContainer("transition").get().getMetadata()
                .getVersion().getFriendlyString();
        //? } else if forge {
        /*
        return net.minecraftforge.fml.ModList.get().getModContainerById("transition").get().getModInfo().getVersion()
                .toString();
        *///? } else if neoforge {
        /*
        return net.neoforged.fml.ModList.get().getModContainerById("transition").get().getModInfo().getVersion().toString();
         *///? }
    }

    @Override
    public String getLevelType() {
        var world = GeneralUtil.getWorld();
        return world == null ? "None" : world.getServer().isSingleplayer() ? "Singleplayer" : "Multiplayer";
    }

    @Override
    public boolean hasPlayer() {
        return GeneralUtil.getPlayer() != null;
    }

    @Override
    public String getScreen() {
        var screen = Minecraft.getInstance().screen;
        return screen == null ? "Null" : screen.toString();
    }
}
