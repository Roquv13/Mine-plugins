package org.plugins.pluginmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.plugins.pluginmc.gui.DropGui;

public class DropCommand implements CommandExecutor {

    private DropGui dropGui;

    public DropCommand(DropGui dropGui) { this.dropGui = dropGui; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.openInventory(dropGui.getInventory(player));
        } else {
            sender.sendMessage("Only for players");
        }

        return true;
    }
}
