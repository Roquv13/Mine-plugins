package org.plugins.pluginmc.events;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.generator.structure.Structure;
import org.bukkit.util.StructureSearchResult;
import org.plugins.pluginmc.api.DropApi;
import org.plugins.pluginmc.gui.DropGui;
import org.plugins.pluginmc.objects.DropChance;
import org.plugins.pluginmc.objects.WarpList;

import static org.plugins.pluginmc.events.BlockBreak.drops;

public class InventoryClick implements Listener {

    DropApi dropApi = new DropApi();

    WarpList warpList = new WarpList();

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

        if (!event.getView().getTitle().equalsIgnoreCase("Warps")) return;

        event.setCancelled(true);

        Material eventMaterial = event.getCurrentItem().getType();

        switch (eventMaterial) {
            case ENCHANTING_TABLE:
                player.teleport(warpList.warpsGui.get("Enchants"));
                break;
            case OBSIDIAN:
                player.teleport(warpList.warpsGui.get("Nether Portal"));
                break;
            case OAK_WOOD:
                player.teleport(warpList.warpsGui.get("Oak Wood"));
                break;
            case STONE_HOE:
                World world = Bukkit.getWorld("world");
                if (world != null) {
                    StructureSearchResult villageResult = world.locateNearestStructure(player.getLocation(), Structure.VILLAGE_PLAINS, 201, false);

                    if (villageResult != null && villageResult.getStructure() == Structure.VILLAGE_PLAINS) {
                        int x = villageResult.getLocation().getBlockX();
                        int z = villageResult.getLocation().getBlockZ();
                        // Get Y of village and add 1 to teleport on block
                        double y = world.getHighestBlockAt(x, z).getY() + 1;

                        player.sendMessage(ChatColor.AQUA + "Location of village || " + ChatColor.RED + "x: " + x + " y: " + y + " z: " + z);
                        player.teleport(new Location(world, x, y, z));
                    } else {
                        player.sendMessage(ChatColor.RED + "No village found or invalid structure type!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "World not found!");
                }
                break;
            default:
                player.sendMessage(ChatColor.RED + "NO LOCATION!");
        }
    }


}



