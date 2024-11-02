package org.joutak.jouween.jack.quests.ArmorQuests;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.ShulkerBox;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.Damageable;
import org.joutak.jouween.config.JouWeenConfig;
import org.joutak.jouween.jack.data.JackData;
import org.joutak.jouween.jack.quests.AbstractQuest;

import java.util.List;

public class DiamondLeggingsQuest extends AbstractQuest {

    int x;
    int y;
    int z;

    public DiamondLeggingsQuest(int id, int weight, int reward) {
        this.id = id;
        this.weight = weight;
        this.reward = reward;
        this.x = JackData.getInstance().getPantsX();
        this.y = JackData.getInstance().getPantsY();
        this.z = JackData.getInstance().getPantsZ();
    }

    @Override
    public TextComponent getDescription() {
        return Component.text()
                .append(Component.text("Уххх... Я чувствую, что пора готовиться к битве. Нам еще предстоит понять с чем, " +
                        "но, думаю, что надо запастись броней. Принеси мне пожалуйста целые ", NamedTextColor.DARK_AQUA))
                .append(Component.text("алмазные поножи", NamedTextColor.LIGHT_PURPLE))
                .append(Component.text(" без зачарований. 1 штуку."))
                .build();
    }

    @Override
    public boolean checkQuest(Player player) {

        ItemStack playerItem = player.getInventory().getItemInMainHand();

        if (!playerItem.getType().equals(Material.DIAMOND_LEGGINGS)){
            return false;
        }

        ArmorMeta armorMeta = (ArmorMeta) playerItem.getItemMeta();
        Damageable damageable = (Damageable) playerItem.getItemMeta();

        if (!(armorMeta.getEnchants().isEmpty()&&!damageable.hasDamage())){
            return false;
        }

        Block block = Bukkit.getWorld(JouWeenConfig.getInstance().getWorldName()).getBlockAt(x,y,z);

        if (!(block.getType().equals(Material.SHULKER_BOX))){
            player.sendMessage("Срочно скажи лапитанию проверить сундуки");
            return false;
        }

        ShulkerBox shulkerBox = (ShulkerBox) block.getState();

        if (shulkerBox.getInventory().contains(Material.DIAMOND_LEGGINGS, 27)){
            player.sendMessage("Срочно скажи лапитанию проверить сундуки");
            return false;
        }

        return true;
    }

    @Override
    public void completeQuest(Player player) {

        ItemStack playerItem = player.getInventory().getItemInMainHand();
        ItemStack chestItem = playerItem.clone();

        Block block = Bukkit.getWorld(JouWeenConfig.getInstance().getWorldName()).getBlockAt(x,y,z);

        ShulkerBox shulkerBox = (ShulkerBox) block.getState();

        playerItem.setAmount(0);
        ArmorMeta armorMeta = (ArmorMeta) chestItem.getItemMeta();
        armorMeta.lore(List.of(Component.text("Сделано ручками " + player.getName())));
        armorMeta.addEnchant(Enchantment.PROTECTION,1,false);
        armorMeta.addEnchant(Enchantment.UNBREAKING,1,false);
        chestItem.setItemMeta(armorMeta);
        shulkerBox.getInventory().addItem(chestItem);
    }
}
