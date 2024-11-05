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


public class ArrowsQuest extends AbstractQuest {

    private static int ARROWS_AMOUNT = 64;
    int x;
    int y;
    int z;

    public ArrowsQuest(int id, int weight, int reward) {
        this.id = id;
        this.weight = weight;
        this.reward = reward;
        this.x = JackData.getInstance().getArrowsX();
        this.y = JackData.getInstance().getArrowsY();
        this.z = JackData.getInstance().getArrowsZ();
    }

    @Override
    public TextComponent getDescription() {
        return Component.text()
                .append(Component.text("Уххх... Я чувствую, что пора готовиться к битве. Нам еще предстоит понять с чем, " +
                        "но, думаю, что надо запастись оружием. Принеси мне пожалуйста ", NamedTextColor.DARK_AQUA))
                .append(Component.text("64 стрелы", NamedTextColor.LIGHT_PURPLE))
                .append(Component.text(". Самых обычных, без каких-либо эффектов", NamedTextColor.DARK_AQUA))
                .build();
    }

    @Override
    public boolean checkQuest(Player player) {

        ItemStack playerItem = player.getInventory().getItemInMainHand();

        if (!playerItem.getType().equals(Material.ARROW) || playerItem.getAmount() < ARROWS_AMOUNT){
            return false;
        }

        Block block = Bukkit.getWorld(JouWeenConfig.getInstance().getWorldName()).getBlockAt(x,y,z);

        if (!(block.getType().equals(Material.SHULKER_BOX))){
            player.sendMessage("Срочно скажи лапитанию проверить сундуки");
            return false;
        }

        ShulkerBox shulkerBox = (ShulkerBox) block.getState();

        if (shulkerBox.getInventory().contains(Material.ARROW, 27 * 64)){
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
