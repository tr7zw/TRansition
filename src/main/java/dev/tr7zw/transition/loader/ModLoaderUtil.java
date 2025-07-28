package dev.tr7zw.transition.loader;

//? if forge || neoforge {
/*import java.util.function.Consumer;
*///?}
import java.util.function.Function;

import dev.tr7zw.transition.ClientTRansitionMod;
import lombok.experimental.UtilityClass;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.Screen;
//? if fabric {
import net.minecraft.Util;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
//?} else if forge {
/*import net.minecraftforge.fml.ModList;
import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.ArrayUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import java.util.function.Consumer;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
//? if < 1.21.6 {
 import net.minecraftforge.eventbus.api.Event;
//?}
//? if <= 1.16.5 {
 import net.minecraftforge.fml.ExtensionPoint;
 import net.minecraftforge.fml.network.FMLNetworkConstants;
 import org.apache.commons.lang3.tuple.Pair;
//?} else if <= 1.17.1 {
// import net.minecraftforge.fml.IExtensionPoint;
// import net.minecraftforge.fmlclient.ConfigGuiHandler.ConfigGuiFactory;
//?} else if <= 1.18.2 {
 /^import net.minecraftforge.fml.IExtensionPoint;
 import net.minecraftforge.client.ConfigGuiHandler.ConfigGuiFactory;
^///?} else {
 /^import net.minecraftforge.fml.IExtensionPoint;
 import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory;
^///?}
*///?} else if neoforge {
/*import net.neoforged.fml.ModList;
import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.ArrayUtils;
import java.util.function.Consumer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.IExtensionPoint;
import net.neoforged.bus.api.Event;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
//? if >= 1.20.5 {
// import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
//?} else {
 import net.neoforged.neoforge.client.ConfigScreenHandler.ConfigScreenFactory;
 import net.neoforged.neoforge.event.TickEvent.ClientTickEvent;
//?}
*///?}

@UtilityClass
public class ModLoaderUtil {

    public static void registerKeybind(KeyMapping keyBinding) {
        //? if fabric {
        KeyBindingHelper.registerKeyBinding(keyBinding);
        //?} else if forge || neoforge {
        /*Minecraft.getInstance().options.keyMappings = ArrayUtils.add(Minecraft.getInstance().options.keyMappings, keyBinding);
        *///?}

    }

    @Deprecated
    public static void registerClientTickListener(Runnable runnable) {
        ModLoaderEventUtil.registerClientTickStartListener(runnable);
    }

    public static boolean isModLoaded(String name) {
        //? if fabric {
        return FabricLoader.getInstance().isModLoaded(name);
        //?} else {
        /*return ModList.get().isLoaded(name);
        *///?}
    }

    public static void disableDisplayTest() {
        //? if fabric {
        try {
            Class.forName("dev.su5ed.sinytra.connector.mod.ConnectorMod").getCanonicalName();
            // Fabric mod running under Sinytra Connector, crash right here
            System.out.println("Detected Sinytra Connector used on a Fabric mod. Closing the game. "
                    + ModLoaderUtil.class.getPackage().toString());
            Util.getPlatform().openUri("https://tr7zw.github.io/sinytraconnector/");
            System.exit(-1);
        } catch (Exception ex) {
            // good
        }
        //?}
        //? if forge || neoforge {
        /*//? if <= 1.16.5 {
          ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST,
          () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (remote, isServer) -> true));
        //?} else {
         /^//? if >= 1.20.5 && neoforge {
        
         //?} else {
                  ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class,
                          () -> new IExtensionPoint.DisplayTest(
                                 () -> ModLoadingContext.get().getActiveContainer().getModInfo().getVersion().toString(),
                                  (remote, isServer) -> true));
         //?}
        ^///?}
        *///?}
    }

    public static void registerConfigScreen(Function<Screen, Screen> createScreen) {
        ClientTRansitionMod.configScreenManager.registerConfigScreen(createScreen);
        //? if forge || neoforge {
        /*//? if <= 1.16.5 {
                  ModLoadingContext.get().registerExtensionPoint(
          ExtensionPoint.CONFIGGUIFACTORY,
          () -> (mc, screen) -> createScreen.apply(screen));
        //?} else if <= 1.18.2 {
          /^ModLoadingContext.get().registerExtensionPoint(ConfigGuiFactory.class, ()
          -> new ConfigGuiFactory((mc, screen) -> {
                     return createScreen.apply(screen);
                 }));
        ^///?} else if >= 1.20.5 && neoforge {
         // ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (mc, screen) -> {
         //     return createScreen.apply(screen);
         // });
        //?} else {
          /^ModLoadingContext.get().registerExtensionPoint(ConfigScreenFactory.class, () -> new ConfigScreenFactory((mc, screen) -> {
                     return createScreen.apply(screen);
                 }));
        ^///?}
        *///?}
    }

    @Deprecated
    public static void registerClientSetupListener(Runnable runnable) {
        ModLoaderEventUtil.registerClientSetupListener(runnable);
    }

    //? if forge {
    /*private static final ThreadLocal<net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext> modLoadingContext = ThreadLocal.withInitial(() -> null);
    
    public static void setModLoadingContext(net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext context) {
        modLoadingContext.set(context);
    }
    public static FMLJavaModLoadingContext getModLoadingContext() { return modLoadingContext.get(); }
    //? if < 1.21.6 {
          public static <T extends Event> void registerForgeEvent(Consumer<T> handler) {
          	MinecraftForge.EVENT_BUS.addListener(handler);
          }
    //?}
    *///?} else if neoforge {
    /*public static <T extends Event> void registerForgeEvent(Consumer<T> handler) {
    	NeoForge.EVENT_BUS.addListener(handler);
    }
    *///?}

}
