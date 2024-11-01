package org.joutak.jouween.mobs.event;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.joutak.jouween.config.JouWeenConfig;
import org.joutak.jouween.mobs.AllMobTypes;

public class MobPhaseEvokerEvent implements Listener {

    @EventHandler
    public void mobSpawnEvent(CreatureSpawnEvent event){
        if (!JouWeenConfig.isFirstPhaseEnabled()){
            return;
        }
        if (!event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.SPELL)) {
            return;
        }

        LivingEntity oldMob = event.getEntity();
        if (!oldMob.getType().equals(EntityType.VEX)){
            return;
        }

        event.setCancelled(true);

        AllMobTypes.spawnRandomMob(event.getLocation());

    }

}
