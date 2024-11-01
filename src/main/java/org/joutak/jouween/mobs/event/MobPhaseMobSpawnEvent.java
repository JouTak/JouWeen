package org.joutak.jouween.mobs.event;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.joutak.jouween.JouWeen;
import org.joutak.jouween.config.JouWeenConfig;
import org.joutak.jouween.mobs.AllMobTypes;

public class MobPhaseMobSpawnEvent implements Listener {

    @EventHandler
    public void mobSpawnEvent(CreatureSpawnEvent event) {
        if (!JouWeenConfig.isFirstPhaseEnabled()) {
            return;
        }
        CreatureSpawnEvent.SpawnReason spawnReason = event.getSpawnReason();
        if (!(spawnReason.equals(CreatureSpawnEvent.SpawnReason.SPAWNER_EGG) ||
                spawnReason.equals(CreatureSpawnEvent.SpawnReason.NATURAL) ||
                spawnReason.equals(CreatureSpawnEvent.SpawnReason.VILLAGE_INVASION))) {
            return;
        }

        LivingEntity oldMob = event.getEntity();

        if (!(oldMob.getType().equals(EntityType.ZOMBIE) ||
                oldMob.getType().equals(EntityType.SKELETON) ||
                oldMob.getType().equals(EntityType.WITCH) ||
                oldMob.getType().equals(EntityType.HUSK) ||
                oldMob.getType().equals(EntityType.ZOMBIE_VILLAGER) ||
                oldMob.getType().equals(EntityType.CREEPER) ||
                oldMob.getType().equals(EntityType.SPIDER))) {
            return;
        }

        if (Math.random() > JouWeen.getSculkMobSpawnPercent()) {
            return;
        }

        event.setCancelled(true);
        AllMobTypes.spawnRandomMob(event.getLocation());


    }

}
