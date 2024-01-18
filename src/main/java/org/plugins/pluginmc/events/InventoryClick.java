package org.plugins.pluginmc.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import org.plugins.pluginmc.api.DropApi;
import org.plugins.pluginmc.gui.DropGui;

public class InventoryClick implements Listener {

    DropApi dropApi = new DropApi();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;

        Player player = (Player) event.getWhoClicked();

        if (!ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase("Drop GUI")) return;

        event.setCancelled(true);

        if (event.getCurrentItem() == null) return;

        Material clickedType = event.getCurrentItem().getType();

        if (dropApi.getDisabledDrops(player).contains(clickedType)) {
            dropApi.enableDrop(player, clickedType);
        } else {
            dropApi.disableDrop(player, clickedType);
        }

        player.closeInventory();
        player.openInventory(DropGui.getInventory(player));
    }

}
