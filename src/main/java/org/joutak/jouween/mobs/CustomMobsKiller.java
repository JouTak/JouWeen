package org.joutak.jouween.mobs;

import org.bukkit.Bukkit;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.joutak.jouween.JouWeen;
import org.joutak.jouween.config.JouWeenConfig;

public class CustomMobsKiller {

    public void registerMobKiller(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(JouWeen.getInstance(),this::killCustomMobs,0,20*60*5);
    }

    public void killCustomMobs(){
        Bukkit.getWorld(JouWeenConfig.getInstance().getWorldName()).getEntitiesByClass(SkeletonHorse.class)
                .forEach(it ->{
                    PotionEffect potionEffect = it.getPotionEffect(PotionEffectType.SPEED);
                    if (potionEffect == null){
                        return;
                    }
                    if (potionEffect.getAmplifier()!=2){
                        return;
                    }
                    if (it.getTicksLived()<20*60*15){
                        return;
                    }
                    it.setHealth(0);
                });

        Bukkit.getWorld(JouWeenConfig.getInstance().getWorldName()).getEntitiesByClass(Skeleton.class)
                .forEach(it ->{
                    PotionEffect potionEffect = it.getPotionEffect(PotionEffectType.NIGHT_VISION);
                    if (potionEffect == null){
                        return;
                    }
                    if (potionEffect.getAmplifier()!=9){
                        return;
                    }
                    if (it.getTicksLived()<20*60*15){
                        return;
                    }
                    it.setHealth(0);
                });
    }

}
