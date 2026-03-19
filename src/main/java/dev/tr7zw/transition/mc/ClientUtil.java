package dev.tr7zw.transition.mc;

import lombok.experimental.UtilityClass;
import net.minecraft.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.*;
import net.minecraft.network.chat.Component;

@UtilityClass
public class ClientUtil {

    private final static Minecraft MC = Minecraft.getInstance();

    public static void sendChatMessage(Component message) {
        // spotless:off
        //$ send_chat_message
        MC.getChatListener().handleSystemMessage(message, false);
        // spotless:on
    }

    public static void sendActionBarMessage(Component message) {
        MC.gui.setOverlayMessage(message, false);
    }

    public static void sendToast(Component titel, Component message) {
        //$ send_toast
        MC.getToastManager().addToast(new SystemToast(SystemToast.SystemToastId.PERIODIC_NOTIFICATION, titel, message));
    }

}
