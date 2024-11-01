package org.joutak.jouween.jack.data;

import com.google.gson.annotations.Expose;
import lombok.Data;
import org.joutak.jouween.jack.quests.AbstractQuest;

import java.util.Objects;

@Data
public class JackQuestPlayerData {

    @Expose
    private String playerName;

    @Expose
    private boolean canTakeQuest = true;

    @Expose
    private int currentQuestId = 0;

    @Expose
    private String someQuestInfo = "";

    @Expose
    private MobBottlesPlayerData mobBottlesPlayerData = new MobBottlesPlayerData();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JackQuestPlayerData that = (JackQuestPlayerData) o;
        return Objects.equals(playerName, that.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName);
    }
}
