package org.plugins.pluginmc.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.plugins.pluginmc.Main;
import org.plugins.pluginmc.utils.ChatUtil;

import java.util.Collections;

public class ItemShopGui extends Item implements Listener {

    private final String guiName = ChatUtil.colorize("&6&lItem &2&lShop");

    public void openGui(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, guiName);

        ItemStack goldIngot = create(Material.GOLD_INGOT, "&e&lGold Ingot", "&cPrice: &l&a5 &l&6coins ");
        ItemStack diamond = create(Material.DIAMOND, "&b&lDiamond", "&cPrice: &l&a20 &l&6coins ");
        ItemStack netherIngot = create(Material.NETHERITE_INGOT, "&8&lNetherite", "&cPrice: &l&a40 &l&6coins ");
        ItemStack diamondSword = create(Material.DIAMOND_SWORD, "&b&lDiamond Sword", "&cPrice: &l&a35 &l&6coins ");
        ItemStack diamondPickaxe = create(Material.DIAMOND_PICKAXE, "&b&lDiamond Pickaxe", "&cPrice: &l&a55 &l&6coins ");
        ItemStack upgradeTemplate = create(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE, "&b&lUpgrade Template", "&cPrice: &l&a80 &l&6coins ");


        gui.setItem(0, goldIngot);
        gui.setItem(1, diamond);
        gui.setItem(2, netherIngot);
        gui.setItem(3, diamondSword);
        gui.setItem(4, diamondPickaxe);
        gui.setItem(5, upgradeTemplate);

        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());

        player.openInventory(gui);
    }
}
