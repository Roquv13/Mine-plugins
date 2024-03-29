package org.plugins.pluginmc.api;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.plugins.pluginmc.Main;

import java.util.ArrayList;
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

    public int getHomesCount(Player player) {
        return main.getConfig().getStringList(player.getUniqueId().toString()).size();
    }

    public List<String> convertHomesMap(Map<String, Location> map) {
        List<String> list = new ArrayList<>();

        for (Map.Entry<String, Location> me : map.entrySet()) {
            Location location = me.getValue();
            list.add(me.getKey() + ":" + location.getWorld().getName() + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ());
        }

        return list;
    }

    public List<String> homeList(Map<String, Location> map) {
        List<String> homeNames = new ArrayList<>();

        for (Map.Entry<String, Location> me : map.entrySet()) {
            String homeName = me.getKey();
            homeNames.add(homeName);
        }

        return homeNames;
    }

    public boolean addHome(Player player, String homeName, Location location) {
        Map<String, Location> homes = getHomes(player);

        if (homes.containsKey(homeName)) return false;

        homes.put(homeName, location);

        main.getConfig().set(player.getUniqueId().toString(), convertHomesMap(homes));
        main.saveConfig();
        return true;
    }

    public boolean removeHome(Player player, String homeName) {
        Map<String, Location> homes = getHomes(player);

        if (!homes.containsKey(homeName)) return false;

        homes.remove(homeName);

        main.getConfig().set(player.getUniqueId().toString(), convertHomesMap(homes));
        main.saveConfig();
        return true;
    }

    public boolean homeExists(Player player, String homeName) {
        return getHomes(player).containsKey(homeName);
    }

    public boolean teleportHome(Player player, String homeName) {
        Map<String, Location> homes = getHomes(player);

        if (!homes.containsKey(homeName)) return false;

        Location location = homes.get(homeName);

        player.teleport(location);
        return true;
    }
}
