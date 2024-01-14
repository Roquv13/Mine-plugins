package org.plugins.pluginmc.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.plugins.pluginmc.Main;
import org.plugins.pluginmc.utils.ChatUtil;

import java.util.HashMap;
import java.util.Map;

public class Gui extends Item implements Listener {

    private final String guiName = ChatUtil.colorize(" &8> &aEFFECTS");

    public Map<Integer, ItemStack> createItems() {
        Map<Integer, ItemStack> items = new HashMap<>();

        ItemStack goldPickaxe = create(Material.GOLDEN_PICKAXE, "&e&lHASTE 2", "&8>> &7Price: &6&n10 blocks of emeralds&7.");
        ItemStack diamondPickaxe = create(Material.DIAMOND_PICKAXE, "&e&lHASTE 3", "&8>> &7Price: &6&n20 blocks of emeralds&7.");

        items.put(3, goldPickaxe);
        items.put(5, diamondPickaxe);

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
