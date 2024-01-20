package org.plugins.pluginmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.plugins.pluginmc.gui.WarpGui;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class WarpCommand implements CommandExecutor {
    private YamlConfiguration config;

    public WarpCommand() {
        File configFile = new File("plugins/plugin-mc/warps.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof ConsoleCommandSender) return true;

        Player player = (Player) sender;

        LinkedHashMap<String, Location> warps = loadWarps();

        if (args.length == 0) {
            player.openInventory(WarpGui.getInventory(player));
        } else if (args.length == 1) {
            if ((!warps.isEmpty()) && warps.containsKey(args[0])) {
                Location location = warps.get(args[0]);
                player.teleport(new Location(Bukkit.getWorld("world"), location.getX(), location.getY(), location.getZ()));
            } else if ((!warps.isEmpty()) && args[0].equalsIgnoreCase("list")) {
                player.sendMessage(ChatColor.DARK_GREEN + "List of warps:");
                for (String name : warps.keySet()) {
                    player.sendMessage(ChatColor.GREEN + name);
                }
            } else {
                player.sendMessage("Warp list is empty.");
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("add")) {
                String name = args[1];
                Location location = player.getLocation();

                warps.put(name, location);

                config.set("warps." + name + ".world", location.getWorld().getName());
                config.set("warps." + name + ".x", location.getX());
                config.set("warps." + name + ".y", location.getY());
                config.set("warps." + name + ".z", location.getZ());
                saveConfig();

                player.sendMessage(ChatColor.GREEN + "Warp " + name +  " added.");
            } else if (args[0].equalsIgnoreCase("del")) {
                String name = args[1];

                warps.remove(name);

                // Delete warp from config
                config.set("warps." + name, null);
                saveConfig();

                player.sendMessage(ChatColor.RED + "Warp " + name +  " deleted.");
            }
        } else {
            player.sendMessage("This argument doesn't exist.");
        }

        return true;
    }

    private LinkedHashMap<String, Location> loadWarps() {
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

    private void saveConfig() {
        try {
            config.save(new File("plugins/plugin-mc/warps.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
