package org.plugins.pluginmc.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import org.plugins.pluginmc.events.PlayerJoin;

public class ScoreboardUpdater extends BukkitRunnable {

    private final PlayerJoin playerJoin;

    public ScoreboardUpdater(PlayerJoin playerJoin) {
        this.playerJoin = playerJoin;
    }

    @Override
    public void run() {
        updateScoreboard();
    }

    public static void createScoreboard(Player player) {
        ScoreboardManager manager = (ScoreboardManager) Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective dummyObj = scoreboard.registerNewObjective("Stats", Criteria.DUMMY, "Scoreboard");

        dummyObj.setDisplaySlot(DisplaySlot.SIDEBAR);

        setScore(5, dummyObj, ChatColor.GOLD + "Edition: " + ChatColor.AQUA + "Beta");
        setScore(4, dummyObj, " ");
        setScore(3, dummyObj, ChatColor.GOLD + "Online: " + ChatColor.GREEN + Bukkit.getOnlinePlayers().size());
        setScore(2, dummyObj, " ");
        setScore(1, dummyObj,  ChatColor.GOLD + "IP: " + ChatColor.RED + "Server.com");

        player.setScoreboard(scoreboard);

    }

    public static void setScore(int scoreValue, Objective objective, String text) {
        Score score = objective.getScore(text);
        score.setScore(scoreValue);
    }

    public static void updateScoreboard(){
        for (Player players : Bukkit.getOnlinePlayers()) {
            createScoreboard(players);
        }
    }
}
