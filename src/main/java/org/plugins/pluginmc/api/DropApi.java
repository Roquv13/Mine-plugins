package org.plugins.pluginmc.api;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.plugins.pluginmc.Main;
import org.plugins.pluginmc.events.BlockBreak;
import org.plugins.pluginmc.objects.DropChance;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DropApi {

    private static final String CONFIG_PATH = ".disabled";
    private static final String ENABLED_PATH = ".enabled";

    public void toggleDrop(Player player, Material material, boolean enable) {
        List<Material> disabled = getDisabledDrops(player);
        List<Material> enabled = getEnabledDrops(player);

        if (enable) {
            disabled.remove(material);
            enabled.add(material);
        } else {
            disabled.add(material);
            enabled.remove(material);
        }

        updateConfig(player, CONFIG_PATH, disabled);
        updateConfig(player, ENABLED_PATH,enabled);
    }

    public List<Material> getDisabledDrops(Player player) {
        return getConfigList(player, CONFIG_PATH);
    }

    public List<Material> getEnabledDrops(Player player) {
        return getConfigList(player, ENABLED_PATH, getDefaultDrops());
    }

    private List<Material> getConfigList(Player player, String path) {
        List<String> list = Main.getInstance().getConfig().getStringList(player.getUniqueId() + path);
        return (list != null) ? list.stream().map(Material::valueOf).collect(Collectors.toList()) : Collections.emptyList();
    }

    private List<Material> getConfigList(Player player, String path, List<Material> defaultValue) {
        List<Material> list = getConfigList(player, path);
        return (list.isEmpty()) ? defaultValue : list;
    }

    private void updateConfig(Player player, String path, List<Material> materials) {
        Main.getInstance().getConfig().set(player.getUniqueId() + path, convertMaterialList(materials));
        Main.getInstance().saveConfig();
    }

    public List<Material> getDefaultDrops() {
        return Arrays.stream(BlockBreak.drops).map(DropChance::getMaterial).collect(Collectors.toList());
    }

    public List<String> convertMaterialList(List<Material> list) {
        return list.stream().map(Enum::toString).collect(Collectors.toList());
    }
}
