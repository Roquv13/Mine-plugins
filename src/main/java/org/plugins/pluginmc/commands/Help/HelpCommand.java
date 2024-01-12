package org.plugins.pluginmc.commands.Help;

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
            player.sendMessage(ChatUtil.colorize("&7/help itemshop - informations about item shop"));
            player.sendMessage(ChatUtil.colorize("&8/help effects - informations about Effects"));
            player.sendMessage(ChatUtil.colorize("&b/help <soon> - informations about nothing"));
        } else {
            System.out.println("Only for players!");
        }
        return false;
    }
}
