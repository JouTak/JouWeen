package org.joutak.jouween.jack.quests;

import lombok.Data;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;

@Data
public abstract class AbstractQuest {

    protected int id;
    protected int weight;
    protected int reward;
    protected String someInfo;

    public abstract TextComponent getDescription();
    public abstract boolean checkQuest(Player player);
    public abstract void completeQuest(Player player);

}
