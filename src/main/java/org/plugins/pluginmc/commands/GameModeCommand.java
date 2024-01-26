package org.plugins.pluginmc.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameModeCommand implements CommandExecutor {

    List<GameMode> gameModes = Arrays.asList(
            GameMode.SURVIVAL,
            GameMode.CREATIVE,
            GameMode.ADVENTURE,
            GameMode.SPECTATOR
    );

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            System.out.println("Only for players.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.isOp()) {
            player.sendMessage("You have no permission!");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("You need to type arguments.");
            return false;
        }

        if (args.length == 1) {

        }

        if (args.length == 2) {

        }

        return true;
    }

    public boolean isInt(String toBeInt) {
        try {
            Integer.parseInt(toBeInt);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
