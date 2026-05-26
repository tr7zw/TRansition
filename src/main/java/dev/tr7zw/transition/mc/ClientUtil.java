package dev.tr7zw.transition.mc;

import lombok.experimental.UtilityClass;
import net.minecraft.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.*;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.*;

@UtilityClass
public class ClientUtil {

    private final static Minecraft MC = Minecraft.getInstance();

    public static void sendChatMessage(Component message) {
        // spotless:off
        //$ send_chat_message
        MC.gui.hud.getChat().addClientSystemMessage(message);
        // spotless:on
    }

    public static void sendActionBarMessage(Component message) {
        //$ send_action_bar_message
        MC.gui.hud.setOverlayMessage(message, false);
    }

    public static void sendToast(Component titel, Component message) {
        // spotless:off
        //$ send_toast
        MC.gui.toastManager().addToast(new SystemToast(SystemToast.SystemToastId.PERIODIC_NOTIFICATION, titel, message));
        // spotless:on
    }

    public static void playSound(SoundEvent sound, float volume, float pitch) {
        if (MC.player != null)
            MC.player.playSound(sound, volume, pitch);
    }

}
