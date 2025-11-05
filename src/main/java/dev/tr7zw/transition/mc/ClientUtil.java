package dev.tr7zw.transition.mc;

import lombok.experimental.UtilityClass;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

@UtilityClass
public class ClientUtil {

    private final static Minecraft MC = Minecraft.getInstance();

    public static void sendChatMessage(Component message) {
        //? if < 1.19.0 {
        /*
         if (MC.player != null)
         MC.player.sendMessage(message, null);
        *///? } else if <= 1.21.1 {
        /*
         if (MC.player != null)
         MC.player.sendSystemMessage(message);
        *///? } else {

        MC.getChatListener().handleSystemMessage(message, false);
        //? }
    }

}
