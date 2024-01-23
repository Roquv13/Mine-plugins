package org.plugins.pluginmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.plugins.pluginmc.api.HomeApi;

public class RemoveHomeCommand implements CommandExecutor {

    HomeApi homeApi = new HomeApi();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof ConsoleCommandSender) return true;

        Player player = (Player) sender;

        if (args.length != 1) return false;

        if (!homeApi.removeHome(player, args[0])) {
            player.sendMessage("Home with that name doesn't exists. Try again.");
            return false;
        }

        player.sendMessage("Home deleted.");

        return true;
    }
}