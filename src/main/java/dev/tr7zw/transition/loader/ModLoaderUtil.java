package dev.tr7zw.transition.loader;

//#if FORGE || NEOFORGE
//$$ import java.util.function.Consumer;
//#endif
import java.util.function.Function;

import dev.tr7zw.transition.ClientTRansitionMod;
import lombok.experimental.UtilityClass;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.Screen;
//#if FABRIC
import net.minecraft.Util;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
//#elseif FORGE
//$$ import net.minecraftforge.fml.ModList;
//$$ import net.minecraft.client.Minecraft;
//$$ import org.apache.commons.lang3.ArrayUtils;
//$$ import net.minecraftforge.common.MinecraftForge;
//$$ import net.minecraftforge.event.TickEvent.ClientTickEvent;
//$$ import java.util.function.Consumer;
//$$ import net.minecraftforge.fml.ModLoadingContext;
//$$ import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
//$$ import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
//#if MC < 12106
//$$ import net.minecraftforge.eventbus.api.Event;
//#endif
//#if MC <= 11605
//$$ import net.minecraftforge.fml.ExtensionPoint;
//$$ import net.minecraftforge.fml.network.FMLNetworkConstants;
//$$ import org.apache.commons.lang3.tuple.Pair;
//#elseif MC <= 11701
//$$ import net.minecraftforge.fml.IExtensionPoint;
//$$ import net.minecraftforge.fmlclient.ConfigGuiHandler.ConfigGuiFactory;
//#elseif MC <= 11802
//$$ import net.minecraftforge.fml.IExtensionPoint;
//$$ import net.minecraftforge.client.ConfigGuiHandler.ConfigGuiFactory;
//#else
//$$ import net.minecraftforge.fml.IExtensionPoint;
//$$ import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory;
//#endif
//#elseif NEOFORGE
//$$ import net.neoforged.fml.ModList;
//$$ import net.minecraft.client.Minecraft;
//$$ import org.apache.commons.lang3.ArrayUtils;
//$$ import java.util.function.Consumer;
//$$ import net.neoforged.fml.ModLoadingContext;
//$$ import net.neoforged.fml.IExtensionPoint;
//$$ import net.neoforged.bus.api.Event;
//$$ import net.neoforged.neoforge.common.NeoForge;
//$$ import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
//#if MC >= 12005
//$$ import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
//#else
//$$ import net.neoforged.neoforge.client.ConfigScreenHandler.ConfigScreenFactory;
//$$ import net.neoforged.neoforge.event.TickEvent.ClientTickEvent;
//#endif
//#endif

@UtilityClass
public class ModLoaderUtil {

    public static void registerKeybind(KeyMapping keyBinding) {
        //#if FABRIC
        KeyBindingHelper.registerKeyBinding(keyBinding);
        //#elseif FORGE || NEOFORGE
        //$$ Minecraft.getInstance().options.keyMappings = ArrayUtils.add(Minecraft.getInstance().options.keyMappings, keyBinding);
        //#endif

    }

    @Deprecated
    public static void registerClientTickListener(Runnable runnable) {
        ModLoaderEventUtil.registerClientTickStartListener(runnable);
    }

    public static boolean isModLoaded(String name) {
        //#if FABRIC
        return FabricLoader.getInstance().isModLoaded(name);
        //#else
        //$$ return ModList.get().isLoaded(name);
        //#endif
    }

    public static void disableDisplayTest() {
        //#if FABRIC
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
        //#endif
        //#if FORGE || NEOFORGE
        //#if MC <= 11605
        //$$ ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST,
        //$$ () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (remote, isServer) -> true));
        //#else
        //#if MC >= 12005 && NEOFORGE
        // nothing
        //#else
        //$$        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class,
        //$$                () -> new IExtensionPoint.DisplayTest(
        //$$                       () -> ModLoadingContext.get().getActiveContainer().getModInfo().getVersion().toString(),
        //$$                        (remote, isServer) -> true));
        //#endif
        //#endif
        //#endif
    }

    public static void registerConfigScreen(Function<Screen, Screen> createScreen) {
        ClientTRansitionMod.configScreenManager.registerConfigScreen(createScreen);
        //#if FORGE || NEOFORGE
        //#if MC <= 11605
        //$$         ModLoadingContext.get().registerExtensionPoint(
        //$$ ExtensionPoint.CONFIGGUIFACTORY,
        //$$ () -> (mc, screen) -> createScreen.apply(screen));
        //#elseif MC <= 11802
        //$$ ModLoadingContext.get().registerExtensionPoint(ConfigGuiFactory.class, ()
        //$$ -> new ConfigGuiFactory((mc, screen) -> {
        //$$            return createScreen.apply(screen);
        //$$        }));
        //#elseif MC >= 12005 && NEOFORGE
        //$$ ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (mc, screen) -> {
        //$$     return createScreen.apply(screen);
        //$$ });
        //#else
        //$$ ModLoadingContext.get().registerExtensionPoint(ConfigScreenFactory.class, () -> new ConfigScreenFactory((mc, screen) -> {
        //$$            return createScreen.apply(screen);
        //$$        }));
        //#endif 
        //#endif
    }

    @Deprecated
    public static void registerClientSetupListener(Runnable runnable) {
        ModLoaderEventUtil.registerClientSetupListener(runnable);
    }

    //#if FORGE
    //$$private static final ThreadLocal<net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext> modLoadingContext = ThreadLocal.withInitial(() -> null);
    //$$
    //$$public static void setModLoadingContext(net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext context) {
    //$$    modLoadingContext.set(context);
    //$$}
    //$$public static FMLJavaModLoadingContext getModLoadingContext() { return modLoadingContext.get(); }
    //#if MC < 12106
    //$$     public static <T extends Event> void registerForgeEvent(Consumer<T> handler) {
    //$$     	MinecraftForge.EVENT_BUS.addListener(handler);
    //$$     }
    //#endif
    //#elseif NEOFORGE
    //$$    public static <T extends Event> void registerForgeEvent(Consumer<T> handler) {
    //$$    	NeoForge.EVENT_BUS.addListener(handler);
    //$$    }
    //#endif

}