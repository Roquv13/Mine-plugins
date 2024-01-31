package org.plugins.pluginmc.api;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.plugins.pluginmc.utils.ItemBuilderUtil;

public class RecipeApi {

    public ShapedRecipe getRecipe(Material mainMaterial, NamespacedKey key, ItemStack result) {
        ShapedRecipe recipe = new ShapedRecipe(key, result);

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
