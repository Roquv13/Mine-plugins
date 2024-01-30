package org.plugins.pluginmc.automessage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.List;

public class AutoMessageSender extends BukkitRunnable {

    private final FileConfiguration config;
    private final List<String> messages;
    private final String prefix;

    private int index = 0;

    public AutoMessageSender(JavaPlugin plugin, String filePath) {
        this.config = loadConfig(plugin, filePath);
        this.messages = config.getStringList("messages");
        this.prefix = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "Info" + ChatColor.DARK_GRAY + "] ";
    }

    private FileConfiguration loadConfig(JavaPlugin plugin, String filePath) {
        File file = new File(plugin.getDataFolder(), filePath);
        if (!file.exists()) {
            plugin.saveResource(filePath, false);
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    @Override
    public void run() {
        index = (index + 1 < messages.size() ? index + 1 : 0);

        for (Player players : Bukkit.getOnlinePlayers()) {
            players.sendMessage(prefix + messages.get(index));
        }
    }
}
