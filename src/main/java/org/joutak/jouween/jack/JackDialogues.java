package org.joutak.jouween.jack;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.joutak.jouween.config.JouweenConst;
import org.joutak.jouween.jack.data.JackData;
import org.joutak.jouween.jack.data.JackQuestPlayerData;
import org.joutak.jouween.jack.quests.AbstractQuest;
import org.joutak.jouween.jack.quests.AllQuests;

public class JackDialogues {


    public static void doDialogue(Player player, String dialogueName) {
        switch (dialogueName) {
            case "root":
                rootDialogue(player);
                break;
            case "questsRoot":
                questsRoot(player);
                break;
            case "howToTakeQuest":
                howToTakeQuest(player);
                break;
            case "takeQuest":
                takeQuest(player);
                break;
            case "whatIsMyQuest":
                whatIsMyQuest(player);
                break;
            case "completeQuest":
                completeQuest(player);
                break;
            case "declineQuest":
                declineQuest(player);
                break;
            case "howManyQuests":
                howManyQuests(player);
                break;
            case "loreRoot":
                loreRoot(player);
                break;
            default:
                player.sendMessage("kavo?");
                break;


        }
    }

    private static void rootDialogue(Player player) {

        TextComponent loreRoot = Component.text()
                .append(Component.text("1. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("Что здесь происходит?", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("loreRoot"))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();

        TextComponent questsRoot = Component.text()
                .append(Component.text("2. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("Че по квестам?", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("questsRoot"))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();

        TextComponent textComponent = Component.text()
                .append(Component.text("Ну Привет, ", NamedTextColor.DARK_AQUA))
                .append(Component.text(player.getName(), NamedTextColor.GOLD))
                .append(Component.text("!", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("Что тебя интересует?", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(loreRoot)
                .appendNewline()
                .append(questsRoot)
                .build();

        player.sendMessage(textComponent);
    }

    private static void questsRoot(Player player) {

        TextComponent howToTakeQuest = Component.text()
                .append(Component.text("1. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("А объяснишь чуть подробнее?", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("howToTakeQuest"))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();

        TextComponent takeQuest = Component.text()
                .append(Component.text("2. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("Можешь дать квест?", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("takeQuest"))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();

        TextComponent whatIsMyQuest = Component.text()
                .append(Component.text("3. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("Можешь напомнить мой квест?", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("whatIsMyQuest"))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();

        TextComponent completeQuest = Component.text()
                .append(Component.text("4. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("Хочу сдать квест", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("completeQuest"))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();

        TextComponent declineQuest = Component.text()
                .append(Component.text("5. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("Я хочу отказаться от квеста", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("declineQuest"))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();

        TextComponent howManyQuests = Component.text()
                .append(Component.text("6. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("Сколько квестов сейчас выполнено и сколько осталось?", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("howManyQuests"))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();

        TextComponent root = makeBackComponent(7, "root");

        TextComponent textComponent = Component.text()
                .append(Component.text("А что по квестам? Все просто. Их надо делать.Делаешь квесты-помогаешь серверу." +
                        "Не делаешь квесты- делаешь серверу плохо. Что-то еще?", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(howToTakeQuest)
                .appendNewline()
                .append(takeQuest)
                .appendNewline()
                .append(whatIsMyQuest)
                .appendNewline()
                .append(completeQuest)
                .appendNewline()
                .append(declineQuest)
                .appendNewline()
                .append(howManyQuests)
                .appendNewline()
                .append(root)
                .build();

        player.sendMessage(textComponent);

    }

    private static void howToTakeQuest(Player player) {
        TextComponent backComponent = makeBackComponent(1, "questsRoot");

        TextComponent textComponent = Component.text()
                .append(Component.text("Как же ты надоел. Ну слушай. Все реально очень просто. Подходишь ко мне, просишь квест." +
                        " Когда ты его сделал- подходишь ко мне и говоришь, что ты его сделал. Я проверяю. Сделал- красава." +
                        " Также от квеста ты можешь отказаться. Но в таком случае ты отнимешь у всего сервера общее количество квестов." +
                        " Квесты бывают разные по сложности и могут давать разное количество наград. После того, как ты выполнил квест, новый ты сможешь взять в полночь." +
                        " Но если я смогу с тобой связаться, я постараюсь тебе сказать об этом так, чтобы твоя дряная башка поняла." +
                        " Еще вопросы? Я вообще-то очень занят.", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(backComponent)
                .build();

        player.sendMessage(textComponent);
    }

    private static void takeQuest(Player player) {
        JackData.getInstance().read();
        JackQuestPlayerData playerData = JackData.getInstance().getPlayerDataList().stream()
                .filter(it -> it.getPlayerName().equals(player.getName()))
                .findFirst().orElse(null);


        if (playerData == null) {
            TextComponent textComponent = Component.text()
                    .append(Component.text("Ой, ты первый раз берешь квест. Секунду, запишу тебя в книжечку....", NamedTextColor.DARK_AQUA))
                    .build();
            player.sendMessage(textComponent);
            playerData = JackData.getInstance().addPlayer(player);
        }

        TextComponent backComponent = makeBackComponent(1, "questsRoot");

        if (!playerData.isCanTakeQuest()) {
            TextComponent textComponent = Component.text()
                    .append(Component.text("Ты пока не можешь взять новый квест. Выполни предыдущий или подожди полночь.", NamedTextColor.DARK_AQUA))
                    .appendNewline()
                    .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                    .appendNewline()
                    .append(backComponent)
                    .build();
            player.sendMessage(textComponent);
            return;
        }

        player.sendMessage(JackData.getInstance().playerGotQuest(player).getDescription()
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(backComponent));

    }

    private static void whatIsMyQuest(Player player){
        JackData.getInstance().read();
        JackQuestPlayerData playerData = JackData.getInstance().getPlayerDataList().stream()
                .filter(it -> it.getPlayerName().equals(player.getName()))
                .findFirst().orElse(new JackQuestPlayerData());

        TextComponent backComponent = makeBackComponent(1, "questsRoot");

        if (playerData.getCurrentQuestId()==0){
            TextComponent textComponent = Component.text()
                    .append(Component.text("Дурак? Чтобы узнать какой у тебя квест, его нужно сначала взять...", NamedTextColor.DARK_AQUA))
                    .appendNewline()
                    .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                    .appendNewline()
                    .append(backComponent)
                    .build();
            player.sendMessage(textComponent);
            return;
        }

        player.sendMessage(AllQuests.getQuestById(playerData.getCurrentQuestId()).getDescription()
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(backComponent));
    }
    private static void completeQuest(Player player) {
        JackData.getInstance().read();
        JackQuestPlayerData playerData = JackData.getInstance().getPlayerDataList().stream()
                .filter(it -> it.getPlayerName().equals(player.getName()))
                .findFirst().orElse(new JackQuestPlayerData());

        TextComponent backComponent = makeBackComponent(1, "questsRoot");

        if (playerData.getCurrentQuestId()==0){
            TextComponent textComponent = Component.text()
                    .append(Component.text("Дурак? Чтобы выполнить квест, его нужно сначала взять...", NamedTextColor.DARK_AQUA))
                    .appendNewline()
                    .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                    .appendNewline()
                    .append(backComponent)
                    .build();
            player.sendMessage(textComponent);
            return;
        }
        AbstractQuest quest = AllQuests.getQuestById(playerData.getCurrentQuestId());
        if (!quest.checkQuest(player)){
            TextComponent textComponent = Component.text()
                    .append(Component.text("Чета не то ты сделал, молодой. Вспомни, че тебе делать ваще надо)", NamedTextColor.DARK_AQUA))
                    .appendNewline()
                    .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                    .appendNewline()
                    .append(backComponent)
                    .build();
            player.sendMessage(textComponent);
            return;
        }

        quest.completeQuest(player);
        JackData.getInstance().playerCompleteQuest(player);

        TextComponent textComponent = Component.text()
                .append(Component.text("УРА! Поздравляю! Ты сделал квест! Молодец! Новый квест сможешь взять в полночь)", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(backComponent)
                .build();
        player.sendMessage(textComponent);

    }

    private static void declineQuest(Player player) {
        JackData.getInstance().read();
        JackQuestPlayerData playerData = JackData.getInstance().getPlayerDataList().stream()
                .filter(it -> it.getPlayerName().equals(player.getName()))
                .findFirst().orElse(new JackQuestPlayerData());

        TextComponent backComponent = makeBackComponent(1, "questsRoot");

        if (playerData.getCurrentQuestId()==0){
            TextComponent textComponent = Component.text()
                    .append(Component.text("Дурак? Чтобы отказаться от квеста, его нужно сначала взять...", NamedTextColor.DARK_AQUA))
                    .appendNewline()
                    .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                    .appendNewline()
                    .append(backComponent)
                    .build();
            player.sendMessage(textComponent);
            return;
        }

        JackData.getInstance().playerDeclineQuest(player);

        TextComponent textComponent = Component.text()
                .append(Component.text("Эхх... Очень жаль, что ты отказался от квеста. Ты только отдаляешь нас от выполнения задач!", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(backComponent)
                .build();
        player.sendMessage(textComponent);

    }

    private static void howManyQuests(Player player) {
        JackData.getInstance().read();
        TextComponent backComponent = makeBackComponent(1, "questsRoot");

        TextComponent textComponent = Component.text()
                .append(Component.text("Сколько квестов сейчас выполнено? А, сейчас скажу...", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("Выполнено целых... ", NamedTextColor.DARK_AQUA))
                .append(Component.text(JackData.getInstance().completedQuests, NamedTextColor.GOLD))
                .append(Component.text(" из ", NamedTextColor.DARK_AQUA))
                .append(Component.text(JackData.getInstance().neededQuests, NamedTextColor.GOLD))
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(backComponent)
                .build();

        player.sendMessage(textComponent);
    }


    private static void loreRoot(Player player) {

        TextComponent answer1 = Component.text()
                .append(Component.text("1. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("че написть дальше?", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("123"))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();

        TextComponent root = makeBackComponent(2, "root");

        TextComponent textComponent = Component.text()
                .append(Component.text("ответ на вопрос ", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(answer1)
                .appendNewline()
                .append(root)
                .build();

        player.sendMessage(textComponent);
    }

    private static ClickEvent makeClickEvent(String dialogueName) {
        return ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/jack " + dialogueName + " " + JouweenConst.UNIQUE_KEY);
    }

    private static TextComponent makeBackComponent(int dialogueNumber, String dialogueName) {
        return Component.text()
                .append(Component.text(dialogueNumber + ". ", NamedTextColor.DARK_AQUA))
                .append(Component.text("Вернуться назад", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent(dialogueName))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();
    }

}
