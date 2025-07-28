package dev.tr7zw.transition.mc.entitywrapper;

//? if >= 1.21.2 {
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
//?} else {
/*import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.PlayerModelPart;
import dev.tr7zw.transition.mc.PlayerUtil;
*///?}
import net.minecraft.world.item.Items;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;

public class PlayerWrapper extends LivingEntityWrapper {
    //? if >= 1.21.2 {
    private final PlayerRenderState renderState;

    @Override
    public PlayerRenderState getRenderState() {
        return renderState;
    }

    public PlayerWrapper(PlayerRenderState renderState) {
        super(renderState);
        this.renderState = renderState;
    }
    //?} else {
    /*private final AbstractClientPlayer player;
    
    
    public PlayerWrapper(AbstractClientPlayer player) {
            super(player);
            this.player = player;
    }
    *///?}

    @Override
    public Player getEntity() {
        return (Player) super.getEntity();
    }

    public ResourceLocation getCapeTexture() {
        //? if >= 1.21.2 {
        return renderState.skin.capeTexture();
        //?} else {
        /*return PlayerUtil.getPlayerCape(player);
        *///?}
    }

    public boolean isPlayerInvisible() {
        //? if >= 1.21.2 {
        return renderState.isInvisible;
        //?} else {
        /*return player.isInvisible();
        *///?}
    }

    public boolean isCapeVisible() {
        //? if >= 1.21.2 {
        return renderState.showCape && !isPlayerInvisible();
        //?} else {
        /*return player.isModelPartShown(PlayerModelPart.CAPE);
        *///?}
    }

    public boolean hasElytraEquipped() {
        //? if >= 1.21.4 {
        return renderState.chestEquipment.is(Items.ELYTRA);
        //?} else if >= 1.21.2 {
        //return renderState.chestItem.is(Items.ELYTRA);
        //?} else {
        /*return player.getItemBySlot(EquipmentSlot.CHEST).getItem().equals(Items.ELYTRA);
        *///?}
    }

    public boolean hasChestplateEquipped() {
        //? if >= 1.21.4 {
        return !hasElytraEquipped() && !renderState.chestEquipment.isEmpty();
        //?} else if >= 1.21.2 {
        //return !hasElytraEquipped() && !renderState.chestItem.isEmpty();
        //?} else {
        /*return !hasElytraEquipped() && !player.getItemBySlot(EquipmentSlot.CHEST).isEmpty();
        *///?}
    }
}
