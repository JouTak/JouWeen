package org.joutak.jouween.mobs.mob;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Spellcaster;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.joutak.jouween.mobs.CustomMob;

import java.util.List;

public class SculkEvoker extends CustomMob {

    public SculkEvoker(int weight, int luckId) {

        this.weight = weight;
        this.luckId = luckId;
        this.mobName = "Spreader";
        this.customNames = List.of(
                "",
                "I SUMMON!!!",
                "SCULK SPREADS!!!",
                "dies from 1d4 psycho damage",
                "кусь");
    }

    @Override
    public LivingEntity spawnEntity(Location location) {
        Evoker mob = (Evoker) location.getWorld().spawnEntity(location, EntityType.EVOKER, CreatureSpawnEvent.SpawnReason.COMMAND);
        TextComponent textComponent = Component.text(this.getRandomName(), TextColor.color(Color.TEAL.asRGB()));
        mob.customName(textComponent);
        mob.setCustomNameVisible(true);
        mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50F);
        mob.setHealth(50F);
        mob.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, PotionEffect.INFINITE_DURATION, luckId, false, false));

        mob.setSpell(Spellcaster.Spell.SUMMON_VEX);
        mob.setCanPickupItems(false);
        mob.setCanJoinRaid(false);
        mob.setTicksOutsideRaid(20);

        return mob;
    }

}
