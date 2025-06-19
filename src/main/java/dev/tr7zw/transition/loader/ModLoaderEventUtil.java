package dev.tr7zw.transition.loader;

import lombok.experimental.UtilityClass;

//#if FABRIC
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
//#elseif FORGE
//#if MC >= 11900
//$$ import net.minecraftforge.event.TickEvent.LevelTickEvent;
//#else
//$$ import net.minecraftforge.event.TickEvent.WorldTickEvent;
//#endif
//$$ import java.util.function.Consumer;
//$$ import net.minecraftforge.event.TickEvent.ClientTickEvent;
//$$ import net.minecraftforge.common.MinecraftForge;
//$$ import net.minecraftforge.fml.ModLoadingContext;
//$$ import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
//$$ import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
//#elseif NEOFORGE
//$$ import java.util.function.Consumer;
//$$ import net.neoforged.neoforge.common.NeoForge;
//$$ import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
//$$ import net.neoforged.fml.ModLoadingContext;
//#endif

@UtilityClass
public class ModLoaderEventUtil {

    public static void registerClientTickStartListener(Runnable runnable) {
        //#if FABRIC
        ClientTickEvents.START_CLIENT_TICK.register(e -> {
            runnable.run();
        });
        //#elseif FORGE
        //#if MC >= 12106
        //$$ ClientTickEvent.Pre.BUS.addListener(new Consumer<ClientTickEvent.Pre>() {
        //$$    @Override
        //$$    public void accept(ClientTickEvent.Pre t) {
        //$$            runnable.run();
        //$$   }
        //$$});
        //#else
        //$$ MinecraftForge.EVENT_BUS.addListener(new Consumer<ClientTickEvent>() {
        //$$ 
        //$$    @Override
        //$$    public void accept(ClientTickEvent t) {
        //$$    if (t.phase != ClientTickEvent.Phase.START) return;
        //$$            runnable.run();
        //$$    }
        //$$    
        //$$ });
        //#endif
        //#elseif NEOFORGE
        //#if MC >= 12005
        //$$   NeoForge.EVENT_BUS.addListener(new Consumer<net.neoforged.neoforge.client.event.ClientTickEvent.Pre>() {
        //$$  
        //$$        @Override
        //$$       public void accept(net.neoforged.neoforge.client.event.ClientTickEvent.Pre t) {
        //$$               runnable.run();
        //$$       }
        //$$  
        //$$  });
        //#else
        //$$  NeoForge.EVENT_BUS.addListener(new Consumer<net.neoforged.neoforge.event.TickEvent.ClientTickEvent>() {
        //$$     
        //$$            @Override
        //$$            public void accept(net.neoforged.neoforge.event.TickEvent.ClientTickEvent t) {
        //$$                    runnable.run();
        //$$            }
        //$$            
        //$$    });
        //#endif
        //#endif
    }

    public static void registerClientTickEndListener(Runnable runnable) {
        //#if FABRIC
        ClientTickEvents.END_CLIENT_TICK.register(e -> {
            runnable.run();
        });
        //#elseif FORGE
        //#if MC >= 12106
        //$$ ClientTickEvent.Post.BUS.addListener(new Consumer<ClientTickEvent.Post>() {
        //$$    @Override
        //$$    public void accept(ClientTickEvent.Post t) {
        //$$            runnable.run();
        //$$   }
        //$$});
        //#else
        //$$ MinecraftForge.EVENT_BUS.addListener(new Consumer<ClientTickEvent>() {
        //$$ 
        //$$    @Override
        //$$    public void accept(ClientTickEvent t) {
        //$$    if (t.phase != ClientTickEvent.Phase.END) return;
        //$$            runnable.run();
        //$$    }
        //$$    
        //$$ });
        //#endif
        //#elseif NEOFORGE
        //#if MC >= 12005
        //$$   NeoForge.EVENT_BUS.addListener(new Consumer<net.neoforged.neoforge.client.event.ClientTickEvent.Post>() {
        //$$  
        //$$        @Override
        //$$       public void accept(net.neoforged.neoforge.client.event.ClientTickEvent.Post t) {
        //$$               runnable.run();
        //$$       }
        //$$  
        //$$  });
        //#else
        //$$  NeoForge.EVENT_BUS.addListener(new Consumer<net.neoforged.neoforge.event.TickEvent.ClientTickEvent>() {
        //$$     
        //$$            @Override
        //$$            public void accept(net.neoforged.neoforge.event.TickEvent.ClientTickEvent t) {
        //$$                    runnable.run();
        //$$            }
        //$$            
        //$$    });
        //#endif
        //#endif
    }

