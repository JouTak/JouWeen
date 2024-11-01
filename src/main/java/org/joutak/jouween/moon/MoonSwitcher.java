package org.joutak.jouween.moon;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.joutak.jouween.JouWeen;
import org.joutak.jouween.config.JouWeenConfig;
import org.joutak.jouween.jack.data.JackData;

public class MoonSwitcher {

    public void register() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(JouWeen.getInstance(), () -> {
            checkNightTime();
            checkMidnight();
        }, 0, 20);
    }

    private void checkNightTime() {
        if (!(Bukkit.getWorld(JouWeenConfig.getInstance().worldName).getTime() >= 11834 && Bukkit.getWorld(JouWeenConfig.getInstance().worldName).getTime() <= 11854)) {
            return;
        }
        Bukkit.getOnlinePlayers().stream()
                .forEach(this::switchPlayerMoonPhase);
    }

    private void checkMidnight() {
        if (!(Bukkit.getWorld(JouWeenConfig.getInstance().worldName).getTime() >= 18000 && Bukkit.getWorld(JouWeenConfig.getInstance().worldName).getTime() <= 18020)) {
            return;
        }

        JackData.getInstance().switchPlayersCanTakeQuests();
    }

    public void switchPlayerMoonPhase(Player player) {

        long currMoonPhase = ((Bukkit.getWorld(JouWeenConfig.getInstance().worldName).getFullTime() / 24000) % 8);
//        System.out.println(player.getPlayerTime() % 192000 + 24000 * (7 + JouWeenConfig.getInstance().moonPhase - currMoonPhase) - 12000);
//        System.out.println((player.getPlayerTime() + 24000 * (8 + JouWeenConfig.getInstance().moonPhase - currMoonPhase)) % 24000);
//        System.out.println("offsetFormula: " + 24000 * (8 + JouWeenConfig.getInstance().moonPhase - currMoonPhase));
        player.setPlayerTime(24000 * (8 + JouWeenConfig.getInstance().moonPhase - currMoonPhase), true);
//        System.out.println("offsetTrue: " + player.getPlayerTimeOffset());

    }

}
