package org.joutak.jouween.mobs.event;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffectType;
import org.joutak.jouween.config.JouWeenConfig;
import org.joutak.jouween.mobs.AllMobTypes;
import org.joutak.jouween.mobs.CustomMob;

public class MobPhasePlayerKillEntityEventHandler implements Listener {

    @EventHandler
    public void playerKillEntityEventHandler(EntityDeathEvent event){

        if (!JouWeenConfig.isFirstPhaseEnabled()){
            return;
        }

        LivingEntity livingEntity = event.getEntity();

        if (livingEntity.getPotionEffect(PotionEffectType.NIGHT_VISION) == null) {
            return;
        }

        if (!(livingEntity.getPotionEffect(PotionEffectType.NIGHT_VISION).getAmplifier() >= 5)) {
            return;
        }

        CustomMob customMob = AllMobTypes.getCustomMobById(livingEntity.getPotionEffect(PotionEffectType.NIGHT_VISION).getAmplifier());

        if (customMob == null) {
            return;
        }

        Entity causingEntity = event.getDamageSource().getCausingEntity();
        if (!(causingEntity instanceof Player)){
            return;
        }

        event.setDroppedExp(0);
        event.setDeathSound(Sound.BLOCK_SCULK_SHRIEKER_SHRIEK);
        event.getDrops().clear();

        AreaEffectCloud cloud = (AreaEffectCloud) livingEntity.getLocation().getWorld().spawnEntity(livingEntity.getLocation(), EntityType.AREA_EFFECT_CLOUD, CreatureSpawnEvent.SpawnReason.COMMAND);
        TextComponent textComponent = Component.text(customMob.getMobName() + " Sculk Essence", TextColor.color(Color.TEAL.asRGB()));
        cloud.customName(textComponent);
        cloud.setCustomNameVisible(true);
        cloud.setColor(Color.TEAL);
        cloud.setParticle(Particle.SCULK_CHARGE_POP);
        cloud.setRadius(5F);
        cloud.setRadiusPerTick(-0.02F);
        cloud.setDuration(200);

    }

}
