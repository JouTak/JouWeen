package org.joutak.jouween.jack.quests.BringQuests;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.joutak.jouween.jack.quests.AbstractQuest;

public class EchoShards extends AbstractQuest {

    private static int SHARD_AMOUNT = 8;

    public EchoShards(int id, int weight, int reward) {
        this.id = id;
        this.weight = weight;
        this.reward = reward;
    }

    @Override
    public TextComponent getDescription() {
        return Component.text()
                .append(Component.text("В древних городах должны быть проводники этой заразы," +
                        " без них разобраться в причинах и придумать решение будет тяжело... Принеси мне ", NamedTextColor.DARK_AQUA))
                .append(Component.text(SHARD_AMOUNT, NamedTextColor.GOLD))
                .append(Component.text(" штук осколков эха. Все, не трать моё время на пустую болтовню и иди.", NamedTextColor.DARK_AQUA))
                .build();
    }

    @Override
    public boolean checkQuest(Player player) {

        ItemStack itemStack = player.getInventory().getItemInMainHand();

        try {
            return itemStack.getType().equals(Material.ECHO_SHARD) &&
                    itemStack.getAmount() >= SHARD_AMOUNT;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void completeQuest(Player player) {

        ItemStack itemStack = player.getInventory().getItemInMainHand();

        itemStack.setAmount(itemStack.getAmount() - SHARD_AMOUNT);
    }
}
