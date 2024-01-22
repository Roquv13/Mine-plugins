package org.plugins.pluginmc.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.plugins.pluginmc.Main;
import org.plugins.pluginmc.api.DropApi;
import org.plugins.pluginmc.objects.DropChance;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.bukkit.Bukkit.getLogger;

public class BlockBreak implements Listener {

    DropApi dropApi = new DropApi();

    public static DropChance[] drops = {
            new DropChance(Material.EMERALD, 34),
            new DropChance(Material.DIAMOND, 37),
            new DropChance(Material.COAL, 56),
            new DropChance(Material.SAND, 76)
    };

    @EventHandler
    public void onBlockBreakStone(BlockBreakEvent event) {
        Material material = event.getBlock().getType();
        if (material != Material.STONE) return;

        Player player = event.getPlayer();

        int random = ThreadLocalRandom.current().nextInt(1, 101);

        List<Material> availableItems = new ArrayList<>();

        // Console log for random number
        //getLogger().info("Random: " + random);

        for (DropChance drop : drops) {
            if (dropApi.getDisabledDrops(player).contains(drop.getMaterial())) continue;

            if (random <= drop.getChance()) {
                // Console logs for drop chance of materials in ArrayList
                //getLogger().info(drop.getMaterial().name() + " drop chance: " + drop.getChance());
                availableItems.add(drop.getMaterial());
            }
        }

        // Stop code if list of available items is empty
        if (availableItems.isEmpty()) return;

        Material randomItem = getRandomItem(availableItems);
        // Console logs for material drop
        //getLogger().info("Dropping " + randomItem);
        player.getWorld().dropItemNaturally(player.getLocation(), new ItemStack(randomItem));

    }

    private static Material getRandomItem(List<Material> items) {

        Random random = new Random();

        int randomIndex = random.nextInt(items.size());

        return items.get(randomIndex);
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

    @EventHandler
    public void onBlockBreakStone2(BlockBreakEvent event) {
        Block block = event.getBlock();
        Location location1 = block.getLocation();
        Location location2 = location1.clone().subtract(0, 1, 0);

        if (block.getType() == Material.STONE && location2.getBlock().getType() == Material.AIR) {
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    if (location2.getBlock().getType() == Material.END_STONE) {
                        block.setType(Material.STONE);
                    }
                }
            }, 20L);
        }
    }
}
