package org.joutak.jouween.jack.quests.specialQuests;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.joutak.jouween.jack.data.JackData;
import org.joutak.jouween.jack.quests.AbstractQuest;
import org.joutak.jouween.jack.quests.QuestNeedToBeStarted;

public class CompleteRaidQuest extends QuestNeedToBeStarted {

    public CompleteRaidQuest(int id, int weight, int reward) {
        this.id = id;
        this.weight = weight;
        this.reward = reward;
        this.someInfo="inProcess";
    }

    @Override
    public TextComponent getDescription() {
        return Component.text()
                .append(Component.text("Хочу понять,как человеческие создания могут скапливаться. Узнать механизм этого. Можешь отразить ", NamedTextColor.DARK_AQUA))
                .append(Component.text("Рейд 5го уровня", NamedTextColor.GOLD))
                .append(Component.text("? Думаю, одного рейда хватит", NamedTextColor.DARK_AQUA))
                .build();
    }

    @Override
    public boolean checkQuest(Player player) {
        return JackData.getInstance().findJackQuestPlayer(player.getName()).getSomeQuestInfo().equals("done");
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
