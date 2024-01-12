package org.plugins.pluginmc.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.plugins.pluginmc.utils.ChatUtil;

import java.util.Collections;

public class Gui {

    private final String guiName = ChatUtil.colorize(" &8> &aEFFECTS");

    public void openGui(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, guiName);
        ItemStack goldPickaxe = createItem(Material.GOLDEN_PICKAXE, "&e&lHASTE 2", ChatUtil.colorize("&8>> &7Price: &6&n10 blocks of emeralds&7."));
        ItemStack diamondPickaxe = createItem(Material.DIAMOND_PICKAXE, "&e&lHASTE 3", ChatUtil.colorize("&8>> &7Price: &6&n20 blocks of emeralds&7."));
        gui.setItem(3, goldPickaxe);
        gui.setItem(5, diamondPickaxe);
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
