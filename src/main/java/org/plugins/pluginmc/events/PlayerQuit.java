package org.plugins.pluginmc.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    private final PlayerJoin playerJoin;

    public PlayerQuit(PlayerJoin playerJoin) {
        this.playerJoin = playerJoin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        playerJoin.removePlayer(player);
    }
}
