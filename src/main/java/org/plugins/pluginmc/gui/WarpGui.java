package org.plugins.pluginmc.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.plugins.pluginmc.objects.WarpList;
import org.plugins.pluginmc.utils.ItemBuilderUtil;

public class WarpGui {

    public static Inventory getInventory(Player player) {
        WarpList warpList = new WarpList();

        Inventory inventory = Bukkit.createInventory(player, 9, "Warps");

        inventory.setItem(
                0,
                new ItemBuilderUtil(Material.ENCHANTING_TABLE, 1)
                        .setName(ChatColor.LIGHT_PURPLE + warpList.warpNames.get(0))
                        .toItemStack()
        );

        inventory.setItem(
                1,
                new ItemBuilderUtil(Material.OBSIDIAN, 1)
                        .setName(ChatColor.DARK_GRAY + warpList.warpNames.get(1))
                        .toItemStack()
        );

        inventory.setItem(
                2,
                new ItemBuilderUtil(Material.OAK_WOOD, 1)
                        .setName(ChatColor.DARK_GREEN + warpList.warpNames.get(2))
                        .toItemStack()
        );

        inventory.setItem(
                3,
                new ItemBuilderUtil(Material.STONE_HOE, 1)
                        .setName(ChatColor.BLUE + warpList.warpNames.get(3))
                        .toItemStack()
        );

        return inventory;
    }
}
