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

        inventory.setItem(
                0,
                new ItemBuilderUtil(Material.ENCHANTING_TABLE, 1)
                        .setName(ChatColor.LIGHT_PURPLE + "Enchants")
                        .toItemStack()
        );

        inventory.setItem(
                1,
                new ItemBuilderUtil(Material.OBSIDIAN, 1)
                        .setName(ChatColor.DARK_GRAY + "Nether Portal")
                        .toItemStack()
        );

        inventory.setItem(
                2,
                new ItemBuilderUtil(Material.OAK_WOOD, 1)
                        .setName(ChatColor.DARK_GREEN + "Oak Wood")
                        .toItemStack()
        );

        inventory.setItem(
                3,
                new ItemBuilderUtil(Material.STONE_HOE, 1)
                        .setName(ChatColor.BLUE + "Villagers")
                        .toItemStack()
        );

        return inventory;
    }
}
