package dev.tr7zw.transition.mc;

import lombok.experimental.UtilityClass;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

@UtilityClass
public class ClientUtil {

    private final static Minecraft MC = Minecraft.getInstance();

    public static void sendChatMessage(Component message) {
        //$ send_chat_message
        MC.getChatListener().handleSystemMessage(message, false);
    }

}
