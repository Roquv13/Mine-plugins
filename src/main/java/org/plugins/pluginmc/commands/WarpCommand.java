package org.plugins.pluginmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.plugins.pluginmc.gui.WarpGui;
import org.plugins.pluginmc.objects.WarpList;

import java.util.LinkedHashMap;

public class WarpCommand implements CommandExecutor {

    private final WarpList warpList = new WarpList();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("Only for players.");
            return true;
        }

        Player player = (Player) sender;

        LinkedHashMap<String, Location> warps = warpList.loadWarps();

        if (args.length == 0) {
            player.openInventory(WarpGui.getInventory(player));
            return true;
        } else if (args.length == 1) {
            if (warps.isEmpty()) {
                player.sendMessage("Warp list is empty");
                return true;
            }

            if (warps.containsKey(args[0])) {
                Location location = warps.get(args[0]);
                player.teleport(new Location(Bukkit.getWorld("world"), location.getX(), location.getY(), location.getZ()));

                return true;
            } else if (args[0].equalsIgnoreCase("list")) {
                player.sendMessage(ChatColor.DARK_GREEN + "List of warps:");
                for (String name : warps.keySet()) {
                    player.sendMessage(ChatColor.GREEN + name);
                }

                return true;
            } else {
                return false;
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("add")) {
                String name = args[1];
                Location location = player.getLocation();

                warps.put(name, location);

                warpList.addWarp(name, location);

                player.sendMessage(ChatColor.GREEN + "Warp " + name +  " added.");
            } else if (args[0].equalsIgnoreCase("del")) {
                String name = args[1];

                warps.remove(name);

                // Delete warp from config
                warpList.config.set("warps." + name, null);
                warpList.saveConfig();

                player.sendMessage(ChatColor.RED + "Warp " + name +  " deleted.");
            }

            return true;
        } else {
            player.sendMessage("This argument doesn't exist.");
            return false;
        }
    }
}
