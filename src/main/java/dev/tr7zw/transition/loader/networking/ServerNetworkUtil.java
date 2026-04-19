//? if fabric {
package dev.tr7zw.transition.loader.networking;

import java.util.function.*;

import lombok.extern.java.*;
import net.fabricmc.fabric.api.networking.v1.*;
import net.minecraft.network.*;

import net.minecraft.resources.*;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;

//? if >= 1.20.5 {

//? }
//? if >= 1.20.2 {

import net.minecraft.network.protocol.common.*;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
//? } else {

/*import net.minecraft.network.protocol.game.*;
*///? }
   //? if <= 1.20.1 {

/*import io.netty.buffer.*;
*///? }
   //? if >= 1.20.5 {

import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.PlayPayloadHandler;
import net.fabricmc.fabric.impl.networking.PayloadTypeRegistryImpl;
import org.intellij.lang.annotations.*;
//? } else {

/*import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.PlayChannelHandler;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.MinecraftServer;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
*///? }

@Log
public class ServerNetworkUtil {

    private final static RegisterHander registerHandler = new RegisterImpl();

    private ServerNetworkUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void sendPacket(ServerPlayer player, CustomPacketPayloadSupport packet) {
        //? if >= 1.20.2 {

        player.connection.send(new ClientboundCustomPayloadPacket(packet));
        //? } else {

        /*var buf = new FriendlyByteBuf(Unpooled.buffer(8));
        packet.write(buf);
        player.connection.send(new ClientboundCustomPayloadPacket(packet.id(), buf));
        *///? }
    }

    public static void registerPackets(Consumer<RegisterHander> consumer) {
        ServerPlayConnectionEvents.INIT.register((phase, init) -> {
            consumer.accept(registerHandler);
        });
    }

    public interface RegisterHander {
        <T extends CustomPacketPayloadSupport> void registerClientCustomPacket(Class<T> type, Identifier id,
                Function<FriendlyByteBuf, T> streamMemberEncoder, BiConsumer<T, FriendlyByteBuf> streamDecoder);

        <T extends CustomPacketPayloadSupport> void registerServerCustomPacket(Class<T> type, Identifier id,
                Function<FriendlyByteBuf, T> streamMemberEncoder, BiConsumer<T, FriendlyByteBuf> streamDecoder,
                BiConsumer<T, ServerPlayer> action);
    }

    private static class RegisterImpl implements RegisterHander {

        @Override
        public <T extends CustomPacketPayloadSupport> void registerClientCustomPacket(Class<T> type, Identifier id,
                Function<FriendlyByteBuf, T> streamMemberEncoder, BiConsumer<T, FriendlyByteBuf> streamDecoder) {
            //? if > 1.20.5 {

            if (PayloadTypeRegistryImpl.CLIENTBOUND_PLAY.get(id) == null) {
                PayloadTypeRegistry.clientboundPlay().register(new Type<>(id), new StreamCodec<FriendlyByteBuf, T>() {

                    @Override
                    public T decode(FriendlyByteBuf buffer) {
                        return streamMemberEncoder.apply(buffer);
                    }

                    @Override
                    public void encode(FriendlyByteBuf buffer, T object) {
                        streamDecoder.accept(object, buffer);
                    }

                });
            }
            //? }
        }

        @Override
        public <T extends CustomPacketPayloadSupport> void registerServerCustomPacket(Class<T> type, Identifier id,
                Function<FriendlyByteBuf, T> streamMemberEncoder, BiConsumer<T, FriendlyByteBuf> streamDecoder,
                BiConsumer<T, ServerPlayer> action) {
            BiConsumer<T, ServerPlayer> catchingAction = (payload, player) -> {
                try {
                    action.accept(payload, player);
                } catch (Exception e) {
                    log.log(java.util.logging.Level.SEVERE, "Error while processing packet!", e);
                }
            };

            //? if > 1.20.5 {

            if (PayloadTypeRegistryImpl.SERVERBOUND_PLAY.get(id) == null) {
                PayloadTypeRegistry.serverboundPlay().register(new Type<>(id), new StreamCodec<FriendlyByteBuf, T>() {

                    @Override
                    public T decode(FriendlyByteBuf buffer) {
                        return streamMemberEncoder.apply(buffer);
                    }

                    @Override
                    public void encode(FriendlyByteBuf buffer, T object) {
                        streamDecoder.accept(object, buffer);
                    }

                });
            }
            ServerPlayNetworking.registerGlobalReceiver(new Type<T>(id), new PlayPayloadHandler<T>() {

                @Override
                public void receive(T payload, Context context) {
                    catchingAction.accept(payload, context.player());
                }
            });
            //? } else {

            /*ServerPlayNetworking.registerGlobalReceiver(id, new PlayChannelHandler() {
            
            @Override
            public void receive(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler,
                    FriendlyByteBuf buf, PacketSender responseSender) {
                catchingAction.accept(streamMemberEncoder.apply(buf), player);
            }
            });
            *///? }
        }
    }

}
//? }