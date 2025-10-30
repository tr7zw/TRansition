package dev.tr7zw.transition.loader;

import lombok.experimental.UtilityClass;

//? if fabric {

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
//? } else if forge {
/*
 //? if >= 1.19.0 {

  import net.minecraftforge.event.TickEvent.LevelTickEvent;
 //? } else {
/^
  import net.minecraftforge.event.TickEvent.WorldTickEvent;
 ^///? }
 import java.util.function.Consumer;
 import net.minecraftforge.event.TickEvent.ClientTickEvent;
 import net.minecraftforge.common.MinecraftForge;
 import net.minecraftforge.fml.ModLoadingContext;
 import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
 import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
*///? } else if neoforge {
/*
 import java.util.function.Consumer;
 import net.neoforged.neoforge.common.NeoForge;
 import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
 import net.neoforged.fml.ModLoadingContext;
*///? }

@UtilityClass
public class ModLoaderEventUtil {

    public static void registerClientTickStartListener(Runnable runnable) {
        //? if fabric {

        ClientTickEvents.START_CLIENT_TICK.register(e -> {
            runnable.run();
        });
        //? } else if forge {
/*
         //? if >= 1.21.6 {

          ClientTickEvent.Pre.BUS.addListener(new Consumer<ClientTickEvent.Pre>() {
             @Override
             public void accept(ClientTickEvent.Pre t) {
                     runnable.run();
            }
          });
         //? } else {
/^
          MinecraftForge.EVENT_BUS.addListener(new Consumer<ClientTickEvent>() {
         
             @Override
             public void accept(ClientTickEvent t) {
             if (t.phase != ClientTickEvent.Phase.START) return;
                     runnable.run();
             }
             
          });
         ^///? }
        *///? } else if neoforge {
/*
         //? if >= 1.20.5 {

            NeoForge.EVENT_BUS.addListener(new Consumer<net.neoforged.neoforge.client.event.ClientTickEvent.Pre>() {
           
                 @Override
                public void accept(net.neoforged.neoforge.client.event.ClientTickEvent.Pre t) {
                        runnable.run();
                }
           
           });
         //? } else {
/^
           NeoForge.EVENT_BUS.addListener(new Consumer<net.neoforged.neoforge.event.TickEvent.ClientTickEvent>() {
              
                     @Override
                     public void accept(net.neoforged.neoforge.event.TickEvent.ClientTickEvent t) {
                             runnable.run();
                     }
                     
             });
         ^///? }
        *///? }
    }

    public static void registerClientTickEndListener(Runnable runnable) {
        //? if fabric {

        ClientTickEvents.END_CLIENT_TICK.register(e -> {
            runnable.run();
        });
        //? } else if forge {
/*
         //? if >= 1.21.6 {

          ClientTickEvent.Post.BUS.addListener(new Consumer<ClientTickEvent.Post>() {
             @Override
             public void accept(ClientTickEvent.Post t) {
                     runnable.run();
            }
          });
         //? } else {
/^
          MinecraftForge.EVENT_BUS.addListener(new Consumer<ClientTickEvent>() {
         
             @Override
             public void accept(ClientTickEvent t) {
             if (t.phase != ClientTickEvent.Phase.END) return;
                     runnable.run();
             }
             
          });
         ^///? }
        *///? } else if neoforge {
/*
         //? if >= 1.20.5 {

            NeoForge.EVENT_BUS.addListener(new Consumer<net.neoforged.neoforge.client.event.ClientTickEvent.Post>() {
           
                 @Override
                public void accept(net.neoforged.neoforge.client.event.ClientTickEvent.Post t) {
                        runnable.run();
                }
           
           });
         //? } else {
/^
           NeoForge.EVENT_BUS.addListener(new Consumer<net.neoforged.neoforge.event.TickEvent.ClientTickEvent>() {
              
                     @Override
                     public void accept(net.neoforged.neoforge.event.TickEvent.ClientTickEvent t) {
                             runnable.run();
                     }
                     
             });
         ^///? }
        *///? }
    }

