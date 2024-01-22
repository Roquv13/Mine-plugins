package org.plugins.pluginmc.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.plugins.pluginmc.Main;

public class BlockPlace implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Location location1 = block.getLocation();
        Location location2 = location1.clone().add(0, 1, 0);

        if (block.getType() == Material.END_STONE && location2.getBlock().getType() == Material.AIR) {
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    location2.getBlock().setType(Material.STONE);
                }
            }, 20L);
        }
    }
}
