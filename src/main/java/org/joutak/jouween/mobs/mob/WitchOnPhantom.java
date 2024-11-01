package org.joutak.jouween.mobs.mob;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.joutak.jouween.Utils;
import org.joutak.jouween.mobs.CustomMob;

import java.util.List;

public class WitchOnPhantom extends CustomMob {

    public WitchOnPhantom(int weight, int luckId) {

        this.weight = weight;
        this.luckId = luckId;
        this.mobName = "Witch";
        this.customNames = List.of(
                "",
                "he he he he",
                "SPLASH!",
                "CURSE YOU!",
                "hee hee hee hee heee");
    }

    @Override
    public LivingEntity spawnEntity(Location location) {
        Witch mob = (Witch) location.getWorld().spawnEntity(location, EntityType.WITCH, CreatureSpawnEvent.SpawnReason.COMMAND);
        TextComponent textComponent = Component.text(this.getRandomName(), TextColor.color(Color.TEAL.asRGB()));
        mob.customName(textComponent);
        mob.setCustomNameVisible(true);
        mob.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, PotionEffect.INFINITE_DURATION, luckId, false, false));


        Phantom transport = (Phantom) location.getWorld().spawnEntity(location, EntityType.PHANTOM, CreatureSpawnEvent.SpawnReason.COMMAND);
        transport.setShouldBurnInDay(false);
        transport.setRemoveWhenFarAway(true);

        transport.addPassenger(mob);

        Player player = Utils.getNearestPlayerToLocation(location);

        if (player == null) {
            return transport;
        }

        transport.setTarget(player);

        return transport;
    }

}
