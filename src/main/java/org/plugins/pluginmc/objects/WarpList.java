package org.plugins.pluginmc.objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class WarpList {

    public YamlConfiguration config;

    public WarpList() {
        File configFile = new File("plugins/plugin-mc/warps.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public LinkedHashMap<String, Location> loadWarps() {
        LinkedHashMap<String, Location> warps = new LinkedHashMap<>();

        ConfigurationSection warpsSection = config.getConfigurationSection("warps");
        if (warpsSection != null) {
            Map<String, Object> warpsData = warpsSection.getValues(false);

            for (String name : warpsData.keySet()) {
                ConfigurationSection warpData = warpsSection.getConfigurationSection(name);

                String worldName = warpData.getString("world");
                double x = warpData.getDouble("x");
                double y = warpData.getDouble("y");
                double z = warpData.getDouble("z");

                Location location = new Location(Bukkit.getWorld(worldName), x, y, z);
                warps.put(name, location);
            }
        }

        return warps;
    }
    public void addWarp(String name, Location location) {
        config.set("warps." + name + ".world", location.getWorld().getName());
        config.set("warps." + name + ".x", location.getX());
        config.set("warps." + name + ".y", location.getY());
        config.set("warps." + name + ".z", location.getZ());
        saveConfig();
    }

    public void saveConfig() {
        try {
            config.save(new File("plugins/plugin-mc/warps.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
