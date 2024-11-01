package org.joutak.jouween.mobs.event;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.joutak.jouween.config.JouWeenConfig;

public class MobPhasePlayerHitEntityEvent implements Listener {

    @EventHandler
    public void playerHitEntityEvent(EntityDamageEvent event) {
        if (!JouWeenConfig.isFirstPhaseEnabled()) {
            return;
        }
        Entity entity = event.getEntity();

        if (!(entity instanceof LivingEntity)) {
            return;
        }

        LivingEntity livingEntity = (LivingEntity) entity;

        if (livingEntity.getPotionEffect(PotionEffectType.NIGHT_VISION) == null) {
            return;
        }

        if (!(livingEntity.getPotionEffect(PotionEffectType.NIGHT_VISION).getAmplifier() >= 5)) {
            return;
        }


        if (!(event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK) ||
                event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK) ||
                event.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE))) {
            event.setCancelled(true);
            return;
        }

        Entity causingEntity = event.getDamageSource().getCausingEntity();
        if (!(causingEntity instanceof Player ||
                causingEntity instanceof Arrow)) {
            return;
        }

        if (causingEntity instanceof Arrow) {
            Arrow arrow = (Arrow) causingEntity;
            ProjectileSource projectileSource = arrow.getShooter();
            if (projectileSource instanceof Skeleton) {
                return;
            }
            if (!(projectileSource instanceof Player)) {
                event.setCancelled(true);
                return;
            }
            causingEntity = (Entity) projectileSource;
        }

        Player player = (Player) causingEntity;

        AreaEffectCloud cloud = (AreaEffectCloud) livingEntity.getLocation().getWorld().spawnEntity(livingEntity.getLocation(), EntityType.AREA_EFFECT_CLOUD, CreatureSpawnEvent.SpawnReason.COMMAND);
        cloud.setColor(Color.TEAL);
        cloud.setParticle(Particle.SCULK_SOUL);
        cloud.setRadius(1F);
        cloud.setDuration(5);

        player.playSound(livingEntity, Sound.BLOCK_SCULK_CATALYST_BLOOM, 30F, 0F);
    }

}
