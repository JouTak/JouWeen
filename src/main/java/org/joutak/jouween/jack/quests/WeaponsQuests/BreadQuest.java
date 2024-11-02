package org.joutak.jouween.jack.quests.WeaponsQuests;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.joutak.jouween.config.JouWeenConfig;
import org.joutak.jouween.jack.data.JackData;
import org.joutak.jouween.jack.quests.AbstractQuest;

public class BreadQuest extends AbstractQuest {

    private static int BREAD_AMOUNT = 64;
    int x;
    int y;
    int z;

    public BreadQuest(int id, int weight, int reward) {
        this.id = id;
        this.weight = weight;
        this.reward = reward;
        this.x = JackData.getInstance().getBreadX();
        this.y = JackData.getInstance().getBreadY();
        this.z = JackData.getInstance().getBreadZ();
    }

    @Override
    public TextComponent getDescription() {
        return Component.text()
                .append(Component.text("Короче, игрок, с надвигающимся боем я тебе помогу, но в благородство играть не буду" +
                        " - принеси мне ", NamedTextColor.DARK_AQUA))
                .append(Component.text("64 хлеба", NamedTextColor.LIGHT_PURPLE))
                .append(Component.text(" и будем в расчёте", NamedTextColor.DARK_AQUA))
                .build();
    }

    @Override
    public boolean checkQuest(Player player) {

        ItemStack playerItem = player.getInventory().getItemInMainHand();

        if (!playerItem.getType().equals(Material.BREAD) || playerItem.getAmount() < BREAD_AMOUNT){
            return false;
        }

        Block block = Bukkit.getWorld(JouWeenConfig.getInstance().getWorldName()).getBlockAt(x,y,z);

        if (!(block.getType().equals(Material.SHULKER_BOX))){
            player.sendMessage("Срочно скажи лапитанию проверить сундуки");
            return false;
        }

        ShulkerBox shulkerBox = (ShulkerBox) block.getState();

        if (shulkerBox.getInventory().contains(Material.BREAD, 27)){
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
        shulkerBox.getInventory().addItem(chestItem);
    }
}
