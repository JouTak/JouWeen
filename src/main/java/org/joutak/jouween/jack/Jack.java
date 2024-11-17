package org.joutak.jouween.jack;

import lombok.Data;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.joutak.jouween.config.JouWeenConfig;
import org.joutak.jouween.jack.data.JackData;

@Data
public class Jack {

    private static Jack instance;

    @Getter
    private static WitherSkeleton jackWitherSkeleton;

    private Jack() {
        instance = this;
        checkOrSpawnJack();

//        jackWitherSkeleton.remove
    }

    private void checkOrSpawnJack() {

        if (!JouWeenConfig.isSecondPhaseEnabled()) {
            return;
        }

        World world = Bukkit.getWorld(JouWeenConfig.getInstance().worldName);

        if (world == null) {
            throw new RuntimeException("WORLD NAME IS INCORRECT!!!!");
        }

        Location location = new Location(world, JackData.getInstance().getX(), JackData.getInstance().getY(), JackData.getInstance().getZ());

        world.getNearbyEntities(location, 5D, 5D, 5D);

        WitherSkeleton witherSkeleton = world.getNearbyEntitiesByType(WitherSkeleton.class, location, 5D).stream()
                .filter(it -> it.getEntitySpawnReason().equals(CreatureSpawnEvent.SpawnReason.COMMAND)).findFirst().orElse(null);

        if (witherSkeleton == null) {
            ArmorStand armorStand = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND, CreatureSpawnEvent.SpawnReason.COMMAND);
            armorStand.setInvisible(true);
            armorStand.setMarker(true);
            armorStand.setGravity(false);
            armorStand.setCanMove(false);
            armorStand.setInvulnerable(true);
            armorStand.setAI(false);
            armorStand.setRemoveWhenFarAway(false);
            witherSkeleton = (WitherSkeleton) world.spawnEntity(location, EntityType.WITHER_SKELETON, CreatureSpawnEvent.SpawnReason.COMMAND);
            witherSkeleton.getEquipment().setHelmet(new ItemStack(Material.JACK_O_LANTERN));
            witherSkeleton.getEquipment().setItemInMainHand(new ItemStack(Material.SOUL_LANTERN));
            witherSkeleton.getEquipment().setItemInOffHand(new ItemStack(Material.GLASS_BOTTLE));
            witherSkeleton.setAI(false);
            witherSkeleton.setInvulnerable(true);
            witherSkeleton.setRemoveWhenFarAway(false);
            TextComponent textComponent = Component.text("Безголовый Джек", TextColor.color(Color.ORANGE.asRGB()));
            witherSkeleton.customName(textComponent);
            witherSkeleton.setCustomNameVisible(true);
            armorStand.addPassenger(witherSkeleton);
        }

        if (jackWitherSkeleton == null) {
            jackWitherSkeleton = witherSkeleton;
            return;
        }

        if (!witherSkeleton.getLocation().equals(jackWitherSkeleton.getLocation())) {
            jackWitherSkeleton.setHealth(0);
        }

        jackWitherSkeleton = witherSkeleton;

    }

    public static Jack getInstance() {
        if (instance == null) {
            return new Jack();
        }
        return instance;
    }

    public static void create() {
        new Jack();
    }

}
