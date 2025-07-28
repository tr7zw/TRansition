package dev.tr7zw.transition.mc;

import com.mojang.authlib.GameProfile;

import lombok.experimental.UtilityClass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;

//? if >= 1.20.2 {
import net.minecraft.client.resources.PlayerSkin;
//?} else {
/*import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import java.util.Map;
*///?}

@UtilityClass
public class PlayerUtil {

    public static ResourceLocation getPlayerSkin(AbstractClientPlayer player) {
        //? if >= 1.20.2 {
        return player.getSkin().texture();
        //?} else {
        /*return player.getSkinTextureLocation();
        *///?}
    }

    public static ResourceLocation getPlayerSkin(GameProfile gameprofile) {
        //? if >= 1.20.2 {
        PlayerSkin playerSkin = Minecraft.getInstance().getSkinManager().getInsecureSkin(gameprofile);
        if (playerSkin.textureUrl() == null) {
            return null;
        }
        return playerSkin.texture();
        //?} else {
        /*Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = Minecraft.getInstance().getSkinManager()
                .getInsecureSkinInformation(gameprofile);
        MinecraftProfileTexture texture = map.get(MinecraftProfileTexture.Type.SKIN);
         if (texture == null) {
             return null;
         }
         ResourceLocation resourceLocation = Minecraft.getInstance().getSkinManager().registerTexture(texture,
                 MinecraftProfileTexture.Type.SKIN);
         return resourceLocation;
        *///?}
    }

    public static ResourceLocation getPlayerCape(AbstractClientPlayer player) {
        try {
            //? if >= 1.20.2 {
            return player.getSkin().capeTexture();
            //?} else {
            /*return player.getCloakTextureLocation();
            *///?}
        } catch (NullPointerException ignored) {
            // Broken Oculus + cape modifying mods cause NPE by rendering before the player is setup and are not nullchecking
            // https://github.com/tr7zw/WaveyCapes/issues/86
            return null;
        }
    }

}
