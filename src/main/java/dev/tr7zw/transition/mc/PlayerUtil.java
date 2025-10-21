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

    //#if MC >= 12109
    public static ResourceLocation getPlayerSkin(net.minecraft.world.entity.Avatar avatar) {
        return ((net.minecraft.client.entity.ClientAvatarEntity) avatar).getSkin().body().texturePath();
    }
    //#endif

    //#if MC >= 12005
    public static GameProfile getProfile(net.minecraft.world.item.component.ResolvableProfile profile) {
        if(profile == null) return null;
        //#if MC >= 12109
        return Minecraft.getInstance().playerSkinRenderCache().getOrDefault(profile).gameProfile();
        //#else
        //$$if(profile.isResolved()) {
        //$$    return profile.gameProfile();
        //$$} else {
        //$$    return null;
        //$$}
        //#endif
    }
    //#endif

    public static ResourceLocation getPlayerSkin(GameProfile gameprofile) {
        if(gameprofile == null) {
            return null;
        }
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
    
    //#if MC >= 12109
    public static ResourceLocation getPlayerCape(net.minecraft.world.entity.Avatar avatar) {
        var skin = ((net.minecraft.client.entity.ClientAvatarEntity) avatar).getSkin();
        if(skin.cape() == null || skin.cape() == null) {
            return null;
        }
        return skin.cape().texturePath();
    }
    //#endif

}
