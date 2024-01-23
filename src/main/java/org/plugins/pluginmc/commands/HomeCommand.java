package org.plugins.pluginmc.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.plugins.pluginmc.api.HomeApi;
import org.plugins.pluginmc.manager.ConfigManager;
import org.plugins.pluginmc.utils.ChatUtil;

import java.util.Map;

public class HomeCommand implements CommandExecutor {

    private ConfigManager configManager;

    HomeApi homeApi = new HomeApi();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof ConsoleCommandSender) return true;

        Player player = (Player) sender;

        if (homeApi.getHomesCount(player) == 0) {
            player.sendMessage("You have no homes added.");
            player.sendMessage(ChatUtil.colorize(configManager.getCorrectUsage().replace("{USAGE}", "/sethome or /sethome [home name]")));
            return true;
        }

        Map<String, Location> homes = homeApi.getHomes(player);

        if (homeApi.getHomesCount(player) == 1 && args.length == 0) {
            String homeName = homes.entrySet().iterator().next().getKey();

            if (!homeApi.teleportHome(player, homeName)) {
                player.sendMessage("Teleport to home failed.");
                return false;
            }
            player.sendMessage("Successfully teleported to home.");
        } else {
            if (args.length != 1) return false;

            if (!homeApi.homeExists(player, args[0])) {
                player.sendMessage("Home with that name doesn't exists.");
            }

            homeApi.teleportHome(player, args[0]);
            player.sendMessage("Successfully teleported to home.");
        }

        return true;
    }
}
