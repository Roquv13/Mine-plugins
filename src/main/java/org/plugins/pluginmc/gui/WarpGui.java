package org.plugins.pluginmc.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.plugins.pluginmc.utils.ItemBuilderUtil;

public class WarpGui {

    public static Inventory getInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 9, "Warps");

        //4,5,6
        inventory.setItem(
                4,
                new ItemBuilderUtil(Material.ENCHANTING_TABLE, 1)
                        .setName(ChatColor.LIGHT_PURPLE + "Enchants")
                        .toItemStack()
        );

        inventory.setItem(
                5,
                new ItemBuilderUtil(Material.DIAMOND_SWORD, 1)
                        .setName(ChatColor.AQUA + "Mob Grinder")
                        .toItemStack()
        );

        inventory.setItem(
                6,
                new ItemBuilderUtil(Material.OAK_WOOD, 1)
                        .setName(ChatColor.DARK_GREEN + "Oak Wood")
                        .toItemStack()
        );

        return inventory;
    }
}
