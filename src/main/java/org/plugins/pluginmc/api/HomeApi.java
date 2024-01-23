package org.plugins.pluginmc.api;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.plugins.pluginmc.Main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeApi {

    Main main = Main.getInstance();

    public Map<String, Location> getHomes(Player player) {

        // 'home_name:world:x:y:z'
        List<String> rawHomes = main.getConfig().getStringList(player.getUniqueId().toString());
        Map<String, Location> map = new HashMap<>();

        for (String raw: rawHomes) {
            String[] splitted = raw.split(":");

            map.put(
                splitted[0],
                new Location(
                    Bukkit.getWorld(splitted[1]),
                    Double.parseDouble(splitted[2]),
                    Double.parseDouble(splitted[3]),
                    Double.parseDouble(splitted[4])
                )
            );
        }

        return map;
    }
}
