package org.joutak.jouween.jack.quests.specialQuests;

import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;
import org.joutak.jouween.jack.quests.AbstractQuest;
import org.joutak.jouween.jack.quests.QuestNeedToBeStarted;

public class CompleteRaidQuest extends QuestNeedToBeStarted {

    public CompleteRaidQuest(int id, int weight, int reward) {
        this.id = id;
        this.weight = weight;
        this.reward = reward;
    }

    @Override
    public TextComponent getDescription() {
        return null;
    }

    @Override
    public boolean checkQuest(Player player) {
        return false;
    }

    @Override
    public void completeQuest(Player player) {

    }

    @Override
    public void startQuest(Player player) {

    }

    @Override
    public void failQuest(Player player) {

    }
}
