package org.joutak.jouween.mobs.mob;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ColorableArmorMeta;
import org.bukkit.inventory.meta.ShieldMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.joutak.jouween.mobs.CustomMob;

import java.util.List;

public class StrongZombie extends CustomMob {

    public StrongZombie(int weight, int luckId) {

        this.weight = weight;
        this.luckId = luckId;
        this.mobName = "Muscle";
        this.customNames = List.of(
                "",
                "ZOMBOOOOO",
                "SPOOOOKYYYY",
                "Strong Sculk Zombie",
                "RAAAAAAAWRRRRR");
    }

    @Override
    public LivingEntity spawnEntity(Location location) {
        Zombie mob = (Zombie) location.getWorld().spawnEntity(location, EntityType.ZOMBIE, CreatureSpawnEvent.SpawnReason.COMMAND);
        TextComponent textComponent = Component.text(this.getRandomName(), TextColor.color(Color.TEAL.asRGB()));
        mob.customName(textComponent);
        mob.setCustomNameVisible(true);
        mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100F);
        mob.setHealth(100F);
        mob.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, Integer.MAX_VALUE, 1));
        mob.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, Integer.MAX_VALUE, 2));
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

        ItemStack ironAxe = new ItemStack(Material.IRON_AXE);
        ItemStack shield = new ItemStack(Material.SHIELD);

        ShieldMeta shieldMeta = (ShieldMeta) shield.getItemMeta();
        shieldMeta.setBaseColor(DyeColor.BLACK);
        shield.setItemMeta(shieldMeta);

        mob.getEquipment().setItemInMainHand(ironAxe);
        mob.getEquipment().setItemInOffHand(shield);
        mob.setCanPickupItems(false);

        return mob;
    }

}
