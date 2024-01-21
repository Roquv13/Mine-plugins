package org.plugins.pluginmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.plugins.pluginmc.objects.Item;
import org.plugins.pluginmc.gui.ItemShopGui;
import org.plugins.pluginmc.manager.ConfigManager;
import org.plugins.pluginmc.utils.ChatUtil;

import java.util.Map;

public class ItemShopCommand extends Item implements CommandExecutor {

    private ItemShopGui isGui;
    private ConfigManager configManager;
    private Map<String, Material> materialMap;

    public ItemShopCommand(ConfigManager configManager, ItemShopGui isGui) {
        this.configManager = configManager;
        this.isGui = isGui;

        materialMap = materialsMap();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (!(player.hasPermission("plugin-mc.itemshop"))) {
            player.sendMessage(ChatUtil.colorize(configManager.getPermissionError().replace("{PERM}", "roquv.itemshop")));
            return true;
        }

        if (args.length == 0) {
            isGui.openGui(player);
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("items")) {
            player.sendMessage("Available items in the item shop:");
            for (String item : materialMap.keySet()) {
                player.sendMessage(item);
            }

            return true;
        }

        if (args.length < 2 || args.length > 3) {
            player.sendMessage(ChatUtil.colorize(configManager.getCorrectUsage().replace("{USAGE}", "/itemshop <player> <item>")));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        //Add check if service letters is upper case
        String service;
        if (args.length == 2) {
            service = args[1].substring(0, 1).toUpperCase() + args[1].substring(1).toLowerCase();
        } else {
            service = args[1].substring(0, 1).toUpperCase() + args[1].substring(1).toLowerCase()
                    + " " + args[2].substring(0, 1).toUpperCase() + args[2].substring(1).toLowerCase();
        }


        if (materialMap.containsKey(service)) {
            if (target == null) {
                player.sendMessage("There is no player with this name.");
            } else {
                target.sendMessage("You bought " + service);
                target.getInventory().addItem(new ItemStack(materialMap.get(service)));
            }
            player.sendMessage(service);
        } else {
            player.sendMessage("There is no such item available. Try again.");
        }

        return true;
    }
}
