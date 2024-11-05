package org.joutak.jouween.jack.quests;

import org.joutak.jouween.mobs.CustomMob;

import java.util.ArrayList;
import java.util.List;

public class AllQuests {

    private static int weightSum = 2;

    private static List<AbstractQuest> quests = new ArrayList<>();

    public static void addQuest(AbstractQuest quest){
        weightSum+=quest.getWeight();
        quests.add(quest);
    }

    public static AbstractQuest getRandomQuest(){
        int randomNumber = (int) Math.ceil(Math.random() * weightSum);
        int curr = 0;
        AbstractQuest quest = quests.get(0);
        while (randomNumber > 0) {
            AbstractQuest customMob = quests.get(curr);
            if (customMob.getWeight() >= randomNumber) {
                quest = quests.get(curr);
                break;
            }
            randomNumber -= customMob.getWeight();
            curr++;
        }
        return quest;
    }

    public static AbstractQuest getQuestById(int targetId){
        return quests.stream().filter(it -> it.id==targetId).findFirst().orElse(null);
    }


}
