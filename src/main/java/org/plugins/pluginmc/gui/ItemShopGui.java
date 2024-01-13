package org.plugins.pluginmc.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.plugins.pluginmc.utils.ChatUtil;

import java.util.Collections;

public class ItemShopGui implements Listener {

    private final String guiName = ChatUtil.colorize("&6&lItem &2&lShop");

    private ItemStack createGoldIngotItem() {
        return createItem(Material.GOLD_INGOT, "&e&lGold Ingot", "&cPrice: &l&a5 &l&6coins ");
    }
    private ItemStack createDiamondItem() {
        return createItem(Material.DIAMOND, "&b&lDiamond", "&cPrice: &l&a20 &l&6coins ");
    }
    private ItemStack createNetheriteBrickItem() {
        return createItem(Material.NETHER_BRICK, "&8&lNetherite", "&cPrice: &l&a40 &l&6coins ");
    }

    public void openGui(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, guiName);

        gui.setItem(0, createGoldIngotItem());
        gui.setItem(1, createDiamondItem());
        gui.setItem(2, createNetheriteBrickItem());

        player.openInventory(gui);
    }

    public ItemStack createItem(Material material, String name, String lore) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatUtil.colorize(name));
        itemMeta.setLore(Collections.singletonList(ChatUtil.colorize(lore)));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
