package dev.tr7zw.transition.nms;

import lombok.experimental.UtilityClass;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

@UtilityClass
public class ClientUtil {

    private final static Minecraft MC = Minecraft.getInstance();

    public static void sendChatMessage(Component message) {
        //#if MC < 11900
        //$$ if (MC.player != null)
        //$$ MC.player.sendMessage(message, null);
        //#elseif MC <= 12101
        //$$ if (MC.player != null)
        //$$ MC.player.sendSystemMessage(message);
        //#else
        MC.getChatListener().handleSystemMessage(message, false);
        //#endif
    }

}
