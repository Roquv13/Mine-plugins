package org.plugins.pluginmc.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.plugins.pluginmc.Main;

public class AsyncPlayerChat implements Listener {
    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        // Op can use chat
        if (player.isOp()) return;

        // If chat is on, stop code
        if (Main.isChatEnabled) return;

        event.setCancelled(true);
        player.sendMessage("Chat is off.");
    }
}
