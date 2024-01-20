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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class WarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
       if (sender instanceof ConsoleCommandSender) return true;

       Player player = (Player) sender;

        LinkedHashMap<String, Location> warps = new LinkedHashMap<>();

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
                player.sendMessage( ChatColor.GREEN + "Warp " + name +  " added.");
           } else if (args[0].equalsIgnoreCase("del")) {
                String name = args[1];

                warps.remove(name);
                player.sendMessage(ChatColor.RED + "Warp " + name +  " deleted.");
           }
       } else {
           player.sendMessage("This argument doesn't exists. Try");
       }

       return false;
    }
}
