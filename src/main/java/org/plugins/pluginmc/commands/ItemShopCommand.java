package org.plugins.pluginmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.plugins.pluginmc.gui.ItemShopGui;
import org.plugins.pluginmc.manager.ConfigManager;
import org.plugins.pluginmc.utils.ChatUtil;

import java.util.ArrayList;
import java.util.List;

public class ItemShopCommand implements CommandExecutor {

    private ItemShopGui isGui;
    private ConfigManager configManager;
    private List<String> itemsList;

    public ItemShopCommand(ConfigManager configManager, ItemShopGui isGui) {
        this.configManager = configManager;
        this.isGui = isGui;

        // Initialize the items list
        itemsList = new ArrayList<>();
        itemsList.add("gold");
        itemsList.add("diamond");
        itemsList.add("netherite");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (!(player.hasPermission("plugin-mc.itemshop"))) {
            player.sendMessage(ChatUtil.colorize(configManager.getPermissionError().replace("{PERM}", "roquv.itemshop")));
            return true;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("items")) {
                player.sendMessage("Available items in the item shop:");
                for (String item : itemsList) {
                    player.sendMessage(item);
                }
            }

            if (args[0] == null) {
                isGui.openGui(player);
            }

            return true;
        }

        if (args.length < 2) {
            player.sendMessage(ChatUtil.colorize(configManager.getCorrectUsage().replace("{USAGE}", "/itemshop <player> <service>")));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        String service = args[1];

        if (itemsList.contains(service.toLowerCase())) {
            if (target == null) {
                player.sendMessage("There is no player with this name.");
            } else {
                if (service.equalsIgnoreCase("gold")) {
                    target.sendMessage("You bought gold");
                    target.getInventory().addItem(new ItemStack(Material.GOLD_INGOT));
                }

                if (service.equalsIgnoreCase("diamond")) {
                    target.sendMessage("You bought diamond");
                    target.getInventory().addItem(new ItemStack(Material.DIAMOND));
                }

                if (service.equalsIgnoreCase("netherite")) {
                    target.sendMessage("You bought netherite");
                    target.getInventory().addItem(new ItemStack(Material.NETHERITE_INGOT));
                }
            }
        } else {
            player.sendMessage("There is no such item available. Try again.");
        }

        return true;
    }
}
