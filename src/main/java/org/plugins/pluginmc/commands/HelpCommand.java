package org.plugins.pluginmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.plugins.pluginmc.utils.ChatUtil;

public class HelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            showDefaultHelp(sender);
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("itemshop")) {
                showItemShopHelp(sender);
            }

            if (args[0].equalsIgnoreCase("effects")) {
                showEffectsHelp(sender);
            }
        }
        return false;
    }

    private void showDefaultHelp(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            player.sendMessage(ChatUtil.colorize("&7/help itemshop - informations about item shop"));
            player.sendMessage(ChatUtil.colorize("&8/help effects - informations about Effects"));
            player.sendMessage(ChatUtil.colorize("&b/help <soon> - informations about nothing"));
        } else {
            System.out.println("Only for players!");
        }
    }

    private void showItemShopHelp(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            player.sendMessage("/itemshop items - Display items in item shop");
            player.sendMessage("/itemshop <player> <item> - Buy an item from item shop");
        } else {
            System.out.println("Only for players");
        }
    }

    private void showEffectsHelp(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            player.sendMessage("/effects <player> - Open Gui with effects that can be applied to player");
        }
    }
}
