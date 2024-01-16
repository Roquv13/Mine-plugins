package org.plugins.pluginmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!sender.isOp()) return true;

        if (args.length != 1) return true;

        switch (args[0]) {
            case "on":
                break;
            case "off":
                break;
            case "clear":
                break;
            default:
                sender.sendMessage("/chat [on/off/clear]");
        }

        return false;
    }
}
