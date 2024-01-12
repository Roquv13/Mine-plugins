package org.plugins.pluginmc.commands.ItemShop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.plugins.pluginmc.manager.ConfigManager;
import org.plugins.pluginmc.utils.ChatUtil;

import java.util.ArrayList;
import java.util.List;

public class ItemShopCommand implements CommandExecutor {

    private ConfigManager configManager;

    public ItemShopCommand(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("plugin-mc.itemshop")) {
            sender.sendMessage(ChatUtil.colorize(configManager.getPermissionError().replace("{PERM}", "roquv.itemshop")));
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(ChatUtil.colorize(configManager.getCorrectUsage().replace("{USAGE}", "/itemshop <player> <service>")));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        String service = args[1];

        List<String> items = new ArrayList<>();
        items.add("diamond");
        items.add("gold");

        if (items.contains(service.toLowerCase())) {
            if (target == null){
                sender.sendMessage("There is no player with this name.");
            } else {
                if (service.equalsIgnoreCase("diamond")) {
                    target.sendMessage("You bought diamond");
                    target.getInventory().addItem(new ItemStack(Material.DIAMOND));
                }

                if (service.equalsIgnoreCase("gold")) {
                    target.sendMessage("You bought gold");
                    target.getInventory().addItem(new ItemStack(Material.GOLD_INGOT));
                }
            }
        } else {
            sender.sendMessage("There is no such item available. Try again.");
        }

        return false;
    }
}
