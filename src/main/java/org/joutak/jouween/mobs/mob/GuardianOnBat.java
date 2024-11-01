package org.joutak.jouween.mobs.mob;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.joutak.jouween.mobs.CustomMob;

import java.util.List;

public class GuardianOnBat extends CustomMob {

    public GuardianOnBat(int weight, int luckId) {

        this.weight = weight;
        this.luckId = luckId;
        this.mobName = "Eye";
        this.customNames = List.of(
                "",
                "Flying justice",
                "Eye",
                "blurp",
                "they see you");
    }

    @Override
    public LivingEntity spawnEntity(Location location) {
        Guardian mob = (Guardian) location.getWorld().spawnEntity(location, EntityType.GUARDIAN, CreatureSpawnEvent.SpawnReason.COMMAND);
        TextComponent textComponent = Component.text(this.getRandomName(), TextColor.color(Color.TEAL.asRGB()));
        mob.customName(textComponent);
        mob.setCustomNameVisible(true);
        mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10F);
        mob.setHealth(10F);
        mob.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, PotionEffect.INFINITE_DURATION, luckId, false, false));

        Bat transport = (Bat) location.getWorld().spawnEntity(location, EntityType.BAT, CreatureSpawnEvent.SpawnReason.COMMAND);
        transport.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(5F);
        transport.setHealth(5F);
        transport.setInvisible(true);
        transport.setRemoveWhenFarAway(true);

        transport.addPassenger(mob);

        return transport;
    }

}
