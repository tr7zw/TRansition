package dev.tr7zw.transition.loader.networking;

//? if >= 1.20.2 {

import net.minecraft.network.*;
import net.minecraft.network.protocol.common.custom.*;
import net.minecraft.resources.*;
//? }

//? if >= 1.20.2 {

public interface CustomPacketPayloadSupport extends CustomPacketPayload {
    //? } else {

    /*import net.minecraft.network.*;
    import net.minecraft.resources.*;
    
    public interface CustomPacketPayloadSupport {
    *///? }

    Identifier id();

    void write(FriendlyByteBuf paramFriendlyByteBuf);

    CustomPacketPayloadSupport read(FriendlyByteBuf paramFriendlyByteBuf);

    //? if >= 1.20.5 {

    default net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return new net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type<CustomPacketPayload>(id());
    }

    //? }

}
