package org.plugins.pluginmc;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info(ChatColor.GREEN + "Plugin > started");
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.GREEN + "Plugin > stopped");
    }
}
