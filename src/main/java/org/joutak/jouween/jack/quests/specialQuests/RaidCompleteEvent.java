package org.joutak.jouween.jack.quests.specialQuests;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidFinishEvent;
import org.joutak.jouween.jack.data.JackData;
import org.joutak.jouween.jack.data.JackQuestPlayerData;

import java.util.List;

public class RaidCompleteEvent implements Listener {

    @EventHandler
    public void playerFinishRaid(RaidFinishEvent event) {

        if (event.getRaid().getBadOmenLevel() != 5) {
            return;
        }

        List<Player> players = event.getWinners();

        players.forEach(it -> {
            JackQuestPlayerData playerData = JackData.getInstance().findJackQuestPlayer(it.getName());
            if (playerData.getCurrentQuestId()!=12){
                return;
            }
            playerData.setSomeQuestInfo("done");
            JackData.getInstance().replacePlayer(playerData);
        });
    }

}
