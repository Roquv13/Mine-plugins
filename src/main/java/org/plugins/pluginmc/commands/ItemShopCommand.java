package org.plugins.pluginmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.plugins.pluginmc.manager.ConfigManager;
import org.plugins.pluginmc.utils.ChatUtil;

public class ItemShopCommand implements CommandExecutor {

    private ConfigManager configManager;

    public ItemShopCommand(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("roquv.itemshop")) {
            sender.sendMessage(ChatUtil.colorize(configManager.getPermissionError().replace("{PERM}", "roquv.itemshop")));
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(ChatUtil.colorize(configManager.getCorrectUsage().replace("{USAGE}", "/is <player> <service>")));
        }

        Player target = Bukkit.getPlayer(args[0]);
        String service = args[1];

        if (service.equalsIgnoreCase("diamond")) {
            target.sendMessage("You bought diamond");
            target.getInventory().addItem(new ItemStack(Material.DIAMOND));
        }

        if (service.equalsIgnoreCase("gold")) {
            target.sendMessage("You bought gold");
            target.getInventory().addItem(new ItemStack(Material.GOLD_INGOT));
        }

        return false;
    }
}
