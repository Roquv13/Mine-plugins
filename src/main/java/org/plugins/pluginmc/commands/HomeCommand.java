package org.plugins.pluginmc.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.plugins.pluginmc.api.HomeApi;

import java.util.Map;

public class HomeCommand implements CommandExecutor {

    HomeApi homeApi = new HomeApi();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof ConsoleCommandSender) return true;

        Player player = (Player) sender;

        if (homeApi.getHomesCount(player) == 0) {
            player.sendMessage("You have no homes added.");
            return true;
        }

        Map<String, Location> homes = homeApi.getHomes(player);

        if (homeApi.getHomesCount(player) == 1) {
            if (!homeApi.teleportHome(player, homes.entrySet().iterator().next().getKey())) {
                player.sendMessage("Teleport to home failed.");
            }
            player.sendMessage("Successfully teleported to home.");
            return true;
        } else {
            if (args.length != 1) {
                player.sendMessage("/home [home name]");
                return true;
            }

            if (!homeApi.homeExists(player, args[0])) {
                player.sendMessage("Home with that name doesn't exists.");
                return true;
            }

            homeApi.teleportHome(player, args[0]);
            player.sendMessage("Successfully teleported to home.");
        }

        return false;
    }
}
