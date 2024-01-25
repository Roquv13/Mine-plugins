package org.plugins.pluginmc.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.plugins.pluginmc.Main;
import org.plugins.pluginmc.utils.ChatUtil;

import java.util.HashMap;
import java.util.Map;

public class PlayerJoin implements Listener {

    private final Map<Player, Long> playerJoinTimes = new HashMap<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        for (Player invisiblePlayer : Main.invisiblePlayers) {
            player.hidePlayer(invisiblePlayer);
        }

        player.sendMessage(ChatUtil.colorize(Main.configManager.getJoinMessage().replace("{PLAYER}", player.getName())));

        playerJoinTimes.put(player, System.currentTimeMillis());
    }

    public long getTotalPlayTime(Player player) {
        if (!playerJoinTimes.containsKey(player))
            return 0;

        long joinTime = playerJoinTimes.get(player);
        return System.currentTimeMillis() - joinTime;
    }

    public void removePlayer(Player player) {
        playerJoinTimes.remove(player);
    }
}
