package org.joutak.jouween.jack.quests.BringQuests;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.joutak.jouween.jack.quests.AbstractQuest;

public class Torchlight extends AbstractQuest {

    private static int TORCHLIGHT_AMOUNT = 1;

    public Torchlight(int id, int weight, int reward) {
        this.id = id;
        this.weight = weight;
        this.reward = reward;
    }

    @Override
    public TextComponent getDescription() {
        return Component.text()
                .append(Component.text("Зараза древняя, надо узнать как древние существа противостояли им. Принеси мне ", NamedTextColor.DARK_AQUA))
                .append(Component.text(TORCHLIGHT_AMOUNT, NamedTextColor.GOLD))
                .append(Component.text(" факельник.", NamedTextColor.DARK_AQUA))
                .build();
    }

    @Override
    public boolean checkQuest(Player player) {

        ItemStack itemStack = player.getInventory().getItemInMainHand();

        try {
            return itemStack.getType().equals(Material.TORCHFLOWER) &&
                    itemStack.getAmount() >= TORCHLIGHT_AMOUNT;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void completeQuest(Player player) {

        ItemStack itemStack = player.getInventory().getItemInMainHand();

        itemStack.setAmount(itemStack.getAmount() - TORCHLIGHT_AMOUNT);
    }
}
