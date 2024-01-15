package org.plugins.pluginmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.plugins.pluginmc.gui.EffectsGui;

public class EffectsCommand implements CommandExecutor {

    private EffectsGui effectsGui;

    public EffectsCommand(EffectsGui effectsGui) {
        this.effectsGui = effectsGui;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            effectsGui.openGui(player);
        } else {
            sender.sendMessage("Only for players");
        }

        return true;
    }
}
