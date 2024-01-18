package org.plugins.pluginmc.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.plugins.pluginmc.DropChance;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.bukkit.Bukkit.getLogger;

public class BlockBreak implements Listener {

    DropChance[] drops = {
            new DropChance(Material.EMERALD, 34),
            new DropChance(Material.DIAMOND, 37),
            new DropChance(Material.COAL, 56),
            new DropChance(Material.SAND, 76)
    };

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() != Material.STONE) return;

        Player player = event.getPlayer();

        int random = ThreadLocalRandom.current().nextInt(1, 101);

        for (DropChance drop : drops) {
            // Console logs for random and drop chance
            getLogger().info("Random: " + random);
            getLogger().info("Drop chance: " + drop.getChance());

            if (drop.getChance() >= random) {
                // Console logs for material
                getLogger().info("Dropping " + drop.getMaterial().name());
                // Drop item to player
                player.getWorld().dropItemNaturally(player.getLocation(), new ItemStack(drop.getMaterial()));
                break;
            }
        }
    }

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
