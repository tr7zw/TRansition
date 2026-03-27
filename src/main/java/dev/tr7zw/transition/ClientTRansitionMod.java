package dev.tr7zw.transition;

import dev.tr7zw.transition.config.*;
import dev.tr7zw.transition.manager.*;
import dev.tr7zw.transition.mc.*;
import dev.tr7zw.transition.sentry.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.sounds.*;
import java.util.*;

import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import org.apache.logging.log4j.*;

//? if fabric {

import net.fabricmc.api.ClientModInitializer;

//? }

public class ClientTRansitionMod
        //? if fabric {

        implements ClientModInitializer
//? }
{
    public static final Logger logger = LogManager.getLogger();
    public static final ConfigScreenManager configScreenManager = new ConfigScreenManager();
    public static final ConfigManager<TransitionConfig> config = new ConfigManager<>("transition",
            TransitionConfig::new, null);

    //? if fabric {

    @Override
    //? }
    public void onInitializeClient() {

        SentryInstance.initialize(new SentryDataProviderImpl());
        CodeManager.getInstance().registerCode(new String(Base64.getDecoder().decode("dXVkZGxybHJiYQ==")), () -> {
            ClientUtil.sendChatMessage(ComponentProvider.literal(new String(Base64.getDecoder().decode(
                    "WW91IGZvdW5kIHRoZSBzZWNyZXQgY29kZSEgSXQgZGlkbid0IGRvIGFueXRoaW5nLCBidXQgeW91IGZvdW5kIGl0IQ=="))));
        });
        CodeManager.getInstance().registerCode(new String(Base64.getDecoder().decode("aSBhbSBldm9uIGduYXNoYmxhZGU=")),
                () -> {
                    Minecraft.getInstance().options.advancedItemTooltips = true;
                    ClientUtil.sendChatMessage(ComponentProvider
                            .literal(new String(Base64.getDecoder().decode("SSB3YXMgdGhlIGZhdm9yaXRlLg=="))));
                });
        CodeManager.getInstance().registerCode(new String(Base64.getDecoder().decode("bWFyY28h")), () -> {
            ClientUtil.sendToast(
                    ComponentProvider.literal(new String(Base64.getDecoder().decode("U29tZSB2b2ljZS4uLg=="))),
                    ComponentProvider.literal(new String(Base64.getDecoder().decode("UG9sbyE="))));
        });
        CodeManager.getInstance().registerCode(new String(Base64.getDecoder().decode("Z29kbW9kZQ==")), () -> {
            long start = System.currentTimeMillis();
            new Thread(() -> {
                float health = GeneralUtil.getPlayer().getHealth();
                Minecraft.getInstance().submit(() -> {
                    Minecraft.getInstance().setScreen(null);
                    ClientUtil.sendActionBarMessage(ComponentProvider
                            .literal(new String(Base64.getDecoder().decode("R29kbW9kZSBlbmFibGVkIQ=="))));
                });
                while (System.currentTimeMillis() - start < 5000) {
                    Minecraft.getInstance().submit(() -> {
                        if (GeneralUtil.getPlayer().isDeadOrDying()) {
                            return;
                        }
                        ClientUtil.playSound(SoundEvents.PLAYER_DEATH, 1, 1);
                        GeneralUtil.getPlayer().hurtTo(health * (1f - ((System.currentTimeMillis() - start) / 5000f)));
                    });
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (GeneralUtil.getPlayer().isDeadOrDying()) {
                    return;
                }
                var screen = new DeathScreen(
                        ComponentProvider.literal(
                                GeneralUtil.getPlayer().getName().getString() + new String(Base64.getDecoder().decode(
                                        "IHdlbnQgc28gbWFkIHdpdGggcG93ZXIgdGhhdCB0aGV5IGRpZWQhIEFwcmlsIEZvb2xzIQ=="))),
                        false
                //? if >= 1.21.11 {

                        , Minecraft.getInstance().player
                //? }
                );

                List<ItemStack> items = new ArrayList<>();
                Minecraft.getInstance().submit(() -> {
                    GeneralUtil.getPlayer().hurtTo(0.1f);
                    //? if >= 1.18 {

                    var inv = GeneralUtil.getPlayer().getInventory();
                    //? } else {
                    /*
                    var inv = GeneralUtil.getPlayer().inventory;
                    *///? }
                    for (int i = 0; i < inv.getContainerSize(); i++) {
                        items.add(inv.getItem(i));
                        inv.setItem(i, ItemStack.EMPTY);
                    }
                    if (Minecraft.getInstance().screen == null) {
                        Minecraft.getInstance().setScreen(screen);
                    }
                });
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (GeneralUtil.getPlayer().isDeadOrDying()) {
                    return;
                }
                Minecraft.getInstance().submit(() -> {
                    if (Minecraft.getInstance().screen == screen) {
                        Minecraft.getInstance().setScreen(null);
                    }
                    GeneralUtil.getPlayer().hurtTo(health);
                    //? if >= 1.18 {

                    var inv = GeneralUtil.getPlayer().getInventory();
                    //? } else {
                    /*
                    var inv = GeneralUtil.getPlayer().inventory;
                    *///? }
                    for (int i = 0; i < inv.getContainerSize(); i++) {
                        inv.setItem(i, items.get(i));
                    }
                });
            }).start();
        });
    }

}
