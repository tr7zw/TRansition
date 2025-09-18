package dev.tr7zw.transition.mc;

import java.util.Optional;

import com.mojang.authlib.GameProfile;

import lombok.experimental.UtilityClass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;

//#if MC >= 12002 && MC < 12109
//$$import net.minecraft.client.resources.PlayerSkin;
//#else
//$$ import com.mojang.authlib.minecraft.MinecraftProfileTexture;
//$$ import java.util.Map;
//#endif

@UtilityClass
public class PlayerUtil {

    public static ResourceLocation getPlayerSkin(AbstractClientPlayer player) {
        //#if MC >= 12109
        return player.getSkin().body().texturePath();
        //#elseif MC >= 12002
        //$$return player.getSkin().texture();
        //#else
        //$$ return player.getSkinTextureLocation();
        //#endif
    }

    public static ResourceLocation getPlayerSkin(GameProfile gameprofile) {
        //#if MC >= 12109
        return Minecraft.getInstance().getSkinManager().get(gameprofile).getNow(Optional.empty())
                .map(s -> s.body().texturePath()).orElse(null);
        //#elseif MC >= 12002
        //$$PlayerSkin playerSkin = Minecraft.getInstance().getSkinManager().getInsecureSkin(gameprofile);
        //$$if (playerSkin.textureUrl() == null) {
        //$$    return null;
        //$$}
        //$$return playerSkin.texture();
        //#else
        //$$ Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = Minecraft.getInstance().getSkinManager()
        //$$         .getInsecureSkinInformation(gameprofile);
        //$$ MinecraftProfileTexture texture = map.get(MinecraftProfileTexture.Type.SKIN);
        //$$  if (texture == null) {
        //$$      return null;
        //$$  }
        //$$  ResourceLocation resourceLocation = Minecraft.getInstance().getSkinManager().registerTexture(texture,
        //$$          MinecraftProfileTexture.Type.SKIN);
        //$$  return resourceLocation;
        //#endif
    }

    public static ResourceLocation getPlayerCape(AbstractClientPlayer player) {
        try {
            //#if MC >= 12109
            return player.getSkin().cape().texturePath();
            //#elseif MC >= 12002
            //$$return player.getSkin().capeTexture();
            //#else
            //$$ return player.getCloakTextureLocation();
            //#endif
        } catch (NullPointerException ignored) {
            // Broken Oculus + cape modifying mods cause NPE by rendering before the player is setup and are not nullchecking
            // https://github.com/tr7zw/WaveyCapes/issues/86
            return null;
        }
    }

}
