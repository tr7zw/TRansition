package dev.tr7zw.transition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    //? if fabric {

    @Override
    //? }
    public void onInitializeClient() {

    }

    public void onInitialize() {

    }

}
