package dev.tr7zw.transition;

import dev.tr7zw.transition.config.*;
import dev.tr7zw.transition.manager.*;
import dev.tr7zw.transition.mc.*;
import dev.tr7zw.transition.sentry.*;
import net.minecraft.client.*;
import org.apache.logging.log4j.*;
import java.util.*;

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
    }

    public void onInitialize() {

    }

}
