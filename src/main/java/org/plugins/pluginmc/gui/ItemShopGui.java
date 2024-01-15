package org.plugins.pluginmc.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.plugins.pluginmc.Item;
import org.plugins.pluginmc.Main;
import org.plugins.pluginmc.utils.ChatUtil;

import java.util.HashMap;
import java.util.Map;

public class ItemShopGui extends Item implements Listener {

    private final String guiName = ChatUtil.colorize("&6&lItem &2&lShop");

    public ItemShopGui() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    public void openGui(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, guiName);
        Map<Integer, ItemStack> items = itemList();

        for (Map.Entry<Integer, ItemStack> entry : items.entrySet()) {
            gui.setItem(entry.getKey(), entry.getValue());
        }

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Map<Integer, ItemStack> items = itemList();

        if (event.getView().getTitle().equals(guiName)) {
            event.setCancelled(true);

            if (event.getCurrentItem() != null) {
                ItemStack clickedItem = event.getCurrentItem();
                Player player = (Player) event.getWhoClicked();

                if (event.getClickedInventory() != null && event.getClickedInventory().equals(event.getView().getTopInventory())) {
                    handleItemPurchase(clickedItem, player);
                }
            }
        }
    }

    public void handleItemPurchase(ItemStack item, Player player) {
        player.getInventory().addItem(item);
        player.sendMessage(ChatUtil.colorize("&aYou purchased " + item.getItemMeta().getDisplayName()));
    }
}
