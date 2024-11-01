package org.joutak.jouween.mobs.event;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.joutak.jouween.config.JouWeenConfig;

import java.util.HashMap;
import java.util.List;

public class MobPhasePlayerInteractWithEntity implements Listener {

    @EventHandler
    public void playerInteractEntityEventHandler(PlayerInteractEvent event) {

        if (!JouWeenConfig.isFirstPhaseEnabled()){
            return;
        }

        if (event.getHand()==null){
            return;
        }

        if (event.getHand().equals(EquipmentSlot.OFF_HAND)) {
            return;
        }

        Player player = event.getPlayer();

        ItemStack activeItem = player.getInventory().getItemInMainHand();

        if (activeItem.getItemMeta() == null) {
            return;
        }

        if (activeItem.getItemMeta().hasCustomModelData()){
            return;
        }

        if (!activeItem.getType().equals(Material.GLASS_BOTTLE)) {
            return;
        }

        AreaEffectCloud cloud;

        if (event.getInteractionPoint()!=null){
            cloud = event.getPlayer().getLocation().getWorld().getNearbyEntitiesByType(AreaEffectCloud.class, event.getInteractionPoint(), 1D).stream().findFirst().orElse(null);
        } else {
            cloud = event.getPlayer().getLocation().getWorld().getNearbyEntitiesByType(AreaEffectCloud.class, event.getPlayer().getLocation(), 5D).stream().findFirst().orElse(null);
        }

//        System.out.println(event.getPlayer().getLocation().getWorld().getNearbyEntities(event.getInteractionPoint(), 1D, 1D, 1D).stream());

//        AreaEffectCloud cloud = (AreaEffectCloud) event.getPlayer().getLocation().getWorld().getNearbyEntities(event.getInteractionPoint(), 1D, 1D, 1D).stream().filter(
//                entity -> {
//                    if (!(entity instanceof AreaEffectCloud)) {
//                        return false;
//                    }
//
//                    AreaEffectCloud areaEffectCloud = (AreaEffectCloud) entity;
//                    TextComponent textComponent = (TextComponent) areaEffectCloud.customName();
//                    if (textComponent == null) {
//                        return false;
//                    }
//                    return textComponent.content().contains("Sculk Essence");
//                }
//        ).findFirst().orElse(null);

        if (cloud == null) {
            return;
        }

        if (cloud.customName()==null){
            return;
        }

        if (!((TextComponent) cloud.customName()).content().contains("Sculk Essence")){
            return;
        }

        activeItem.setAmount(activeItem.getAmount() - 1);

        ItemStack sculkBottle = new ItemStack(Material.GLASS_BOTTLE);
        ItemMeta sculkBottleItemMeta = sculkBottle.getItemMeta();
        TextComponent lore = Component.text("Something is pulsating inside", TextColor.color(Color.PURPLE.asRGB()));
        sculkBottleItemMeta.lore(List.of(lore));

        String mobName = ((TextComponent) cloud.customName()).content().split(" ")[0];
        TextComponent name = Component.text(mobName + " Sculk essence in a bottle", TextColor.color(Color.TEAL.asRGB()));
        sculkBottleItemMeta.displayName(name);

        sculkBottleItemMeta.setCustomModelData(52);

        sculkBottle.setItemMeta(sculkBottleItemMeta);

        cloud.setRadius(0F);

        HashMap<Integer, ItemStack> excessBottles = player.getInventory().addItem(sculkBottle);

        if (!excessBottles.isEmpty()) {
            Item droppedItem = (Item) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.ITEM);
            droppedItem.setItemStack(excessBottles.get(0));
        }

    }

}
