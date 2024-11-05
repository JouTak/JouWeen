package org.joutak.jouween.mobs.event;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffectType;
import org.joutak.jouween.config.JouWeenConfig;

public class KillSkeletonOnHorseEvent implements Listener {

    @EventHandler
    public void killSkeletonHorse(EntityDeathEvent event){

        if (!JouWeenConfig.isFirstPhaseEnabled()){
            return;
        }

        LivingEntity livingEntity = event.getEntity();

        if (!(livingEntity instanceof SkeletonHorse)){
            return;
        }

        if (livingEntity.getPotionEffect(PotionEffectType.SPEED) == null) {
            return;
        }

        if (livingEntity.getPotionEffect(PotionEffectType.SPEED).getAmplifier() != 2) {
            return;
        }

        event.setDroppedExp(0);
        event.getDrops().clear();

    }

    @EventHandler
    public void killSkeletonWithCommand(EntityDeathEvent event){

        if (!JouWeenConfig.isFirstPhaseEnabled()){
            return;
        }

        LivingEntity livingEntity = event.getEntity();

        if (!(livingEntity instanceof Skeleton)){
            return;
        }

        if (livingEntity.getPotionEffect(PotionEffectType.NIGHT_VISION) == null) {
            return;
        }

        if (livingEntity.getPotionEffect(PotionEffectType.NIGHT_VISION).getAmplifier() != 9) {
            return;
        }

        event.setDroppedExp(0);
        event.getDrops().clear();

    }
}
