package org.plugins.pluginmc.api;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.plugins.pluginmc.Main;
import org.plugins.pluginmc.events.BlockBreak;
import org.plugins.pluginmc.objects.DropChance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DropApi {

    public void disableDrop(Player player, Material material) {
        List<Material> disabled = getDisabledDrops(player);
        List<Material> enabled = getEnabledDrops(player);

        disabled.add(material);
        enabled.remove(material);

        Main.getInstance().getConfig().set(player.getUniqueId() + ".disabled", convertMaterialList(disabled));
        Main.getInstance().getConfig().set(player.getUniqueId() + ".enabled", convertMaterialList(enabled));
        Main.getInstance().saveConfig();
    }
    public void enableDrop(Player player, Material material) {
        List<Material> disabled = getDisabledDrops(player);
        List<Material> enabled = getEnabledDrops(player);

        disabled.remove(material);
        enabled.add(material);

        Main.getInstance().getConfig().set(player.getUniqueId() + ".enabled", convertMaterialList(enabled));
        Main.getInstance().getConfig().set(player.getUniqueId() + ".enabled", convertMaterialList(enabled));
        Main.getInstance().saveConfig();
    }

    public List<Material> getDisabledDrops(Player player) {
        List<String> list = Main.getInstance().getConfig().getStringList(player.getUniqueId() + ".disabled");

        if (list != null) {
            return list
                    .stream()
                    .map(element -> Material.valueOf(element))
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }

    }
    public List<Material> getEnabledDrops(Player player) {
        List<String> list = Main.getInstance().getConfig().getStringList(player.getUniqueId() + ".enabled");

        if (list != null) {
            return list
                    .stream()
                    .map(element -> Material.valueOf(element))
                    .collect(Collectors.toList());
        } else {
            return getDefaultDrops();
        }
    }

    public List<Material> getDefaultDrops() {
        DropChance[] drops = BlockBreak.drops;
        List<Material> materials = new ArrayList<>();

        for (DropChance drop : drops) {
            materials.add(drop.getMaterial());
        }

        return materials;
    }

    public List<String> convertMaterialList(List<Material> list) {
        return list
                .stream()
                .map(element -> element.toString())
                .collect(Collectors.toList());
    }
}