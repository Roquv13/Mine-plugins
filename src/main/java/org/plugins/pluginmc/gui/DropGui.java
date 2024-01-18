package org.plugins.pluginmc.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.plugins.pluginmc.Item;
import org.plugins.pluginmc.Main;
import org.plugins.pluginmc.api.DropApi;
import org.plugins.pluginmc.events.BlockBreak;
import org.plugins.pluginmc.objects.DropChance;
import org.plugins.pluginmc.utils.ChatUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.plugins.pluginmc.events.BlockBreak.drops;

public class DropGui extends Item implements Listener {

    private final String guiName = ChatUtil.colorize("&6&lDrop &2&lGUI");

    public DropGui() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    public void openGui(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, guiName);
        Map<Integer, ItemStack> items = createItems();

        for (Map.Entry<Integer, ItemStack> entry : items.entrySet()) {
            gui.setItem(entry.getKey(), entry.getValue());
        }

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Map<Integer, ItemStack> items = createItems();

        if (event.getView().getTitle().equals(guiName) && event.getAction() == InventoryAction.PICKUP_ALL) {
            event.setCancelled(true); // Prevent item moving or swapping

            if (event.getCurrentItem() != null) {
                // Check which item was clicked
                for (ItemStack item : items.values()) {
                    if (event.getCurrentItem().getType() == item.getType()) {
                        Player player = (Player) event.getWhoClicked();

                        Material itemType = item.getType();

                        //addEffect(player, itemType);
                        break;
                    }
                }
            }
        }
    }

    public Map<Integer, ItemStack> createItems() {
        Map<Integer, ItemStack> items = new HashMap<>();

        int i = 0;
        for (DropChance drop : drops) {
            Material dropMaterial = drop.getMaterial();
            String dropMaterialName = drop.getMaterial().name();
            items.put(i, create(dropMaterial, dropMaterialName, "DROP ON/OFF"));
            i++;
        }

        return items;
    }
}
