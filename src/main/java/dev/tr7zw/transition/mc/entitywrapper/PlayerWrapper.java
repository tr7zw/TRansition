package dev.tr7zw.transition.mc.entitywrapper;

import dev.tr7zw.transition.mc.PlayerUtil;
//? if >= 1.21.2 {

import net.minecraft.client.renderer.entity.state.*;
//? } else if < 1.21.2 {

/*import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.PlayerModelPart;
import dev.tr7zw.transition.mc.PlayerUtil;
*///? }
import net.minecraft.resources.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.entity.player.Player;

public class PlayerWrapper extends LivingEntityWrapper {

    //? if >= 1.21.2 {

    private final AvatarRenderState renderState;

    @Override
    public AvatarRenderState getRenderState() {
        return renderState;
    }

    public PlayerWrapper(AvatarRenderState renderState) {
        super(renderState);
        this.renderState = renderState;
    }
    //? }

    //? if >= 1.21.9 {

    public net.minecraft.world.entity.Avatar getAvatar() {
        if (super.getEntity() instanceof net.minecraft.world.entity.Avatar avatar) {
            return avatar;
        }
        return null;
    }

    //? } else if < 1.21.2 {

    /*private final AbstractClientPlayer player;
    
    
    public PlayerWrapper(AbstractClientPlayer player) {
           super(player);
           this.player = player;
    }
    *///? }

    @Override
    public Player getEntity() {
        return (Player) super.getEntity();
    }

    public Identifier getCapeTexture() {
        //$ get_cape_texture
        return PlayerUtil.getPlayerCape(getAvatar());
    }

    public boolean isPlayerInvisible() {
        //$ is_player_invisible
        return renderState.isInvisible;
    }

    public boolean isCapeVisible() {
        //$ is_cape_visible
        return renderState.showCape && !isPlayerInvisible();
    }

    public boolean hasElytraEquipped() {
        //$ has_elytra_equipped
        return renderState.chestEquipment.is(Items.ELYTRA);
    }

    public boolean hasChestplateEquipped() {
        //$ has_chestplate_equipped
        return !hasElytraEquipped() && !renderState.chestEquipment.isEmpty();
    }
}
