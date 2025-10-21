package dev.tr7zw.transition.mc.entitywrapper;

import dev.tr7zw.transition.mc.PlayerUtil;
//#if MC >= 12109
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
//#elseif MC >= 12102
//$$import net.minecraft.client.renderer.entity.state.PlayerRenderState;
//#else
//$$import net.minecraft.client.player.AbstractClientPlayer;
//$$import net.minecraft.world.entity.EquipmentSlot;
//$$import net.minecraft.world.entity.player.PlayerModelPart;
//$$import dev.tr7zw.transition.mc.PlayerUtil;
//#endif
import net.minecraft.world.item.Items;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;

public class PlayerWrapper extends LivingEntityWrapper {
    //#if MC >= 12109
    private final AvatarRenderState renderState;

    @Override
    public AvatarRenderState getRenderState() {
        return renderState;
    }

    public PlayerWrapper(AvatarRenderState renderState) {
        super(renderState);
        this.renderState = renderState;
    }
    
    public net.minecraft.world.entity.Avatar getAvatar() {
        return (net.minecraft.world.entity.Avatar) super.getEntity();
    }
    
    //#elseif MC >= 12102
    //$$private final PlayerRenderState renderState;
    //$$@Override
    //$$public PlayerRenderState getRenderState() {
    //$$    return renderState;
    //$$}
    //$$public PlayerWrapper(PlayerRenderState renderState) {
    //$$    super(renderState);
    //$$    this.renderState = renderState;
    //$$}
    //#else
    //$$private final AbstractClientPlayer player;
    //$$
    //$$
    //$$public PlayerWrapper(AbstractClientPlayer player) {
    //$$        super(player);
    //$$        this.player = player;
    //$$}
    //#endif

    @Override
    public Player getEntity() {
        return (Player) super.getEntity();
    }

    public ResourceLocation getCapeTexture() {
        //#if MC >= 12109
        return PlayerUtil.getPlayerCape(getAvatar());
        //#elseif MC >= 12102
        //$$return renderState.skin.capeTexture();
        //#else
        //$$return PlayerUtil.getPlayerCape(player);
        //#endif
    }

    public boolean isPlayerInvisible() {
        //#if MC >= 12102
        return renderState.isInvisible;
        //#else
        //$$return player.isInvisible();
        //#endif
    }

    public boolean isCapeVisible() {
        //#if MC >= 12102
        return renderState.showCape && !isPlayerInvisible();
        //#else
        //$$return player.isModelPartShown(PlayerModelPart.CAPE);
        //#endif
    }

    public boolean hasElytraEquipped() {
        //#if MC >= 12104
        return renderState.chestEquipment.is(Items.ELYTRA);
        //#elseif MC >= 12102
        //$$return renderState.chestItem.is(Items.ELYTRA);
        //#else
        //$$return player.getItemBySlot(EquipmentSlot.CHEST).getItem().equals(Items.ELYTRA);
        //#endif
    }

    public boolean hasChestplateEquipped() {
        //#if MC >= 12104
        return !hasElytraEquipped() && !renderState.chestEquipment.isEmpty();
        //#elseif MC >= 12102
        //$$return !hasElytraEquipped() && !renderState.chestItem.isEmpty();
        //#else
        //$$return !hasElytraEquipped() && !player.getItemBySlot(EquipmentSlot.CHEST).isEmpty();
        //#endif
    }
}
