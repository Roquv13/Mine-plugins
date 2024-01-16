package org.plugins.pluginmc.alerts;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        if (block.getType() != Material.DIAMOND_ORE) return;

        Player player = event.getPlayer();

        for (Player playerOnline : Bukkit.getOnlinePlayers()) {
            if (!playerOnline.isOp()) continue;

            playerOnline.sendMessage(ChatColor.GRAY + "Player " + ChatColor.AQUA + player.getName() + ChatColor.GRAY + " dug up diamond ore.");
        }
    }
}
