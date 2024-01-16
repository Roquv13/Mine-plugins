package org.plugins.pluginmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.plugins.pluginmc.Main;

public class ChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!sender.isOp()) return true;

        if (args.length != 1) return true;

        switch (args[0]) {
            case "on":
                Main.isChatEnabled = true;
                sender.sendMessage("Chat ON.");

                break;
            case "off":
                Main.isChatEnabled = false;
                sender.sendMessage("Chat OFF.");

                break;
            case "clear":
                for (Player players : Bukkit.getOnlinePlayers()){
                    for (int i = 0; i < 100; i++) {
                        players.sendMessage(" ");
                    }
                }

                sender.sendMessage("Chat was cleared by " + sender.getName());
                break;
            default:
                sender.sendMessage("/chat [on/off/clear]");
        }

        return false;
    }
}
