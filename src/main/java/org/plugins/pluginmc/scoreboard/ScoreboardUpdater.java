package org.plugins.pluginmc.scoreboard;

import org.bukkit.Bukkit;
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
        Objective objective = scoreboard.registerNewObjective("Stats", Criteria.DUMMY, "Scoreboard");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        setScore(3, objective, "Online: " + Bukkit.getOnlinePlayers().size());
        setScore(2, objective, " ");
        setScore(1, objective, "IP: Server.com");

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
