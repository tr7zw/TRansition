package dev.tr7zw.transition.mc;

import java.util.Optional;

import com.mojang.authlib.GameProfile;

import lombok.experimental.UtilityClass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.*;

//? if >= 1.20.2 && < 1.21.9 {
/*
 import net.minecraft.client.resources.PlayerSkin;
*///? } else {

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import java.util.Map;
//? }

@UtilityClass
public class PlayerUtil {

    public static /*? >= 1.21.11 {*/ Identifier /*?} else {*//* ResourceLocation *//*?}*/ getPlayerSkin(
            AbstractClientPlayer player) {
        //? if >= 1.21.9 {

        return player.getSkin().body().texturePath();
        //? } else if >= 1.20.2 {
        /*
         return player.getSkin().texture();
        *///? } else {
        /*
         return player.getSkinTextureLocation();
        *///? }
    }

    //? if >= 1.21.9 {

    public static /*? >= 1.21.11 {*/ Identifier /*?} else {*//* ResourceLocation *//*?}*/ getPlayerSkin(
            net.minecraft.world.entity.Avatar avatar) {
        return ((net.minecraft.client.entity.ClientAvatarEntity) avatar).getSkin().body().texturePath();
    }
    //? }

    //? if >= 1.20.5 {

    public static GameProfile getProfile(net.minecraft.world.item.component.ResolvableProfile profile) {
        if (profile == null)
            return null;
        //? if >= 1.21.9 {

        return Minecraft.getInstance().playerSkinRenderCache().getOrDefault(profile).gameProfile();
        //? } else {
        /*
         if(profile.isResolved()) {
            return profile.gameProfile();
         } else {
            return null;
         }
        *///? }
    }
    //? }

    public static /*? >= 1.21.11 {*/ Identifier /*?} else {*//* ResourceLocation *//*?}*/ getPlayerSkin(
            GameProfile gameprofile) {
        if (gameprofile == null) {
            return null;
        }
        //? if >= 1.21.9 {

        return Minecraft.getInstance().getSkinManager().get(gameprofile).getNow(Optional.empty())
                .map(s -> s.body().texturePath()).orElse(null);
        //? } else if >= 1.20.2 {
        /*
         PlayerSkin playerSkin = Minecraft.getInstance().getSkinManager().getInsecureSkin(gameprofile);
         if (playerSkin.textureUrl() == null) {
            return null;
         }
         return playerSkin.texture();
        *///? } else {
        /*
         Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = Minecraft.getInstance().getSkinManager()
                 .getInsecureSkinInformation(gameprofile);
         MinecraftProfileTexture texture = map.get(MinecraftProfileTexture.Type.SKIN);
          if (texture == null) {
              return null;
          }
          ResourceLocation resourceLocation = Minecraft.getInstance().getSkinManager().registerTexture(texture,
                  MinecraftProfileTexture.Type.SKIN);
          return resourceLocation;
        *///? }
    }

    public static /*? >= 1.21.11 {*/ Identifier /*?} else {*//* ResourceLocation *//*?}*/ getPlayerCape(
            AbstractClientPlayer player) {
        try {
            //? if >= 1.21.9 {

            return player.getSkin().cape().texturePath();
            //? } else if >= 1.20.2 {
            /*
             return player.getSkin().capeTexture();
            *///? } else {
            /*
             return player.getCloakTextureLocation();
            *///? }
        } catch (NullPointerException ignored) {
            // Broken Oculus + cape modifying mods cause NPE by rendering before the player is setup and are not nullchecking
            // https://github.com/tr7zw/WaveyCapes/issues/86
            return null;
        }
    }

    //? if >= 1.21.9 {

    public static /*? >= 1.21.11 {*/ Identifier /*?} else {*//* ResourceLocation *//*?}*/ getPlayerCape(
            net.minecraft.world.entity.Avatar avatar) {
        var skin = ((net.minecraft.client.entity.ClientAvatarEntity) avatar).getSkin();
        if (skin.cape() == null || skin.cape() == null) {
            return null;
        }
        return skin.cape().texturePath();
    }
    //? }

}
