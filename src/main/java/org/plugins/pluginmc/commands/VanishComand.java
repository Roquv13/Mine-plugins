package org.plugins.pluginmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.plugins.pluginmc.Main;

public class VanishComand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof ConsoleCommandSender) return true;

        Player player = (Player) sender;

        if (!player.hasPermission("vanish.admin")) return true;

        if (Main.invisiblePlayers.contains(player)) {
            for (Player players : Bukkit.getOnlinePlayers()) {
                players.showPlayer(player);
            }
            Main.invisiblePlayers.remove(player);

            player.sendMessage("You are visible!");
        }
    }
}
