package org.joutak.jouween.moon;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.joutak.jouween.config.JouWeenConfig;

public class MoonPlayerJoinEvent implements Listener {

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event) {
        if (Bukkit.getWorld(JouWeenConfig.getInstance().getWorldName()).getTime()>=11834||Bukkit.getWorld(JouWeenConfig.getInstance().getWorldName()).getTime()<=167){
            new MoonSwitcher().switchPlayerMoonPhase(event.getPlayer());
        }
    }

}
