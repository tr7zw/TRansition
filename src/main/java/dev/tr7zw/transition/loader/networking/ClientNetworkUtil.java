//? if fabric {
package dev.tr7zw.transition.loader.networking;

import java.util.function.*;

import lombok.extern.java.*;
import net.fabricmc.fabric.api.client.networking.v1.*;

import net.minecraft.client.Minecraft;
import net.minecraft.network.*;

import net.minecraft.resources.*;

//? if >= 1.20.2 {

import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
//? } else {

/*import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;
*///? }
   //? if <= 1.20.1 {

/*import io.netty.buffer.Unpooled;
*///? }
   //? if >= 1.20.5 {

import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type;
import net.fabricmc.fabric.impl.networking.PayloadTypeRegistryImpl;
import net.minecraft.server.level.*;
//? } else {

/*import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.multiplayer.ClientPacketListener;
*///? }

@Log
public class ClientNetworkUtil {

    private final static RegisterHander HANDLER = new RegisterImpl();

    private ClientNetworkUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void sendPacket(CustomPacketPayloadSupport packet) {
        //? if >= 1.20.2 {

        Minecraft.getInstance().getConnection().send(new ServerboundCustomPayloadPacket(packet));
        //? } else {

        /*var buf = new FriendlyByteBuf(Unpooled.buffer(8));
        packet.write(buf);
        Minecraft.getInstance().getConnection()
                .send(new ServerboundCustomPayloadPacket(packet.id(), buf));
        *///? }
    }

    public static void registerPackets(Consumer<RegisterHander> handler) {
        ClientPlayConnectionEvents.INIT.register((handle, client) -> {
            handler.accept(HANDLER);
        });
    }

    public interface RegisterHander {

        default <T extends CustomPacketPayloadSupport> void registerServerCustomPacket(T dummy) {
            registerServerCustomPacket((Class<T>)dummy.getClass(), dummy.id(), b -> (T)dummy.read(b));
        }

        <T extends CustomPacketPayloadSupport> void registerServerCustomPacket(Class<T> type, Identifier id,
                Function<FriendlyByteBuf, T> streamMemberEncoder);

        default <T extends CustomPacketPayloadSupport> void registerClientCustomPacket(T dummy, Consumer<T> action) {
            registerClientCustomPacket((Class<T>)dummy.getClass(), dummy.id(), b -> (T)dummy.read(b), action);
        }

        <T extends CustomPacketPayloadSupport> void registerClientCustomPacket(Class<T> type, Identifier id,
                Function<FriendlyByteBuf, T> streamMemberEncoder, Consumer<T> action);
    }

    private static class RegisterImpl implements RegisterHander {

        @Override
        public <T extends CustomPacketPayloadSupport> void registerServerCustomPacket(Class<T> type, Identifier id,
                Function<FriendlyByteBuf, T> streamMemberEncoder) {
            //? if > 1.20.5 {

            if (PayloadTypeRegistryImpl.SERVERBOUND_PLAY.get(id) == null) {
                PayloadTypeRegistryImpl.SERVERBOUND_PLAY.register(new Type<>(id),
                        new StreamCodec<FriendlyByteBuf, T>() {

                            @Override
                            public T decode(FriendlyByteBuf buffer) {
                                return streamMemberEncoder.apply(buffer);
                            }

                            @Override
                            public void encode(FriendlyByteBuf buffer, T object) {
                                object.write(buffer);
                            }

                        });
            }
            //? }
        }

        @Override
        public <T extends CustomPacketPayloadSupport> void registerClientCustomPacket(Class<T> type, Identifier id,
                Function<FriendlyByteBuf, T> streamMemberEncoder, Consumer<T> action) {
            Consumer<T> catchingAction = (payload) -> {
                try {
                    action.accept(payload);
                } catch (Exception e) {
                    log.log(java.util.logging.Level.SEVERE, "Error while processing packet!", e);
                }
            };

            //? if > 1.20.5 {

            if (PayloadTypeRegistryImpl.CLIENTBOUND_PLAY.get(id) == null) {
                PayloadTypeRegistryImpl.CLIENTBOUND_PLAY.register(new Type<>(id),
                        new StreamCodec<FriendlyByteBuf, T>() {

                            @Override
                            public T decode(FriendlyByteBuf buffer) {
                                return streamMemberEncoder.apply(buffer);
                            }

                            @Override
                            public void encode(FriendlyByteBuf buffer, T object) {
                                object.write(buffer);
                            }

                        });
            }
            ClientPlayNetworking.registerReceiver(new Type<T>(id), new ClientPlayNetworking.PlayPayloadHandler<T>() {

                @Override
                public void receive(T payload, ClientPlayNetworking.Context context) {
                    catchingAction.accept(payload);

                }
            });
            //? } else {

            /*ClientPlayNetworking.registerGlobalReceiver(id,
                new net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.PlayChannelHandler() {
            
                    @Override
                    public void receive(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf,
                            PacketSender responseSender) {
                        catchingAction.accept(streamMemberEncoder.apply(buf));
                    }
                });
            *///? }
        }
    }

}
//? }