package org.joutak.jouween.jack.quests.BringQuests;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;
import org.joutak.jouween.jack.quests.AbstractQuest;

public class WaterBreathingLingeringPotion extends AbstractQuest {

    public WaterBreathingLingeringPotion(int id, int weight, int reward) {
        this.id = id;
        this.weight = weight;
        this.reward = reward;
    }

    @Override
    public TextComponent getDescription() {
        return Component.text()
                .append(Component.text("Хочу кое-что проверить, принеси мне ", NamedTextColor.DARK_AQUA))
                .append(Component.text("оседающее зелье подводного дыхания", NamedTextColor.GOLD))
                .append(Component.text(". Всё, давай, без лишних вопросов - просто иди и принеси мне зелье.", NamedTextColor.DARK_AQUA))
                .build();
    }

    @Override
    public boolean checkQuest(Player player) {

        ItemStack itemStack = player.getInventory().getItemInMainHand();

        if (!(itemStack.getType().equals(Material.LINGERING_POTION))) {
            return false;
        }

        PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();

        try {
            if (!(potionMeta.getBasePotionType().equals(PotionType.WATER_BREATHING))) {
                return false;
            }
        }
        catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public void completeQuest(Player player) {

        ItemStack itemStack = player.getInventory().getItemInMainHand();

        itemStack.setAmount(0);
    }
}
