package org.plugins.pluginmc;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.plugins.pluginmc.events.BlockBreak;
import org.plugins.pluginmc.commands.*;
import org.plugins.pluginmc.events.AsyncPlayerChat;
import org.plugins.pluginmc.events.PlayerJoin;
import org.plugins.pluginmc.gui.DropGui;
import org.plugins.pluginmc.gui.EffectsGui;
import org.plugins.pluginmc.gui.ItemShopGui;
import org.plugins.pluginmc.manager.ConfigManager;

import java.util.ArrayList;

public final class Main extends JavaPlugin implements Listener {

    public static ConfigManager configManager;

    private static Main instance;

    private final String name = getDescription().getName();

    public static Boolean isChatEnabled = true;

    public static ArrayList<Player> invisiblePlayers = new ArrayList<>();

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        //Console information
        this.getLogger().info(String.format("Plugin \"%s\" is starting...", name));

        //Register events
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        getServer().getPluginManager().registerEvents(new AsyncPlayerChat(), this);
        configManager = new ConfigManager(getConfig());

        //Commands
        getCommand("help").setExecutor(new HelpCommand());
        ItemShopGui isGui = new ItemShopGui();
        getCommand("itemshop").setExecutor(new ItemShopCommand(configManager, isGui));
        EffectsGui effectsGui = new EffectsGui();
        getCommand("effects").setExecutor(new EffectsCommand(effectsGui));
        getCommand("chat").setExecutor(new ChatCommand());
        getCommand("vanish").setExecutor(new VanishComand());
        // Add new Gui
        DropGui dropGui = new DropGui();
        getCommand("drop").setExecutor(new DropCommand(dropGui));

        initConfig();
    }

    @Override
    public void onDisable() {
        this.getLogger().info(String.format("Plugin \"%s\" stopped.", name));
        saveConfig();
    }

    private void initConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }
}
