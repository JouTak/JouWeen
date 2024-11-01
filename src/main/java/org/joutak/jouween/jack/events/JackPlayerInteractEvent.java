package org.joutak.jouween.jack.events;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.joutak.jouween.config.JouWeenConfig;
import org.joutak.jouween.jack.Jack;
import org.joutak.jouween.jack.JackDialogues;

public class JackPlayerInteractEvent implements Listener {

    @EventHandler
    public void playerInteractWithJack(PlayerInteractEntityEvent event){

        if (event.getHand().equals(EquipmentSlot.OFF_HAND)){
            return;
        }

        if (!JouWeenConfig.isSecondPhaseEnabled()){
            return;
        }

        if (!event.getRightClicked().equals(Jack.getJackWitherSkeleton())){
            return;
        }

        JackDialogues.doDialogue(event.getPlayer(), "root");
    }

}
