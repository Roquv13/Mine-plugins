package org.plugins.pluginmc.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreboardUpdater {

    public static void createScoreboard(Player player) {
        ScoreboardManager manager = (ScoreboardManager) Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("Stats", Criteria.DUMMY, "Scoreboard");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        player.setScoreboard(scoreboard);
    }
}
