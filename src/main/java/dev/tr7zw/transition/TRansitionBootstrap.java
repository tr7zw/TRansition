//#if FORGE
//$$package dev.tr7zw.transition;
//$$
//$$import net.minecraftforge.api.distmarker.Dist;
//$$import net.minecraftforge.fml.DistExecutor;
//$$import net.minecraftforge.fml.common.Mod;
//$$import dev.tr7zw.transition.ClientTRansitionMod;
//$$
//$$@Mod("transition")
//$$public class TRansitionBootstrap {
//$$
//$$    public TRansitionBootstrap() {
//$$            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> { 
//$$         new ClientTRansitionMod().onInitializeClient();
//$$        });
//$$        new ClientTRansitionMod().onInitialize();
//$$    }
//$$    
//$$}
//#elseif NEOFORGE
//$$package dev.tr7zw.transition;
//$$
//$$import net.neoforged.api.distmarker.Dist;
//$$import net.neoforged.fml.loading.FMLEnvironment;
//$$import net.neoforged.fml.common.Mod;
//$$import dev.tr7zw.transition.ClientTRansitionMod;
//$$
//$$@Mod("transition")
//$$public class TRansitionBootstrap {
//$$
//$$    public TRansitionBootstrap() {
//$$        if(FMLEnvironment.dist == Dist.CLIENT) {
//$$         new ClientTRansitionMod().onInitializeClient();
//$$        }
//$$        new ClientTRansitionMod().onInitialize();
//$$    }
//$$    
//$$}
//#endif
