package org.plugins.pluginmc.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.plugins.pluginmc.Item;
import org.plugins.pluginmc.Main;
import org.plugins.pluginmc.api.DropApi;
import org.plugins.pluginmc.events.BlockBreak;
import org.plugins.pluginmc.objects.DropChance;
import org.plugins.pluginmc.utils.ChatUtil;
import org.plugins.pluginmc.utils.ItemBuilderUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.plugins.pluginmc.events.BlockBreak.drops;

public class DropGui extends Item implements Listener {

    DropApi dropApi = new DropApi();

    private final String guiName = ChatUtil.colorize("&6&lDrop &2&lGUI");

    public DropGui() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    public Inventory getInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 9, guiName);

        List<Material> disabled = dropApi.getDisabledDrops(player);

        for (int i = 0; i < drops.length; i++) {
            DropChance dropChance = drops[i];
            inventory.setItem(
                    i,
                    new ItemBuilderUtil(dropChance.getMaterial(), 1)
                    .setName((disabled.contains(dropChance.getMaterial()) ? ChatColor.RED : ChatColor.GREEN) + dropChance.getMaterial().toString().toLowerCase())
                    .toItemStack()
            );
        }

        return inventory;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;

        Player player = (Player) event.getWhoClicked();

        if (!ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase("Drop GUI")) return;

        event.setCancelled(true);

        if (event.getCurrentItem() == null) return;

        Material clickedType = event.getCurrentItem().getType();

        if (dropApi.getDisabledDrops(player).contains(clickedType)) {
            dropApi.enableDrop(player, clickedType);
        } else {
            dropApi.disableDrop(player, clickedType);
        }

        player.closeInventory();
        player.openInventory(getInventory(player));
    }
}
