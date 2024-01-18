package org.plugins.pluginmc.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.plugins.pluginmc.Item;
import org.plugins.pluginmc.Main;
import org.plugins.pluginmc.api.DropApi;
import org.plugins.pluginmc.objects.DropChance;
import org.plugins.pluginmc.utils.ItemBuilderUtil;

import java.util.List;

import static org.plugins.pluginmc.events.BlockBreak.drops;

public class DropGui extends Item implements Listener {

    static DropApi dropApi = new DropApi();

    public DropGui() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    public static Inventory getInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 9, "Drop Menu");

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
}
