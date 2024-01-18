package org.plugins.pluginmc.api;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.plugins.pluginmc.Main;

import java.util.List;
import java.util.stream.Collectors;

public class DropApi {

    public List<Material> getDisabledDrops(Player player) {
        return Main.getInstance().getConfig().getStringList(player.getUniqueId() + ".disabled")
                .stream()
                .map(element -> Material.valueOf(element))
                .collect(Collectors.toList());
    }
    public List<Material> getEnabledDrops(Player player) {
        return Main.getInstance().getConfig().getStringList(player.getUniqueId() + ".enabled")
                .stream()
                .map(element -> Material.valueOf(element))
                .collect(Collectors.toList());
    }
}
