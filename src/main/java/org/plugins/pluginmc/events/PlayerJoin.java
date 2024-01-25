package org.plugins.pluginmc.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.plugins.pluginmc.Main;
import org.plugins.pluginmc.utils.ChatUtil;

public class PlayerJoin implements Listener {

    Main main = Main.getInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        for (Player invisiblePlayer : Main.invisiblePlayers) {
            player.hidePlayer(invisiblePlayer);
        }

        player.sendMessage(ChatUtil.colorize(Main.configManager.getJoinMessage().replace("{PLAYER}", player.getName())));

        main.playerJoinTimes.put(player, System.currentTimeMillis());
    }
}
