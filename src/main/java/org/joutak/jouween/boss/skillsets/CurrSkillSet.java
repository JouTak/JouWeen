package org.joutak.jouween.boss.skillsets;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.joutak.jouween.Utils;
import org.joutak.jouween.boss.JackBoss;

import java.util.ArrayList;

public interface CurrSkillSet {

    void targetSkill();

    void radiusSkill();

    void summonSkill();

    CurrSkillSet shiftForward();

    CurrSkillSet shiftBackwards();

    int getCurrStageNumb();

    default boolean isPlayerNear(Player p) {
        return Utils.getDistance(p.getLocation(), JackBoss.getInstance().getJackBossWitherSkeleton().getLocation()) < 25;
    }

    default ArrayList<Player> getAllNearPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()
        ) {
            if (isPlayerNear(p)) {
                players.add(p);
            }
        }
        return players;
    }

}
