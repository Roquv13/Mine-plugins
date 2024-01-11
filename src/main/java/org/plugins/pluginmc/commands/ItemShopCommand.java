package org.plugins.pluginmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.plugins.pluginmc.manager.ConfigManager;

public class ItemShopCommand implements CommandExecutor {
    public ItemShopCommand(ConfigManager configManager) {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}
