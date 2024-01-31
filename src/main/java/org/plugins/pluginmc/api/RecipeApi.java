package org.plugins.pluginmc.api;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.plugins.pluginmc.utils.ItemBuilderUtil;

public class RecipeApi {

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
