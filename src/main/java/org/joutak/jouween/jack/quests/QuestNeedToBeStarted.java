package org.joutak.jouween.jack.quests;

import org.bukkit.entity.Player;

public abstract class QuestNeedToBeStarted extends AbstractQuest {

    public abstract void startQuest(Player player);
    public abstract void failQuest(Player player);

}
