package org.plugins.pluginmc.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.plugins.pluginmc.Main;
import org.plugins.pluginmc.utils.ChatUtil;

import java.util.HashMap;
import java.util.Map;

public class Gui extends Item implements Listener {

    private final String guiName = ChatUtil.colorize(" &8> &aEFFECTS");

    public Map<Integer, ItemStack> createItems() {
        Map<Integer, ItemStack> items = new HashMap<>();

        ItemStack goldPickaxe = create(Material.GOLDEN_PICKAXE, "&e&lHASTE 1", "&8>> &7Price: &6&n10 coins&7.");
        ItemStack diamondPickaxe = create(Material.DIAMOND_PICKAXE, "&e&lHASTE 2", "&8>> &7Price: &6&n20 coins&7.");
        ItemStack goldenBoots = create(Material.GOLDEN_BOOTS, "&e&SPEED 1", "&8>> &7Price: &6&n10 coins&7.");
        ItemStack diamondBoots = create(Material.DIAMOND_BOOTS, "&e&lSPEED 2", "&8>> &7Price: &6&n20 coins&7.");
        ItemStack goldenSword = create(Material.GOLDEN_SWORD, "&e&STRENGTH 1", "&8>> &7Price: &6&n10 coins&7.");
        ItemStack diamondSword = create(Material.DIAMOND_SWORD, "&e&STRENGTH 2", "&8>> &7Price: &6&n20 coins&7.");
        ItemStack totem = create(Material.TOTEM_OF_UNDYING, "&e&REGENERATION", "&8>> &7Price: &6&n50 coins&7.");
        ItemStack potion = create(Material.POTION, "&e&lNIGHT VISION", "&8>> &7Price: &6&n50 coins&7.");
        ItemStack enchantedGoldenApple = create(Material.ENCHANTED_GOLDEN_APPLE, "&e&lHEALTH BOOST", "&8>> &7Price: &6&n80 coins&7.");

        items.put(1, goldPickaxe);
        items.put(2, diamondPickaxe);
        items.put(3, goldenBoots);
        items.put(4, diamondBoots);
        items.put(5, goldenSword);
        items.put(6, diamondSword);
        items.put(7, totem);
        items.put(8, potion);
        items.put(9, enchantedGoldenApple);

        return items;
    }

    public void openGui(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, guiName);
        Map<Integer, ItemStack> items = createItems();

        for (Map.Entry<Integer, ItemStack> entry : items.entrySet()) {
            gui.setItem(entry.getKey(), entry.getValue());
        }

        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Map<Integer, ItemStack> items = createItems();

        if (event.getView().getTitle().equals(guiName)) {
            event.setCancelled(true); // Prevent item moving or swapping

            if (event.getCurrentItem() != null) {
                // Check which item was clicked
                for (ItemStack item : items.values()) {
                    if (event.getCurrentItem().getType() == item.getType()) {
                        Player player = (Player) event.getWhoClicked();

                        if (item.getType() == Material.GOLDEN_PICKAXE) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 600, 1));
                            player.sendMessage(ChatUtil.colorize("&aYou have gained HASTE 1!"));
                        } else if (item.getType() == Material.DIAMOND_PICKAXE) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 600, 2));
                            player.sendMessage(ChatUtil.colorize("&aYou have gained HASTE 2!"));
                        } else if (item.getType() == Material.GOLDEN_BOOTS) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 1));
                            player.sendMessage(ChatUtil.colorize("&aYou have gained SPEED 1!"));
                        }  else if (item.getType() == Material.DIAMOND_BOOTS) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 2));
                            player.sendMessage(ChatUtil.colorize("&aYou have gained SPEED 2!"));
                        } else if (item.getType() == Material.GOLDEN_SWORD) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 1));
                            player.sendMessage(ChatUtil.colorize("&aYou have gained STRENGTH 1!"));
                        } else if (item.getType() == Material.DIAMOND_SWORD) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 2));
                            player.sendMessage(ChatUtil.colorize("&aYou have gained STRENGTH 2!"));
                        } else if (item.getType() == Material.TOTEM_OF_UNDYING) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 2));
                            player.sendMessage(ChatUtil.colorize("&aYou have gained REGENERATION!"));
                        } else if (item.getType() == Material.POTION) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 600, 2));
                            player.sendMessage(ChatUtil.colorize("&aYou have gained NIGHT VISION!"));
                        } else if (item.getType() == Material.ENCHANTED_GOLDEN_APPLE) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 600, 2));
                            player.sendMessage(ChatUtil.colorize("&aYou have gained HEALTH BOOST!"));
                        }
                    }
                }
            }
        }
    }
}
