package org.plugins.pluginmc.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPing implements Listener {

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        event.setMotd(ChatColor.translateAlternateColorCodes('&',
        "&7New play mode &dSky&fBlock" + "\n" +
                "&7Server Discord:"
        ));
    }

}
