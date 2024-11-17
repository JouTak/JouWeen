package org.joutak.jouween.boss.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.joutak.jouween.Utils;
import org.joutak.jouween.boss.JackBoss;
import org.joutak.jouween.boss.JackBossData;
import org.joutak.jouween.config.JouWeenConfig;

public class JackBossEvents implements Listener {

    @EventHandler
    public void playerNetherPortalEvent(PlayerTeleportEvent event){
        if (!event.getCause().equals(PlayerTeleportEvent.TeleportCause.NETHER_PORTAL)){
            return;
        }

        Player player = event.getPlayer();

        if (Utils.getDistance(player.getLocation(), Utils.getLocation(Bukkit.getWorld(JouWeenConfig.getInstance().getWorldName()), JackBossData.getInstance().getPortalLocation()))>25){
            return;
        }

        event.setTo(Utils.getLocation(Bukkit.getWorld(JackBossData.getInstance().getBossWorldName()), JackBossData.getInstance().getEndLocation()));
    }

    @EventHandler
    public void playerRespawnEvent(PlayerRespawnEvent event){
        if (!JackBossData.getInstance().isBossStarted()){
            return;
        }

        if (!event.getRespawnReason().equals(PlayerRespawnEvent.RespawnReason.DEATH)){
            return;
        }

        event.setRespawnLocation(Utils.getLocation(Bukkit.getWorld(JouWeenConfig.getInstance().getWorldName()), JackBossData.getInstance().getPortalLocation()));
    }

    @EventHandler
    public void bossDamageEvent(EntityDamageByEntityEvent event){

        if (event.getEntity().getType().equals(EntityType.PLAYER)){
            return;
        }

        if (!event.getEntity().equals(JackBoss.getInstance().getJackBossWitherSkeleton())){
            return;
        }

        JackBoss.getInstance().damageBoss();
    }

    @EventHandler
    public void bossDeathEvent(EntityDeathEvent event){
        if (event.getEntity().getType().equals(EntityType.PLAYER)){
            return;
        }

        if (!event.getEntity().equals(JackBoss.getInstance().getJackBossWitherSkeleton())){
            return;
        }

        JackBoss.getInstance().onDeath();
    }

}
