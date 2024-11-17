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
            case "eventPack":
                eventPack(player);
                break;
            case "eventMobAnswer":
                eventMobAnswer(player);
                break;
            case "eventSmokeAnswer":
                eventSmokeAnswer(player);
                break;
            case "eventAmountAnswer":
                eventAmountAnswer(player);
                break;
            case "eventAmountAnswerContinuation":
                eventAmountAnswerContinuation(player);
                break;
            case "eventWhenAnswer":
                eventWhenAnswer(player);
                break;
            case "eventFutureAnswer":
                eventFutureAnswer(player);
                break;
            case "jackPack":
                jackPack(player);
                break;
            case "jackMaybeAnswer":
                jackMaybeAnswer(player);
                break;
            case "jackWhereAnswer":
                jackWhereAnswer(player);
                break;
            case "jackLookAnswer":
                jackLookAnswer(player);
                break;
            case "powerPack":
                powerPack(player);
                break;
            case "powerOneAnswer":
                powerOneAnswer(player);
                break;
            case "powerOneAnswerContinuation":
                powerOneAnswerContinuation(player);
                break;
            case "powerTwoAnswer":
                powerTwoAnswer(player);
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
                    .append(Component.text("Ты пока не можешь взять новый квест. Выполни предыдущий, после чего подожди полночь.", NamedTextColor.DARK_AQUA))
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

        TextComponent eventPack = Component.text()
                .append(Component.text("1. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("А можно поподробнее про эту \"беду\"?", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("eventPack"))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();

        TextComponent jackPack = Component.text()
                .append(Component.text("2. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("Кто ты?", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("jackPack"))
                .hoverEvent(HoverEvent.showText(Component.text("*Клик*")))
                .build();

        TextComponent powerPack = Component.text()
                .append(Component.text("3. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("А сам ты с духами справиться не можешь?", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("powerPack"))
                .hoverEvent(HoverEvent.showText(Component.text("*Клик*")))
                .build();

        TextComponent root = makeBackComponent(4, "root");

        TextComponent textComponent = Component.text()
                .append(Component.text("Я здесь, чтобы предупредить вас о надвигающейся беде." +
                        " Духи взбунтовались, они стали сильнее и опаснее." +
                        " Если мы не возьмем ситуацию под контроль, то все может закончиться катастрофой." +
                        " Я знаю, что делать, но мне нужна ваша помощь.", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(eventPack)
                .appendNewline()
                .append(jackPack)
                .appendNewline()
                .append(powerPack)
                .appendNewline()
                .append(root)
                .build();

        player.sendMessage(textComponent);
    }

    private static void eventPack(Player player) {

        TextComponent mob = Component.text()
                .append(Component.text("1. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("Что за духи вселились в мобов?", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("eventMobAnswer"))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();

        TextComponent smoke = Component.text()
                .append(Component.text("2. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("Зачем дымка?", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("eventSmokeAnswer"))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();

        TextComponent when = Component.text()
                .append(Component.text("3. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("Когда все это закончится?", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("eventWhenAnswer"))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();

        TextComponent future = Component.text()
                .append(Component.text("4. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("Что будет, если мы не справимся?", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("eventFutureAnswer"))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();

        TextComponent loreRoot = makeBackComponent(5, "loreRoot");

        TextComponent textComponent = Component.text()
                .append(Component.text("Что ты хочешь узнать?", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(mob)
                .appendNewline()
                .append(smoke)
                .appendNewline()
                .append(when)
                .appendNewline()
                .append(future)
                .appendNewline()
                .append(loreRoot)
                .build();

        player.sendMessage(textComponent);

    }

    private static void eventMobAnswer(Player player) {

        TextComponent eventPack = makePackBackComponent(1, "eventPack");

        TextComponent loreRoot = makeBackComponent(2, "loreRoot");

        TextComponent textComponent = Component.text()
                .append(Component.text("Это злые духи, сильные и беспощадные. " +
                        "Они питаются страхом и яростью, и сейчас ищут способы разрушить наш мир. " +
                        "Эти духи вселяются в существ, делая их очень сильными. " +
                        "Если вы сразитесь с ними, сможете собрать их темную энергию — она понадобится для противодействия их злу.", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(eventPack)
                .appendNewline()
                .append(loreRoot)
                .build();

        player.sendMessage(textComponent);
    }

    private static void eventSmokeAnswer(Player player) {

        TextComponent amount = Component.text()
                .append(Component.text("1. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("А сколько всего видов дымки?", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("eventAmountAnswer"))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();

        TextComponent eventPack = makePackBackComponent(2, "eventPack");

        TextComponent loreRoot = makeBackComponent(3, "loreRoot");

        TextComponent textComponent = Component.text()
                .append(Component.text("Это остатки силы духов. Собирайте их — без этой силы я не смогу изгнать тьму. " +
                        "Сейчас мне нечего больше сказать, но каждый флакон может спасти нас.", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(amount)
                .appendNewline()
                .append(eventPack)
                .appendNewline()
                .append(loreRoot)
                .build();

        player.sendMessage(textComponent);
    }

    private static void eventAmountAnswer(Player player) {

        TextComponent cont = Component.text()
                .append(Component.text("1. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("?", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("eventAmountAnswerContinuation"))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();

        TextComponent textComponent = Component.text()
                .append(Component.text("Много! Но если по-секрету, то мне достоверно известно ровно о стольки эссенцийях...", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(cont)
                .build();

        player.sendMessage(textComponent);
    }

    private static void eventAmountAnswerContinuation(Player player) {

        TextComponent eventPack = makePackBackComponent(1, "eventPack");

        TextComponent loreRoot = makeBackComponent(2, "loreRoot");

        TextComponent textComponent = Component.text()
                .append(Component.text("А, я придумал! Добавь к ответу на главный вопрос жизни, вселенной и всего такого " +
                        "повернутую бесконечность и раздели все это на допустим... часть зверя. " +
                        "Обрезочки, конечно, не считаются", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(eventPack)
                .appendNewline()
                .append(loreRoot)
                .build();

        player.sendMessage(textComponent);
    }

    private static void eventWhenAnswer(Player player) {

        TextComponent eventPack = makePackBackComponent(1, "eventPack");

        TextComponent loreRoot = makeBackComponent(2, "loreRoot");

        TextComponent textComponent = Component.text()
                .append(Component.text("Когда будет собрано достаточно силы и я смогу завершить подготовку к ритуалу. " +
                        "Пока же продолжайте, и помните: медлить нельзя, иначе нас ждет нечто ужасное.", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(eventPack)
                .appendNewline()
                .append(loreRoot)
                .build();

        player.sendMessage(textComponent);
    }

    private static void eventFutureAnswer(Player player) {

        TextComponent eventPack = makePackBackComponent(1, "eventPack");

        TextComponent loreRoot = makeBackComponent(2, "loreRoot");

        TextComponent textComponent = Component.text()
                .append(Component.text("Даже не думайте об этом. Луна уже меняется — скоро это почувствуют все. " +
                        "Если мы не поторопимся, весь мир окажется во власти тьмы.", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(eventPack)
                .appendNewline()
                .append(loreRoot)
                .build();

        player.sendMessage(textComponent);
    }

    private static void jackPack(Player player) {

        TextComponent maybe = Component.text()
                .append(Component.text("1. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("А может всё же?..", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("jackMaybeAnswer"))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();

        TextComponent where = Component.text()
                .append(Component.text("2. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("Откуда ты?", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("jackWhereAnswer"))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();

        TextComponent look = Component.text()
                .append(Component.text("3. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("Почему ты выглядишь так?", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("jackLookAnswer"))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();

        TextComponent loreRoot = makeBackComponent(4, "loreRoot");

        TextComponent textComponent = Component.text()
                .append(Component.text("Ха-ха, я Джек спасибо что поинтересовался, но я не знакомлюсь.", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(maybe)
                .appendNewline()
                .append(where)
                .appendNewline()
                .append(look)
                .appendNewline()
                .append(loreRoot)
                .build();

        player.sendMessage(textComponent);
    }

    private static void jackMaybeAnswer(Player player) {

        TextComponent jackPack = makePackBackComponent(1, "jackPack");

        TextComponent loreRoot = makeBackComponent(2, "loreRoot");

        TextComponent textComponent = Component.text()
                .append(Component.text("Конечно! Выполни все мои задания а потом я обязательно перезвоню тебе", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(jackPack)
                .appendNewline()
                .append(loreRoot)
                .build();

        player.sendMessage(textComponent);
    }

    private static void jackWhereAnswer(Player player) {

        TextComponent jackPack = makePackBackComponent(1, "jackPack");

        TextComponent loreRoot = makeBackComponent(2, "loreRoot");

        TextComponent textComponent = Component.text()
                .append(Component.text("Там где я был - уже нету, такой вот я", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(jackPack)
                .appendNewline()
                .append(loreRoot)
                .build();

        player.sendMessage(textComponent);
    }

    private static void jackLookAnswer(Player player) {

        TextComponent jackPack = makePackBackComponent(1, "jackPack");

        TextComponent loreRoot = makeBackComponent(2, "loreRoot");

        TextComponent textComponent = Component.text()
                .append(Component.text("Как мать родила, так и выгляжу, а вообще перегорел на работе как-то", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(jackPack)
                .appendNewline()
                .append(loreRoot)
                .build();

        player.sendMessage(textComponent);
    }

    private static void powerPack(Player player) {

        TextComponent one = Component.text()
                .append(Component.text("1. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("Один чего?", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("powerOneAnswer"))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();

        TextComponent two = Component.text()
                .append(Component.text("2. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("Два?", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("powerTwoAnswer"))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();


        TextComponent loreRoot = makeBackComponent(3, "loreRoot");

        TextComponent textComponent = Component.text()
                .append(Component.text("Ага, конечно могу! Только у меня одного это займет примерно... Один...", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(one)
                .appendNewline()
                .append(two)
                .appendNewline()
                .append(loreRoot)
                .build();

        player.sendMessage(textComponent);
    }

    private static void powerOneAnswer(Player player) {

        TextComponent cont = Component.text()
                .append(Component.text("1. ", NamedTextColor.DARK_AQUA))
                .append(Component.text("Дальше", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent("powerOneAnswerContinuation"))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();

        TextComponent textComponent = Component.text()
                .append(Component.text("Два...", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(cont)
                .build();

        player.sendMessage(textComponent);
    }

    private static void powerOneAnswerContinuation(Player player) {

        TextComponent loreRoot = makeBackComponent(1, "loreRoot");

        TextComponent textComponent = Component.text()
                .append(Component.text("Двадцать пять лет, если не урезать время которое я трачу на отдых. " +
                        "В мои годы очень важно отдыхать правильно", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(loreRoot)
                .build();

        player.sendMessage(textComponent);
    }

    private static void powerTwoAnswer(Player player) {

        TextComponent loreRoot = makeBackComponent(1, "loreRoot");

        TextComponent textComponent = Component.text()
                .append(Component.text("Да, именно!", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("чтобы перейти дальше, клинки по одному из сообщений ниже", NamedTextColor.DARK_GRAY))
                .appendNewline()
                .append(loreRoot)
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

    private static TextComponent makePackBackComponent(int dialogueNumber, String dialogueName) {
        return Component.text()
                .append(Component.text(dialogueNumber + ". ", NamedTextColor.DARK_AQUA))
                .append(Component.text("У меня есть ещё вопросы", NamedTextColor.GREEN))
                .clickEvent(makeClickEvent(dialogueName))
                .hoverEvent(HoverEvent.showText(Component.text("*клик*")))
                .build();
    }
}
