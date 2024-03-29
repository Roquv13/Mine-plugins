package org.plugins.pluginmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

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

        GameMode selected = null;

        if (isInt(args[0])) {
            int gmInt = Integer.parseInt(args[0]);

            if (gmInt >= 0 && gmInt < 4) {
                selected = gameModes.get(gmInt);
            } else {
                player.sendMessage("Your number is out of range.");
            }

        } else {
            String gmName = args[0].toUpperCase();

            if (isGameMode(gmName)) {
                selected = GameMode.valueOf(gmName);
            } else {
                player.sendMessage("This game mode does not exist");
            }

        }

        if (args.length == 1) {
            player.setGameMode(selected);
            player.sendMessage("Now your gamemode is " + selected.toString());
            return true;
        }

        if (args.length == 2) {
            Player target = Bukkit.getPlayer(args[1]);

            if (target == null) {
                player.sendMessage("This player doesn't exists or is offline.");
                return true;
            }

            target.setGameMode(selected);
            player.sendMessage("Player " + target.getName() + " has gamemode set to " + selected.toString());
            return true;
        }

        return true;
    }

    public boolean isGameMode(String toBeGameMode) {
        try {
            GameMode.valueOf(toBeGameMode);
        } catch (Exception e) {
            return false;
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