    public static void registerWorldTickStartListener(Runnable runnable) {
        //? if fabric {

        ClientTickEvents.START_WORLD_TICK.register(e -> {
            runnable.run();
        });
        //? } else if forge {
/*
         //? if >= 1.21.6 {

          LevelTickEvent.Pre.BUS.addListener(new Consumer<LevelTickEvent.Pre>() {
             @Override
             public void accept(LevelTickEvent.Pre t) {
                     runnable.run();
            }
          });
         //? } else if >= 1.19.0 {
/^
          MinecraftForge.EVENT_BUS.addListener(new Consumer<LevelTickEvent>() {
         
             @Override
             public void accept(LevelTickEvent t) {
             if (t.phase != LevelTickEvent.Phase.START) return;
                     runnable.run();
             }
             
          });
         ^///? } else {
/^
          MinecraftForge.EVENT_BUS.addListener(new Consumer<WorldTickEvent>() {
         
             @Override
             public void accept(WorldTickEvent t) {
             if (t.phase != WorldTickEvent.Phase.START) return;
                     runnable.run();
             }
             
          });
         ^///? }
        *///? } else if neoforge {
/*
         //? if >= 1.20.5 {

            NeoForge.EVENT_BUS.addListener(new Consumer<net.neoforged.neoforge.event.tick.LevelTickEvent.Pre>() {
           
                 @Override
                public void accept(net.neoforged.neoforge.event.tick.LevelTickEvent.Pre t) {
                        runnable.run();
                }
           
           });
         //? } else {
/^
           NeoForge.EVENT_BUS.addListener(new Consumer<net.neoforged.neoforge.event.TickEvent.LevelTickEvent>() {
              
                     @Override
                     public void accept(net.neoforged.neoforge.event.TickEvent.LevelTickEvent t) {
                             runnable.run();
                     }
                     
             });
         ^///? }
        *///? }
    }

    public static void registerWorldTickEndListener(Runnable runnable) {
        //? if fabric {

        ClientTickEvents.END_WORLD_TICK.register(e -> {
            runnable.run();
        });
        //? } else if forge {
/*
         //? if >= 1.21.6 {

          LevelTickEvent.Post.BUS.addListener(new Consumer<LevelTickEvent.Post>() {
             @Override
             public void accept(LevelTickEvent.Post t) {
                     runnable.run();
            }
          });
         //? } else if >= 1.19.0 {
/^
          MinecraftForge.EVENT_BUS.addListener(new Consumer<LevelTickEvent>() {
         
             @Override
             public void accept(LevelTickEvent t) {
             if (t.phase != LevelTickEvent.Phase.END) return;
                     runnable.run();
             }
             
          });
         ^///? } else {
/^
          MinecraftForge.EVENT_BUS.addListener(new Consumer<WorldTickEvent>() {
         
             @Override
             public void accept(WorldTickEvent t) {
             if (t.phase != WorldTickEvent.Phase.END) return;
                     runnable.run();
             }
             
          });
         ^///? }
        *///? } else if neoforge {
/*
         //? if >= 1.20.5 {

            NeoForge.EVENT_BUS.addListener(new Consumer<net.neoforged.neoforge.event.tick.LevelTickEvent.Post>() {
           
                 @Override
                public void accept(net.neoforged.neoforge.event.tick.LevelTickEvent.Post t) {
                        runnable.run();
                }
           
           });
         //? } else {
/^
           NeoForge.EVENT_BUS.addListener(new Consumer<net.neoforged.neoforge.event.TickEvent.LevelTickEvent>() {
              
                     @Override
                     public void accept(net.neoforged.neoforge.event.TickEvent.LevelTickEvent t) {
                             runnable.run();
                     }
                     
             });
         ^///? }
        *///? }
    }

    public static void registerClientSetupListener(Runnable runnable) {
        //? if forge || neoforge {
/*
         //? if neoforge {
/^
          ModLoadingContext.get().getActiveContainer().getEventBus().addListener(new Consumer<FMLClientSetupEvent>() {
         ^///? } else {

         //? if < 1.21.6 {
/^
          FMLJavaModLoadingContext.get().getModEventBus().addListener(new Consumer<FMLClientSetupEvent>() {
         ^///? } else {

          FMLClientSetupEvent.getBus(ModLoaderUtil.getModLoadingContext().getModBusGroup()).addListener(new Consumer<FMLClientSetupEvent>() {
         //? }
         //? }
        
            @Override
            public void accept(FMLClientSetupEvent t) {
                    runnable.run();
            }
            
         });
        *///? }
    }

}
