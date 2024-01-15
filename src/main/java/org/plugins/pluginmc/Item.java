package org.plugins.pluginmc;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.plugins.pluginmc.utils.ChatUtil;

import java.util.Collections;

public class Item {

    public ItemStack create(Material material, String name, String lore) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatUtil.colorize(name));
        itemMeta.setLore(Collections.singletonList(ChatUtil.colorize(lore)));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
