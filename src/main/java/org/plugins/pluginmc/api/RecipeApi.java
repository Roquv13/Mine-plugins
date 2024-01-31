package org.plugins.pluginmc.api;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.plugins.pluginmc.utils.ItemBuilderUtil;

public class RecipeApi {

    public ItemStack getFarmer(Material mainMaterial, String name) {
        return new ItemBuilderUtil(mainMaterial, 1)
                .setName(ChatColor.LIGHT_PURPLE + name)
                .toItemStack();
    }
}
