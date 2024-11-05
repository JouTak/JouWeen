package org.joutak.jouween.mobs.mob;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ColorableArmorMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.ShieldMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.joutak.jouween.mobs.CustomMob;

import java.util.List;

public class SkeletonOnHorse extends CustomMob {

    public SkeletonOnHorse(int weight, int luckId) {

        this.weight = weight;
        this.luckId = luckId;
        this.mobName = "SkeleHorse";
        this.customNames = List.of(
                "",
                "Mr.HorseDec",
                "I'm gonna to take my horse",
                "fast and furious",
                "*Intense Skeleton Horse Sounds*");
    }

    @Override
    public LivingEntity spawnEntity(Location location) {
        Skeleton mob = (Skeleton) location.getWorld().spawnEntity(location, EntityType.SKELETON, CreatureSpawnEvent.SpawnReason.COMMAND);
        TextComponent textComponent = Component.text(this.getRandomName(), TextColor.color(Color.TEAL.asRGB()));
        mob.customName(textComponent);
        mob.setCustomNameVisible(true);
        mob.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, PotionEffect.INFINITE_DURATION, luckId, false, false));

        ItemStack helmet = new ItemStack(getRandomSculkMaterial());
        ItemStack chestPlate = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

        ColorableArmorMeta leatherArmorMeta = (ColorableArmorMeta) chestPlate.getItemMeta();
        leatherArmorMeta.setColor(Color.TEAL);
        leatherArmorMeta.setTrim(new ArmorTrim(TrimMaterial.NETHERITE, TrimPattern.SILENCE));
        leatherArmorMeta.setUnbreakable(true);
        leatherArmorMeta.addEnchant(Enchantment.PROTECTION, 1, true);

        chestPlate.setItemMeta(leatherArmorMeta);
        leggings.setItemMeta(leatherArmorMeta);
        boots.setItemMeta(leatherArmorMeta);

        mob.getEquipment().setHelmet(helmet);
        mob.getEquipment().setChestplate(chestPlate);
        mob.getEquipment().setLeggings(leggings);
        mob.getEquipment().setBoots(boots);
        mob.setCanPickupItems(false);

        ItemStack bow = new ItemStack(Material.BOW);
        ItemStack shield = new ItemStack(Material.SHIELD);

        ShieldMeta shieldMeta = (ShieldMeta) shield.getItemMeta();
        shieldMeta.setBaseColor(DyeColor.BLACK);
        shield.setItemMeta(shieldMeta);

        ItemMeta bowItemMeta = bow.getItemMeta();
        bowItemMeta.addEnchant(Enchantment.POWER, 3, true);
        bow.setItemMeta(bowItemMeta);

        mob.getEquipment().setItemInMainHand(bow);
        mob.getEquipment().setItemInOffHand(shield);

        SkeletonHorse transport = (SkeletonHorse) location.getWorld().spawnEntity(location, EntityType.SKELETON_HORSE, CreatureSpawnEvent.SpawnReason.COMMAND);
        transport.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
        transport.setTamed(true);
        mob.setRemoveWhenFarAway(true);
        transport.setRemoveWhenFarAway(true);

        mob.setCanPickupItems(false);

        transport.addPassenger(mob);

        return transport;
    }

}
