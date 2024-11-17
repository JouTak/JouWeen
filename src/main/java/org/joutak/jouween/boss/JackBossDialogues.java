package org.joutak.jouween.boss;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.Component;
import org.joutak.jouween.JouWeen;

public class JackBossDialogues {

    public static void do_start_dialogue() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()){
            sayEpicLines(player);
        }
    }

    public static void do_death_dialogue() {
        for (Player player: Bukkit.getServer().getOnlinePlayers()){
            sayDeathLines(player);
        }
    }

    public static void sayEpicLines(Player player) {

        TextComponent firstLine = Component.text()
                .append(Component.text("ХАХАХАХАХАХАХАХАХАХАХАХАХА", NamedTextColor.RED))
                        .build();

        Title firstTitle = Title.title(firstLine, getBlankComponent());

        TextComponent secondLine = Component.text()
                .append(Component.text("Вы такие глупцы!", NamedTextColor.DARK_RED))
                .build();

        TextComponent secondSubLine = Component.text()
                .append(Component.text("Я даже удивлён, что вы смогли зайти так далеко", NamedTextColor.DARK_PURPLE))
                .build();

        Title secondTitle = Title.title(secondLine, secondSubLine);

        TextComponent thirdLine = Component.text()
                .append(Component.text("Я благодарен за вашу помощь...", NamedTextColor.DARK_AQUA))
                .build();

        TextComponent thirdSubLine = Component.text()
                .append(Component.text("Теперь у вас есть одно последнее задание", NamedTextColor.DARK_PURPLE))
                .build();

        Title thirdTitle = Title.title(thirdLine, thirdSubLine);

        TextComponent finalLine = Component.text()
                .append(Component.text("УМРИТЕ!", NamedTextColor.RED))
                .build();

        Title finalTitle = Title.title(finalLine, getBlankComponent());

        player.showTitle(firstTitle);

        Bukkit.getScheduler().scheduleSyncDelayedTask(JouWeen.getInstance(), () -> {
            player.showTitle(secondTitle);
        }, 120);

        Bukkit.getScheduler().scheduleSyncDelayedTask(JouWeen.getInstance(), () -> {
            player.showTitle(thirdTitle);
        }, 240);

        Bukkit.getScheduler().scheduleSyncDelayedTask(JouWeen.getInstance(), () -> {
            player.showTitle(finalTitle);
        }, 360);
    }

    public static void sayDeathLines(Player player) {

        TextComponent firstLine = Component.text()
                .append(Component.text("Кхе... Кхе... Кхе...", NamedTextColor.DARK_RED))
                .build();

        TextComponent firstSubLine = Component.text()
                .append(Component.text("Вы оказались сильнее чем я думал", NamedTextColor.DARK_PURPLE))
                .build();

        Title firstTitle = Title.title(firstLine, firstSubLine);

        TextComponent secondLine = Component.text()
                .append(Component.text("Я ещё отомщу!..", NamedTextColor.RED))
                .build();

        Title secondTitle = Title.title(secondLine, getBlankComponent());

        player.showTitle(firstTitle);

        Bukkit.getScheduler().scheduleSyncDelayedTask(JouWeen.getInstance(), () -> {
            player.showTitle(secondTitle);
        }, 120);
    }

    public static TextComponent getBlankComponent() {
        return Component.text().build();
    }
}
