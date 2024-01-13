package org.plugins.pluginmc.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.plugins.pluginmc.utils.ChatUtil;

import java.util.Collections;

public class ItemShopGui {

    private final String guiName = ChatUtil.colorize("&6&lItem &2&lShop");

    public void openGui(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, guiName);

        ItemStack gold = createItem(Material.GOLD_INGOT, "&6Gold &6Ingot", "&cPrice: &l&a5 &l&6coins ");
        ItemStack diamond = createItem(Material.DIAMOND, "&bDiamond", "&cPrice: &l&a20 &l&6coins ");
        ItemStack netherite = createItem(Material.NETHER_BRICK, "&0Netherite", "&cPrice: &l&a40 &l&6coins ");

        gui.setItem(1, gold);
        gui.setItem(2, diamond);
        gui.setItem(3, netherite);

        player.openInventory(gui);
    }

    public ItemStack createItem(Material material, String name, String lore) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatUtil.colorize(name));
        itemMeta.setLore(Collections.singletonList(lore));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
