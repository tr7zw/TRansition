package dev.tr7zw.transition;

import dev.tr7zw.transition.config.*;
import dev.tr7zw.transition.mc.*;
import dev.tr7zw.transition.sentry.*;
import net.minecraft.client.*;
import org.apache.logging.log4j.*;

import dev.tr7zw.transition.manager.ConfigScreenManager;
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

    }

    public void onInitialize() {

    }

}
