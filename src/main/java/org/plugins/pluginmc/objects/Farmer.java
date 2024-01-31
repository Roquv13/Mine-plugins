package org.plugins.pluginmc.objects;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Farmer {

    private ItemStack farmerItem;
    private Material holeGenerateType;

    public Farmer(ItemStack farmerItem, Material holeGenerateType) {
        this.farmerItem = farmerItem;
        this.holeGenerateType = holeGenerateType;
    }

}
