package dev.tr7zw.transition.mc;

import java.util.List;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class InventoryUtil {

    public static Inventory getInventory(Player player) {
        //#if MC >= 11700
        return player.getInventory();
        //#else
        //$$ return player.inventory;
        //#endif
    }

    public static ItemStack getSelected(Inventory inventory) {
        //#if MC >= 12105
        return inventory.getSelectedItem();
        //#else
        //$$ return inventory.getSelected();
        //#endif
    }

    public static ItemStack getOffhand(Inventory inventory) {
        //#if MC >= 12105
        return inventory.getItem(Inventory.SLOT_OFFHAND);
        //#else
        //$$ return inventory.offhand.get(0);
        //#endif
    }

    public static int getSelectedId(Inventory inventory) {
        //#if MC >= 12105
        return inventory.getSelectedSlot();
        //#else
        //$$ return inventory.selected;
        //#endif
    }

    public static List<ItemStack> getNonEquipmentItems(Inventory inventory) {
        //#if MC >= 12105
        return inventory.getNonEquipmentItems();
        //#else
        //$$ return inventory.items;
        //#endif
    }

}
