package dev.tr7zw.transition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dev.tr7zw.transition.manager.ConfigScreenManager;
//#if FABRIC
import net.fabricmc.api.ClientModInitializer;
//#endif

public class ClientTRansitionMod
        //#if FABRIC
        implements ClientModInitializer
//#endif
{
    public static final Logger logger = LogManager.getLogger();
    public static final ConfigScreenManager configScreenManager = new ConfigScreenManager();

    //#if FABRIC
    @Override
    //#endif
    public void onInitializeClient() {

    }

    public void onInitialize() {

    }

}
