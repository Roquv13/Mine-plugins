package org.plugins.pluginmc.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.plugins.pluginmc.api.DropApi;
import org.plugins.pluginmc.gui.DropGui;
import org.plugins.pluginmc.gui.WarpGui;
import org.plugins.pluginmc.objects.DropChance;

import static org.plugins.pluginmc.events.BlockBreak.drops;

public class InventoryClick implements Listener {

    DropApi dropApi = new DropApi();

    @EventHandler
    public void onInventoryClickDrop(InventoryClickEvent event) {
        if (event.getClickedInventory() == null || event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
            return;
        }

        Player player = (Player) event.getWhoClicked();

        // Check if the clicked inventory is the player's inventory
        if (event.getClickedInventory().getType() == InventoryType.PLAYER) {
            return;
        }

        // Check if the clicked inventory title is "Drop GUI"
        if (!ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase("Drop Menu")) {
            return;
        }

        event.setCancelled(true);

        Material clickedType = event.getCurrentItem().getType();

        // Check if the clicked item is part of your GUI items
        for (DropChance dropChance : drops) {
            if (dropChance.getMaterial() == clickedType) {
                dropApi.toggleDrop(player, clickedType, dropApi.getDisabledDrops(player).contains(clickedType));

                player.closeInventory();
                player.openInventory(DropGui.getInventory(player));
                break;
            }
        }
    }

    @EventHandler
    public void onInventoryClickWarp(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;

        Player player = (Player) event.getWhoClicked();

        Inventory clicked = event.getClickedInventory();

        Inventory warp = WarpGui.getInventory(player);

        if (clicked.getHolder() != warp.getHolder()) return;

        event.setCancelled(true);

        switch (event.getCurrentItem().getType()) {
            case ENCHANTING_TABLE:
                
                break;
            case DIAMOND_SWORD:
                break;
            case OAK_WOOD:
                break;
            default:
                return;
        }
    }
}



