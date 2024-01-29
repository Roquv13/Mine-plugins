package org.plugins.pluginmc.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class HologramCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            Location playerLocation = player.getLocation().add(0, 2, 0);

            createHologram(playerLocation, ChatColor.GREEN + "Box");

            Location playerLocation2 = playerLocation.clone().add(1, 1, 0);

            createHologram(playerLocation2, ChatColor.AQUA + "TEXT HOLO");
        } else {
            sender.sendMessage("Only for players!");
        }

        return true;
    }

    public void createHologram(Location location, String text) {
        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setCustomName(text);
    }
}
