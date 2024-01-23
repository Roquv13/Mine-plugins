package org.plugins.pluginmc.manager;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private FileConfiguration config;

    public ConfigManager(FileConfiguration config) {
        this.config = config;
        config.options().copyDefaults(true);
    }

    public String getJoinMessage() {
        return config.getString("join_message");
    }

    public String getCorrectUsage() {
        return config.getString("messages.correct-usage");
    }

    public String getPermissionError() {
        return config.getString("messages.no-permissions");
    }

    public String getUsage() {
        return config.getString("messages.usage");
    }
}