    public static void registerWorldTickStartListener(Runnable runnable) {
        //#if FABRIC
        ClientTickEvents.START_WORLD_TICK.register(e -> {
            runnable.run();
        });
        //#elseif FORGE
        //#if MC >= 12106
        //$$ LevelTickEvent.Pre.BUS.addListener(new Consumer<LevelTickEvent.Pre>() {
        //$$    @Override
        //$$    public void accept(LevelTickEvent.Pre t) {
        //$$            runnable.run();
        //$$   }
        //$$});
        //#elseif MC >= 11900
        //$$ MinecraftForge.EVENT_BUS.addListener(new Consumer<LevelTickEvent>() {
        //$$ 
        //$$    @Override
        //$$    public void accept(LevelTickEvent t) {
        //$$    if (t.phase != LevelTickEvent.Phase.START) return;
        //$$            runnable.run();
        //$$    }
        //$$    
        //$$ });
        //#else
        //$$ MinecraftForge.EVENT_BUS.addListener(new Consumer<WorldTickEvent>() {
        //$$ 
        //$$    @Override
        //$$    public void accept(WorldTickEvent t) {
        //$$    if (t.phase != WorldTickEvent.Phase.START) return;
        //$$            runnable.run();
        //$$    }
        //$$    
        //$$ });
        //#endif
        //#elseif NEOFORGE
        //#if MC >= 12005
        //$$   NeoForge.EVENT_BUS.addListener(new Consumer<net.neoforged.neoforge.event.tick.LevelTickEvent.Pre>() {
        //$$  
        //$$        @Override
        //$$       public void accept(net.neoforged.neoforge.event.tick.LevelTickEvent.Pre t) {
        //$$               runnable.run();
        //$$       }
        //$$  
        //$$  });
        //#else
        //$$  NeoForge.EVENT_BUS.addListener(new Consumer<net.neoforged.neoforge.event.TickEvent.LevelTickEvent>() {
        //$$     
        //$$            @Override
        //$$            public void accept(net.neoforged.neoforge.event.TickEvent.LevelTickEvent t) {
        //$$                    runnable.run();
        //$$            }
        //$$            
        //$$    });
        //#endif
        //#endif
    }

    public static void registerWorldTickEndListener(Runnable runnable) {
        //#if FABRIC
        ClientTickEvents.END_WORLD_TICK.register(e -> {
            runnable.run();
        });
        //#elseif FORGE
        //#if MC >= 12106
        //$$ LevelTickEvent.Post.BUS.addListener(new Consumer<LevelTickEvent.Post>() {
        //$$    @Override
        //$$    public void accept(LevelTickEvent.Post t) {
        //$$            runnable.run();
        //$$   }
        //$$});
        //#elseif MC >= 11900
        //$$ MinecraftForge.EVENT_BUS.addListener(new Consumer<LevelTickEvent>() {
        //$$ 
        //$$    @Override
        //$$    public void accept(LevelTickEvent t) {
        //$$    if (t.phase != LevelTickEvent.Phase.END) return;
        //$$            runnable.run();
        //$$    }
        //$$    
        //$$ });
        //#else
        //$$ MinecraftForge.EVENT_BUS.addListener(new Consumer<WorldTickEvent>() {
        //$$ 
        //$$    @Override
        //$$    public void accept(WorldTickEvent t) {
        //$$    if (t.phase != WorldTickEvent.Phase.END) return;
        //$$            runnable.run();
        //$$    }
        //$$    
        //$$ });
        //#endif
        //#elseif NEOFORGE
        //#if MC >= 12005
        //$$   NeoForge.EVENT_BUS.addListener(new Consumer<net.neoforged.neoforge.event.tick.LevelTickEvent.Post>() {
        //$$  
        //$$        @Override
        //$$       public void accept(net.neoforged.neoforge.event.tick.LevelTickEvent.Post t) {
        //$$               runnable.run();
        //$$       }
        //$$  
        //$$  });
        //#else
        //$$  NeoForge.EVENT_BUS.addListener(new Consumer<net.neoforged.neoforge.event.TickEvent.LevelTickEvent>() {
        //$$     
        //$$            @Override
        //$$            public void accept(net.neoforged.neoforge.event.TickEvent.LevelTickEvent t) {
        //$$                    runnable.run();
        //$$            }
        //$$            
        //$$    });
        //#endif
        //#endif
    }

    public static void registerClientSetupListener(Runnable runnable) {
        //#if FORGE || NEOFORGE
        //#if NEOFORGE
        //$$ ModLoadingContext.get().getActiveContainer().getEventBus().addListener(new Consumer<FMLClientSetupEvent>() {
        //#else
        //#if MC < 12106
        //$$ FMLJavaModLoadingContext.get().getModEventBus().addListener(new Consumer<FMLClientSetupEvent>() {
        //#else
        //$$ FMLClientSetupEvent.getBus(ModLoaderUtil.getModLoadingContext().getModBusGroup()).addListener(new Consumer<FMLClientSetupEvent>() {
        //#endif
        //#endif
        //$$ 
        //$$    @Override
        //$$    public void accept(FMLClientSetupEvent t) {
        //$$            runnable.run();
        //$$    }
        //$$    
        //$$ });
        //#endif
    }

}
