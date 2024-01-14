package org.plugins.pluginmc.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.plugins.pluginmc.Main;
import org.plugins.pluginmc.utils.ChatUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ItemShopGui extends Item implements Listener {

    private final String guiName = ChatUtil.colorize("&6&lItem &2&lShop");

    public Map<Integer, ItemStack> createItems() {
        Map<Integer, ItemStack> items = new HashMap<>();

        ItemStack goldIngot = create(Material.GOLD_INGOT, "&e&lGold Ingot", "&cPrice: &l&a5 &l&6coins ");
        ItemStack diamond = create(Material.DIAMOND, "&b&lDiamond", "&cPrice: &l&a20 &l&6coins ");
        ItemStack netherIngot = create(Material.NETHERITE_INGOT, "&8&lNetherite", "&cPrice: &l&a40 &l&6coins ");
        ItemStack diamondSword = create(Material.DIAMOND_SWORD, "&b&lDiamond Sword", "&cPrice: &l&a35 &l&6coins ");
        ItemStack diamondPickaxe = create(Material.DIAMOND_PICKAXE, "&b&lDiamond Pickaxe", "&cPrice: &l&a55 &l&6coins ");
        ItemStack upgradeTemplate = create(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE, "&b&lUpgrade Template", "&cPrice: &l&a80 &l&6coins ");

        items.put(0, goldIngot);
        items.put(1, diamond);
        items.put(2, netherIngot);
        items.put(3, diamondSword);
        items.put(4, diamondPickaxe);
        items.put(5, upgradeTemplate);

        return items;
    }

    public void openGui(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, guiName);
        Map<Integer, ItemStack> items = createItems();

        for (Map.Entry<Integer, ItemStack> entry : items.entrySet()) {
            gui.setItem(entry.getKey(), entry.getValue());
        }

        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Map<Integer, ItemStack> items = createItems();

        if (event.getView().getTitle().equals(guiName)) {
            event.setCancelled(true);

            if (event.getCurrentItem() != null) {
                for (ItemStack item : items.values()) {
                    if (event.getCurrentItem().getType() == item.getType()) {

                        Player player = (Player) event.getWhoClicked();

                        player.getInventory().addItem(item);
                    }
                }
            }
        }
    }
}
