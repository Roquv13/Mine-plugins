package org.plugins.pluginmc.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.plugins.pluginmc.DropChance;

import static org.bukkit.Bukkit.getLogger;

public class BlockBreak implements Listener {

    DropChance[] drops = {
            new DropChance(Material.DIAMOND, 37),
            new DropChance(Material.COAL, 56),
            new DropChance(Material.EMERALD, 34),
            new DropChance(Material.SAND, 76)
    };

    @EventHandler
    public void onBlockBreakDiamond(BlockBreakEvent event) {
        Block block = event.getBlock();

        if (block.getType() != Material.DIAMOND_ORE) return;

        Player player = event.getPlayer();

        //Skip if player is op
        if (player.isOp()) return;

        //Alert message
        String diamondAlert = "Player " + ChatColor.RED + player.getName() + ChatColor.WHITE + " dug up diamond ore.";

        //Send to console
        getLogger().info("Player " + player.getName() + " dug up diamond ore.");

        for (Player playerOnline : Bukkit.getOnlinePlayers()) {
            //Information send to player
            playerOnline.sendMessage(diamondAlert);
        }
    }
}
