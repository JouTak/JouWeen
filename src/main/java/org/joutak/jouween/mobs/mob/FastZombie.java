package org.joutak.jouween.mobs.mob;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Color;
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
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.joutak.jouween.mobs.CustomMob;

import java.util.List;

public class FastZombie extends CustomMob {

    public FastZombie(int weight, int luckId) {

        this.weight = weight;
        this.luckId = luckId;
        this.mobName = "Speedster";
        this.customNames = List.of(
                "",
                "zoooomboooo",
                "booo",
                "small sculk Zombie",
                "ryaaawr");
    }

    @Override
    public LivingEntity spawnEntity(Location location) {
        Zombie mob = (Zombie) location.getWorld().spawnEntity(location, EntityType.ZOMBIE, CreatureSpawnEvent.SpawnReason.COMMAND);
        mob.setBaby();
        mob.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 3));
        mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10F);
        mob.setHealth(10F);
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

        return mob;
    }

}
