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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.plugins.pluginmc.Main;
import org.plugins.pluginmc.utils.ChatUtil;

import java.util.Collections;

public class Gui implements Listener {

    private final String guiName = ChatUtil.colorize(" &8> &aEFFECTS");

    public void openGui(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, guiName);
        ItemStack goldPickaxe = createItem(Material.GOLDEN_PICKAXE, "&e&lHASTE 2", "&8>> &7Price: &6&n10 blocks of emeralds&7.");
        ItemStack diamondPickaxe = createItem(Material.DIAMOND_PICKAXE, "&e&lHASTE 3", "&8>> &7Price: &6&n20 blocks of emeralds&7.");
        gui.setItem(3, goldPickaxe);
        gui.setItem(5, diamondPickaxe);
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance()); // Assuming YourPlugin has a method getInstance()

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

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(guiName)) {
            event.setCancelled(true); // Prevent item moving or swapping

            if (event.getCurrentItem() != null) {
                // Check which item was clicked
                if (event.getCurrentItem().getType() == Material.GOLDEN_PICKAXE) {
                    // Handle the effect for the gold pickaxe
                    Player player = (Player) event.getWhoClicked();
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 600, 1));
                    player.sendMessage(ChatUtil.colorize("&aYou have gained HASTE 2!"));
                } else if (event.getCurrentItem().getType() == Material.DIAMOND_PICKAXE) {
                    // Handle the effect for the diamond pickaxe
                    Player player = (Player) event.getWhoClicked();
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 600, 2));
                    player.sendMessage(ChatUtil.colorize("&aYou have gained HASTE 3!"));
                }
            }
        }
    }
}
