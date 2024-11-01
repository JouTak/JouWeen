package org.joutak.jouween.jack.quests.BottleQuests;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.joutak.jouween.jack.quests.AbstractQuest;
import org.joutak.jouween.mobs.AllMobTypes;

public class StrongZombieBottles extends AbstractQuest {

    private static int BOTTLES_AMOUNT = 2;

    public StrongZombieBottles(int id, int weight, int reward) {
        this.id = id;
        this.weight = weight;
        this.reward = reward;
    }

    @Override
    public TextComponent getDescription() {
        return Component.text()
                .append(Component.text("Нужны сильные гнилые мышцы... Мне нужно, чтобы ты принес мне эссенцию сильных зомби в количестве ", NamedTextColor.DARK_AQUA))
                .append(Component.text(BOTTLES_AMOUNT, NamedTextColor.GOLD))
                .append(Component.text(" штук. Желаю удачи... Своим топором они тебе и черепушку расколоть могут.", NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text(" Жаль быть тобой) Ведь у меня черепушки...", NamedTextColor.DARK_AQUA))
                .append(Component.text(" НЕТУ!!!!! XDXDXD", NamedTextColor.RED))
                .build();
    }

    @Override
    public boolean checkQuest(Player player) {
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        try {
            return itemStack.getType().equals(Material.GLASS_BOTTLE) &&
                    itemStack.getAmount() >= BOTTLES_AMOUNT &&
                    itemStack.getItemMeta().getCustomModelData() == 52 &&
                    itemStack.getItemMeta().hasLore() &&
                    ((TextComponent) itemStack.getItemMeta().displayName()).content()
                            .contains(AllMobTypes.getCustomMobById(8).getMobName());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void completeQuest(Player player) {
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        itemStack.setAmount(itemStack.getAmount() - BOTTLES_AMOUNT);
    }

}
