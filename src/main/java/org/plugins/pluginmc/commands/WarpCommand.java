package org.plugins.pluginmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class WarpCommand {

    public static Inventory getInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 9, "Warps");

        return inventory;
    }
}
