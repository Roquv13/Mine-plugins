package org.plugins.pluginmc;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.plugins.pluginmc.api.RecipeApi;
import org.plugins.pluginmc.automessage.AutoMessageSender;
import org.plugins.pluginmc.commands.*;
import org.plugins.pluginmc.events.*;
import org.plugins.pluginmc.gui.DropGui;
import org.plugins.pluginmc.gui.EffectsGui;
import org.plugins.pluginmc.gui.ItemShopGui;
import org.plugins.pluginmc.manager.ConfigManager;
import org.plugins.pluginmc.objects.Farmer;
import org.plugins.pluginmc.objects.Item;
import org.plugins.pluginmc.scoreboard.ScoreboardUpdater;
import org.plugins.pluginmc.tablist.TabListUpdater;
import org.plugins.pluginmc.utils.ItemBuilderUtil;

import java.util.ArrayList;

public final class Main extends JavaPlugin implements Listener {

    public static ConfigManager configManager;

    private static Main instance;

    private final String name = getDescription().getName();

    public static Boolean isChatEnabled = true;

    public static ArrayList<Player> invisiblePlayers = new ArrayList<>();

    RecipeApi recipeApi = new RecipeApi();

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        PlayerJoin playerJoin = new PlayerJoin();

        instance = this;

    //Stone generator recipe register
        ItemStack stoneGenerator = new ItemBuilderUtil(Material.END_STONE, 1)
                .setName(ChatColor.GREEN + "Stone Generator")
                .setLore(ChatColor.GRAY + "Place this item on ground")
                .toItemStack();

        ShapedRecipe stoneGeneratorRecipe = new ShapedRecipe(stoneGenerator);
        stoneGeneratorRecipe.shape("sss", "sds", "sss");

        stoneGeneratorRecipe.setIngredient('s', Material.STONE);
        stoneGeneratorRecipe.setIngredient('d', Material.DIAMOND_PICKAXE);

    //Farmers recipes register
        for (Farmer farmer : recipeApi.farmers) {
            ItemStack item = farmer.getFarmerItem();
            NamespacedKey key = new NamespacedKey(this, item.getType().name());
            getServer().addRecipe(recipeApi.getRecipe(item.getType(), key, item));
        }

    //Console information
        this.getLogger().info(String.format("Plugin \"%s\" is starting...", name));

    //Register events
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(playerJoin, this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(playerJoin), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        getServer().getPluginManager().registerEvents(new AsyncPlayerChat(), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        getServer().getPluginManager().registerEvents(new BlockPlace(), this);
        getServer().getPluginManager().registerEvents(new ServerListPing(), this);
        configManager = new ConfigManager(getConfig());

    //Commands
        // Help
        getCommand("help").setExecutor(new HelpCommand());

        // ItemShop
        ItemShopGui isGui = new ItemShopGui();
        getCommand("itemshop").setExecutor(new ItemShopCommand(configManager, isGui));

        // Effects
        EffectsGui effectsGui = new EffectsGui();
        getCommand("effects").setExecutor(new EffectsCommand(effectsGui));

        // Chat
        getCommand("chat").setExecutor(new ChatCommand());

        // Vanish
        getCommand("vanish").setExecutor(new VanishComand());

        // Drop
        DropGui dropGui = new DropGui();
        getCommand("drop").setExecutor(new DropCommand(dropGui));

        // Warp
        getCommand("warp").setExecutor(new WarpCommand());

        // Home
        getCommand("home").setExecutor(new HomeCommand(configManager));
        getCommand("sethome").setExecutor(new SetHomeCommand());
        getCommand("delhome").setExecutor(new RemoveHomeCommand());

        // Gamemode
        getCommand("gm").setExecutor(new GameModeCommand());

        // Holograms
        getCommand("hd").setExecutor(new HologramCommand());

    // Recipes
        // Stone generator
        getServer().addRecipe(stoneGeneratorRecipe);

    // Player time
        new TabListUpdater(playerJoin).runTaskTimer(this, 0, 20);

    // Scoreboard
        new ScoreboardUpdater(playerJoin).runTaskTimer(this, 0, 20 * 3);

    // Auto message
        new AutoMessageSender(this, "autoMessages.yml").runTaskTimer(this, 0, 20 * 60);

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
