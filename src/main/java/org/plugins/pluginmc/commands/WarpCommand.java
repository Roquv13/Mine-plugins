package org.plugins.pluginmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.plugins.pluginmc.gui.WarpGui;

public class WarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
       if (sender instanceof ConsoleCommandSender) return true;

       Player player = (Player) sender;

       if (args.length == 0) {
           player.openInventory(WarpGui.getInventory(player));
       } else if (args.length == 1) {
           if (args[0].equalsIgnoreCase("list")) {

           }
       } else if (args.length == 2) {
           if (args[0].equalsIgnoreCase("add")) {

           } else if (args[0].equalsIgnoreCase("del")) {

           }
       } else {
           player.sendMessage("This argument doesn't exists. Try");
       }

       return false;
    }
}
