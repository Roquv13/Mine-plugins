package org.plugins.pluginmc.api;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.plugins.pluginmc.objects.Farmer;
import org.plugins.pluginmc.utils.ItemBuilderUtil;

import java.util.Arrays;
import java.util.List;

public class RecipeApi {

    public final List<Farmer> farmers = Arrays.asList(
            new Farmer(getFarmer(Material.OBSIDIAN, "Boy Farmer"), Material.OBSIDIAN),
            new Farmer(getFarmer(Material.SAND, "Sand Farmer"), Material.SAND),
            new Farmer(getFarmer(Material.AIR, "Moat digger"), Material.AIR)
    );

    //NamespacedKey key = new NamespacedKey(plugin, "my_recipe");
    public ShapedRecipe getRecipe(Material mainMaterial, NamespacedKey key, ItemStack result) {
        ShapedRecipe recipe = new ShapedRecipe(key, result);
        recipe.shape("ddd", "dcd", "ddd");

        recipe.setIngredient('d', Material.DIAMOND_PICKAXE);
        recipe.setIngredient('c', mainMaterial);

        return recipe;
    }

    public ItemStack getFarmer(Material mainMaterial, String name) {
        return new ItemBuilderUtil(mainMaterial, 1)
                .setName(ChatColor.LIGHT_PURPLE + name)
                .toItemStack();
    }

    public void generateHole(Location location, Material material) {
        for (int i = (int) location.getY(); i > 0; i--) {
            new Location(location.getWorld(), location.getX(), i, location.getZ()).getBlock().setType(material);
        }
    }

}
