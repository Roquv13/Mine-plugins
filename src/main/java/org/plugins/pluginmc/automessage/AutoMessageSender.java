package org.plugins.pluginmc.automessage;

import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class AutoMessageSender extends BukkitRunnable {

    final List<String> messages = Arrays.asList(
            ChatColor.GRAY + "Discord server: " + ChatColor.AQUA + "XXXX",
            ChatColor.GRAY + "Server site: " + ChatColor.AQUA + "XXXX",
            ChatColor.GRAY + "Auto message: " + ChatColor.AQUA + "message"
    );

    final String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "Info" + ChatColor.DARK_GRAY + "] ";

    @Override
    public void run() {

    }
}
