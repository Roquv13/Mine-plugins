package org.plugins.pluginmc.tablist;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.plugins.pluginmc.events.PlayerJoin;

public class TabListUpdater extends BukkitRunnable{

    private final PlayerJoin playerJoin;

    public TabListUpdater(PlayerJoin playerJoin) {
        this.playerJoin = playerJoin;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            updateTabList(player);
        }
    }

    public void updateTabList(Player player) {
        // Header
        String header = "Welcome, " + player.getName();

        // Footer
        long totalTime = playerJoin.getTotalPlayTime(player);
        int hours = (int) (totalTime / 3600000);
        int minutes = (int) ((totalTime % 3600000) / 60000);
        int seconds = (int) ((totalTime % 60000) / 1000);
        String footer = "Play time: " + hours + "h " + minutes + "m " + seconds + "s";

        // Set up header and footer
        player.setPlayerListHeaderFooter(header, footer);
    }
}
