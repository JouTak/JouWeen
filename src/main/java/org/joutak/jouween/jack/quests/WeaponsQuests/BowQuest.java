package org.joutak.jouween.jack.quests.WeaponsQuests;

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
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.joutak.jouween.config.JouWeenConfig;
import org.joutak.jouween.jack.data.JackData;
import org.joutak.jouween.jack.quests.AbstractQuest;

import java.util.List;

public class BowQuest extends AbstractQuest {

    int x;
    int y;
    int z;

    public BowQuest(int id, int weight, int reward) {
        this.id = id;
        this.weight = weight;
        this.reward = reward;
        this.x = JackData.getInstance().getBowX();
        this.y = JackData.getInstance().getBowY();
        this.z = JackData.getInstance().getBowZ();
    }

    @Override
    public TextComponent getDescription() {
        return Component.text()
                .append(Component.text("Уххх... Я чувствую, что пора готовиться к битве. Нам еще предстоит понять с чем, " +
                        "но, думаю, что надо запастись оружием. Принеси мне пожалуйста целый ", NamedTextColor.DARK_AQUA))
                .append(Component.text("лук", NamedTextColor.LIGHT_PURPLE))
                .append(Component.text(" без зачарований. 1 штуку.", NamedTextColor.DARK_AQUA))
                .build();
    }

    @Override
    public boolean checkQuest(Player player) {

        ItemStack playerItem = player.getInventory().getItemInMainHand();

        if (!playerItem.getType().equals(Material.BOW)){
            return false;
        }

        ItemMeta itemMeta = playerItem.getItemMeta();
        Damageable damageable = (Damageable) playerItem.getItemMeta();

        if (!(itemMeta.getEnchants().isEmpty()&&!damageable.hasDamage())){
            return false;
        }

        Block block = Bukkit.getWorld(JouWeenConfig.getInstance().getWorldName()).getBlockAt(x,y,z);

        if (!(block.getType().equals(Material.SHULKER_BOX))){
            player.sendMessage("Срочно скажи лапитанию проверить сундуки");
            return false;
        }

        ShulkerBox shulkerBox = (ShulkerBox) block.getState();

        if (shulkerBox.getInventory().contains(Material.BOW, 27)){
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
        ItemMeta itemMeta = chestItem.getItemMeta();
        itemMeta.lore(List.of(Component.text("Сделано ручками " + player.getName())));
        itemMeta.addEnchant(Enchantment.POWER,1,false);
        itemMeta.addEnchant(Enchantment.UNBREAKING,1,false);
        chestItem.setItemMeta(itemMeta);
        shulkerBox.getInventory().addItem(chestItem);
    }
}
