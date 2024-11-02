package org.joutak.jouween.jack.quests.BringQuests;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.joutak.jouween.jack.quests.AbstractQuest;

public class PhantomMembranes extends AbstractQuest {

    private static int MEMBRANE_AMOUNT = 3;

    public PhantomMembranes(int id, int weight, int reward) {
        this.id = id;
        this.weight = weight;
        this.reward = reward;
    }

    @Override
    public TextComponent getDescription() {
        return Component.text()
                .append(Component.text("Нет времени объяснять, мне нужны мембраны фантомов. Принеси мне ", NamedTextColor.DARK_AQUA))
                .append(Component.text(MEMBRANE_AMOUNT, NamedTextColor.GOLD))
                .append(Component.text(" 3 штуки. Задание я тебе дал, а теперь беги, Форест, беги!", NamedTextColor.DARK_AQUA))
                .build();
    }

    @Override
    public boolean checkQuest(Player player) {

        ItemStack itemStack = player.getInventory().getItemInMainHand();

        try {
            return itemStack.getType().equals(Material.PHANTOM_MEMBRANE) &&
                    itemStack.getAmount() >= MEMBRANE_AMOUNT;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void completeQuest(Player player) {

        ItemStack itemStack = player.getInventory().getItemInMainHand();

        itemStack.setAmount(itemStack.getAmount() - MEMBRANE_AMOUNT);
    }
}
