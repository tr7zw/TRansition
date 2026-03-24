package dev.tr7zw.transition.mc;

import java.util.List;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class InventoryUtil {

    public static Inventory getInventory(Player player) {
        //$ get_inventory_from_player
        return player.getInventory();
    }

    public static ItemStack getSelected(Inventory inventory) {
        //$ get_selected_item
        return inventory.getSelectedItem();
    }

    public static ItemStack getOffhand(Inventory inventory) {
        //$ get_offhand_item
        return inventory.getItem(Inventory.SLOT_OFFHAND);
    }

    public static int getSelectedId(Inventory inventory) {
        //$ get_selected_id
        return inventory.getSelectedSlot();
    }

    public static List<ItemStack> getNonEquipmentItems(Inventory inventory) {
        //$ get_non_equipment_items
        return inventory.getNonEquipmentItems();
    }

}
