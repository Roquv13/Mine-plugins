package org.plugins.pluginmc.commands.ItemShop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpItemShop implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] strings) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            player.sendMessage("/itemshop items - Display items in item shop");
            player.sendMessage("/itemshop <item> - Buy an item from item shop");
        } else {
            System.out.println("Only for players");
        }

        return false;
    }
}
