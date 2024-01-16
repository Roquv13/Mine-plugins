package org.plugins.pluginmc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.plugins.pluginmc.alerts.BlockBreak;
import org.plugins.pluginmc.commands.EffectsCommand;
import org.plugins.pluginmc.commands.HelpCommand;
import org.plugins.pluginmc.commands.ItemShopCommand;
import org.plugins.pluginmc.gui.EffectsGui;
import org.plugins.pluginmc.gui.ItemShopGui;
import org.plugins.pluginmc.manager.ConfigManager;
import org.plugins.pluginmc.utils.ChatUtil;

public final class Main extends JavaPlugin implements Listener {

    private static Main instance;

    private String name = getDescription().getName();

    private ConfigManager configManager;

    private EffectsGui effectsGui;

    private ItemShopGui isGui;

    public static Boolean isChatEnabled = true;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        //Console information
        this.getLogger().info(String.format("Plugin \"%s\" is starting...", name));

        //Register events
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        configManager = new ConfigManager(getConfig());

        //Commands
        getCommand("help").setExecutor(new HelpCommand());
        isGui = new ItemShopGui();
        getCommand("itemshop").setExecutor(new ItemShopCommand(configManager, isGui));
        effectsGui = new EffectsGui();
        getCommand("effects").setExecutor(new EffectsCommand(effectsGui));

        initConfig();
    }

    @Override
    public void onDisable() {
        this.getLogger().info(String.format("Plugin \"%s\" stopped.", name));
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
