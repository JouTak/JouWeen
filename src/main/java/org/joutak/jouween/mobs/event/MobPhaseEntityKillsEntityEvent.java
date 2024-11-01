package org.joutak.jouween.mobs.event;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.raid.RaidFinishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.joutak.jouween.config.JouWeenConfig;
import org.joutak.jouween.mobs.AllMobTypes;
import org.joutak.jouween.mobs.CustomMob;

import java.util.List;

public class MobPhaseEntityKillsEntityEvent implements Listener {

    @EventHandler
    public void entityKillsEntityEvent(EntityDeathEvent event){

        if (!JouWeenConfig.isFirstPhaseEnabled()){
            return;
        }

        Entity causingEntity = event.getDamageSource().getCausingEntity();

        if (causingEntity==null){
            return;
        }

        if (!causingEntity.getType().equals(EntityType.SKELETON)){
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

        event.setDroppedExp(0);
        event.setDeathSound(Sound.BLOCK_SCULK_SHRIEKER_SHRIEK);
        event.getDrops().clear();

        ItemStack musicDisc = new ItemStack(Material.MUSIC_DISC_11);

        ItemMeta itemMeta = musicDisc.getItemMeta();
        itemMeta.displayName(Component.text().append(Component.text("Моча Съела Говно", NamedTextColor.GOLD)).build());
        itemMeta.lore(List.of(Component.text().append(Component.text("Казенный Унитаз", NamedTextColor.GOLD)).build()));
        itemMeta.setEnchantmentGlintOverride(true);

        musicDisc.setItemMeta(itemMeta);
        itemMeta.getAsComponentString();

        event.getDrops().add(musicDisc);

    }

}
