package org.plugins.pluginmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.plugins.pluginmc.gui.Gui;

public class EffectsCommand implements CommandExecutor {

    private Gui gui;

    public EffectsCommand(Gui gui) {
        this.gui = gui;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            gui.openGui(player);
        } else {
            sender.sendMessage("Only for players");
        }

        return true;
    }
}
