package org.plugins.pluginmc.objects;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.plugins.pluginmc.utils.ChatUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Item {

    public ItemStack create(Material material, String name, String lore) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatUtil.colorize(name));
        itemMeta.setLore(Collections.singletonList(ChatUtil.colorize(lore)));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public Map<String ,Material> materialsMap() {
        Map<String, Material> materials = new HashMap<>();

        Material goldIngot = Material.GOLD_INGOT;
        Material diamond = Material.DIAMOND;
        Material netherIngot = Material.NETHERITE_INGOT;
        Material diamondSword = Material.DIAMOND_SWORD;
        Material diamondPickaxe = Material.DIAMOND_PICKAXE;
        Material upgradeTemplate = Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE;

        materials.put("Gold Ingot" , goldIngot);
        materials.put("Diamond" , diamond);
        materials.put("Nether Ingot" , netherIngot);
        materials.put("Diamond Sword" , diamondSword);
        materials.put("Diamond Pickaxe" , diamondPickaxe);
        materials.put("Upgrade Template" , upgradeTemplate);

        return materials;
    }

    public Map<Integer, ItemStack> itemsMap() {
        Map<Integer, ItemStack> items = new HashMap<>();

        ItemStack goldIngot = create(Material.GOLD_INGOT, "&e&lGold Ingot", "&cPrice: &l&a5 &l&6coins ");
        ItemStack diamond = create(Material.DIAMOND, "&b&lDiamond", "&cPrice: &l&a20 &l&6coins ");
        ItemStack netherIngot = create(Material.NETHERITE_INGOT, "&8&lNetherite", "&cPrice: &l&a40 &l&6coins ");
        ItemStack diamondSword = create(Material.DIAMOND_SWORD, "&b&lDiamond Sword", "&cPrice: &l&a35 &l&6coins ");
        ItemStack diamondPickaxe = create(Material.DIAMOND_PICKAXE, "&b&lDiamond Pickaxe", "&cPrice: &l&a55 &l&6coins ");
        ItemStack upgradeTemplate = create(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE, "&b&lUpgrade Template", "&cPrice: &l&a80 &l&6coins ");

        items.put(0, goldIngot);
        items.put(1, diamond);
        items.put(2, netherIngot);
        items.put(3, diamondSword);
        items.put(4, diamondPickaxe);
        items.put(5, upgradeTemplate);

        return items;
    }

}
