package org.plugins.pluginmc.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.plugins.pluginmc.Main;
import org.plugins.pluginmc.utils.ChatUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        for (Player invisiblePlayer : Main.invisiblePlayers) {
            player.hidePlayer(invisiblePlayer);
        }

        player.sendMessage(ChatUtil.colorize(Main.configManager.getJoinMessage().replace("{PLAYER}", player.getName())));

        List<String> header = new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Discord: "));
        List<String> footer = new ArrayList<>(Arrays.asList(ChatColor.GREEN + "Server IP: " + Bukkit.getIp()));
    }
}
