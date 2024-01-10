package org.plugins.pluginmc;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info(ChatColor.GREEN + "Plugin > started");
        getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.GREEN + "Plugin > stopped");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.getPlayer().sendMessage(ChatColor.BLUE + p.getName() + "Welcome to server!");
    }
}
