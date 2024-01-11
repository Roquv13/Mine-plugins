package org.plugins.pluginmc.manager;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private FileConfiguration config;

    private ConfigManager(FileConfiguration config) {
        this.config = config;
        config.options().copyDefaults(true);
    }

    public String getJoinMessage() {
        return config.getString("join_message");
    }
}
