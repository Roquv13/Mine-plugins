package org.plugins.pluginmc.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.plugins.pluginmc.Main;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        for (Player invisiblePlayer : Main.invisiblePlayers) {
            player.hidePlayer(invisiblePlayer);
        }
    }
}
