package org.plugins.pluginmc;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.plugins.pluginmc.commands.HelpCommand;
import org.plugins.pluginmc.commands.ItemShopCommand;
import org.plugins.pluginmc.manager.ConfigManager;
import org.plugins.pluginmc.utils.ChatUtil;

public final class Main extends JavaPlugin implements Listener {

    private ConfigManager configManager;
    @Override
    public void onEnable() {
        getLogger().info(ChatColor.GREEN + "Plugin > started");
        getServer().getPluginManager().registerEvents(this, this);
        configManager = new ConfigManager(getConfig());
        getCommand("help").setExecutor(new HelpCommand());
        getCommand("is").setExecutor(new ItemShopCommand(configManager));
        initConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.GREEN + "Plugin > stopped");
        saveConfig();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatUtil.colorize(configManager.getJoinMessage().replace("{PLAYER}", player.getName())));
    }

    private void initConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }
}
