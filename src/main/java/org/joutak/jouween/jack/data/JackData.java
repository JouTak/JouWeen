package org.joutak.jouween.jack.data;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.joutak.jouween.config.JouweenConst;
import org.joutak.jouween.jack.files.JackFileReader;
import org.joutak.jouween.jack.files.JackFileWriter;
import org.joutak.jouween.jack.quests.AbstractQuest;
import org.joutak.jouween.jack.quests.AllQuests;

import java.util.ArrayList;
import java.util.List;

@Data
public class JackData {

    @Getter
    private static JackData instance;

    @Expose
    @Getter
    @Setter
    public double x = 0;

    @Expose
    @Getter
    @Setter
    public double y = 0;

    @Expose
    @Getter
    @Setter
    public double z = 0;

    @Expose
    @Getter
    @Setter
    public int completedQuests = 0;

    @Expose
    @Getter
    @Setter
    public int neededQuests = 100;

    @Expose
    @Getter
    public List<JackQuestPlayerData> playerDataList = new ArrayList<>();

    public void read() {
        JackData jackData = new JackFileReader(JouweenConst.JACK_FILEPATH).readJack();
        this.x = jackData.x;
        this.y = jackData.y;
        this.z = jackData.z;
        this.completedQuests = jackData.completedQuests;
        this.neededQuests = jackData.neededQuests;
        this.playerDataList = jackData.playerDataList;
        instance = this;
    }

    public void write() {
        new JackFileWriter(JouweenConst.JACK_FILEPATH).write(this);
    }

    public JackQuestPlayerData addPlayer(Player player) {
        JackQuestPlayerData playerData = new JackQuestPlayerData();
        playerData.setPlayerName(player.getName());
        playerDataList.add(playerData);
        new JackFileWriter(JouweenConst.JACK_FILEPATH).write(this);
        return playerData;
    }

    public AbstractQuest playerGotQuest(Player player) {
        JackQuestPlayerData jackQuestPlayerData = playerDataList.stream().filter(it -> it.getPlayerName().equals(player.getName())).findFirst().orElse(null);

        if (jackQuestPlayerData == null) {
            player.sendMessage("Ты почему-то не можешь взять квест. Скрины lapitaniy на стол");
            throw new RuntimeException("PLAYER " + player.getName() + " CAN'T TAKE QUEST!!! NEED TO HOTFIX!!!!");
        }

        AbstractQuest abstractQuest = AllQuests.getRandomQuest();
        jackQuestPlayerData.setCanTakeQuest(false);
        jackQuestPlayerData.setCurrentQuestId(abstractQuest.getId());
        jackQuestPlayerData.setSomeQuestInfo(abstractQuest.getSomeInfo());

        playerDataList.replaceAll(it -> {
            if (it.equals(jackQuestPlayerData)) {
                return jackQuestPlayerData;
            }
            return it;
        });

        new JackFileWriter(JouweenConst.JACK_FILEPATH).write(this);

        return abstractQuest;
    }

    public void playerCompleteQuest(Player player) {
        JackQuestPlayerData jackQuestPlayerData = playerDataList.stream().filter(it -> it.getPlayerName().equals(player.getName())).findFirst().orElse(null);

        if (jackQuestPlayerData == null) {
            player.sendMessage("Чета пиздец не так. Ты вроде можешь комплитнуть квест, но почему-то тебя нет в списке. Кто ты и что ты кликнул скриншоты на стол lapitaniy");
            throw new RuntimeException("PLAYER " + player.getName() + " CAN'T COMPLETE QUEST!!! NEED TO HOTFIX!!!!");
        }

        completedQuests += AllQuests.getQuestById(jackQuestPlayerData.getCurrentQuestId()).getReward();

        jackQuestPlayerData.setCanTakeQuest(false);
        jackQuestPlayerData.setCurrentQuestId(0);
        jackQuestPlayerData.setSomeQuestInfo("");

        playerDataList.replaceAll(it -> {
            if (it.equals(jackQuestPlayerData)) {
                return jackQuestPlayerData;
            }
            return it;
        });

        new JackFileWriter(JouweenConst.JACK_FILEPATH).write(this);

    }

    public void playerDeclineQuest(Player player) {
        JackQuestPlayerData jackQuestPlayerData = playerDataList.stream().filter(it -> it.getPlayerName().equals(player.getName())).findFirst().orElse(null);

        if (jackQuestPlayerData == null) {
            player.sendMessage("Чета пиздец не так. Ты вроде можешь отказаться от квеста, но почему-то тебя нет в списке. Кто ты и что ты кликнул скриншоты на стол lapitaniy");
            throw new RuntimeException("PLAYER " + player.getName() + " CAN'T COMPLETE QUEST!!! NEED TO HOTFIX!!!!");
        }

        completedQuests -= AllQuests.getQuestById(jackQuestPlayerData.getCurrentQuestId()).getReward();

        jackQuestPlayerData.setCanTakeQuest(false);
        jackQuestPlayerData.setCurrentQuestId(0);
        jackQuestPlayerData.setSomeQuestInfo("");

        playerDataList.replaceAll(it -> {
            if (it.equals(jackQuestPlayerData)) {
                return jackQuestPlayerData;
            }
            return it;
        });

        new JackFileWriter(JouweenConst.JACK_FILEPATH).write(this);

    }
}
