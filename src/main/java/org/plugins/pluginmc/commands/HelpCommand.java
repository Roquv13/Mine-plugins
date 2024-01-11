package org.plugins.pluginmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.plugins.pluginmc.utils.ChatUtil;

public class HelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] strings) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            player.sendMessage(ChatUtil.colorize("&7/rank - informations about ranks"));
            player.sendMessage(ChatUtil.colorize("&8/guild - informations about guilds"));
            player.sendMessage(ChatUtil.colorize("&b/tnt - informations about tnt"));
        } else {
            System.out.println("Only for players!");
        }
        return false;
    }
}
