package org.joutak.jouween.boss;

import lombok.Data;
import lombok.Getter;
import net.kyori.adventure.audience.ForwardingAudience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.*;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scoreboard.Team;
import org.joutak.jouween.JouWeen;
import org.joutak.jouween.mobs.AllMobTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class JackBoss {

    @Getter
    private static JackBoss instance;

    private WitherSkeleton jackBossWitherSkeleton;

    private ArmorStand jackBossArmorStand;

    private BossBar bossBar;

    private long secondsPassed = 0;

    private boolean isBossActivated = false;

    public JackBoss() {
        instance = this;
    }

    public void start() {
        JackBossData.getInstance().setBossStarted(true);
        JackBossData.getInstance().write();
    }

    public void spawnSittingBoss(){
        World world = Bukkit.getWorld(JackBossData.getInstance().getBossWorldName());

        if (world == null) {
            throw new RuntimeException("WORLD NAME IS INCORRECT!!!!");
        }

        Location location = new Location(world, JackBossData.getInstance().bossLocation.getX(), JackBossData.getInstance().bossLocation.getY(), JackBossData.getInstance().bossLocation.getZ());

        WitherSkeleton witherSkeleton;

        witherSkeleton = (WitherSkeleton) world.spawnEntity(location, EntityType.WITHER_SKELETON, CreatureSpawnEvent.SpawnReason.COMMAND);
        witherSkeleton.getEquipment().setHelmet(new ItemStack(Material.JACK_O_LANTERN));
        witherSkeleton.getEquipment().setItemInOffHand(new ItemStack(Material.SOUL_LANTERN));
        witherSkeleton.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_SWORD));
        witherSkeleton.setAI(false);
        witherSkeleton.setInvulnerable(true);
        witherSkeleton.setRemoveWhenFarAway(false);
        TextComponent textComponent = Component.text("Безголовый Джек", TextColor.color(Color.ORANGE.asRGB()));
        witherSkeleton.customName(textComponent);
        witherSkeleton.setCustomNameVisible(true);
        witherSkeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1200);
        witherSkeleton.setHealth(1200);
        witherSkeleton.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(10);

        ArmorStand armorStand = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND, CreatureSpawnEvent.SpawnReason.COMMAND);
        armorStand.setInvisible(true);
        armorStand.setMarker(true);
        armorStand.setGravity(false);
        armorStand.setCanMove(false);
        armorStand.setInvulnerable(true);
        armorStand.setAI(false);
        armorStand.setRemoveWhenFarAway(false);
        armorStand.addPassenger(witherSkeleton);

        jackBossArmorStand = armorStand;

        jackBossWitherSkeleton = witherSkeleton;
    }

    public void activateBoss() {

    }

    public void spawnWave(){
        Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();

        players.stream()
                .forEach(it -> {
                    Title title = Title.title(Component.text("Земля дрожит...", NamedTextColor.DARK_RED),Component.text("Духи вырываются наружу...", NamedTextColor.DARK_AQUA));
                    it.showTitle(title);

                    int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(JouWeen.getInstance(), () ->{
                        it.playHurtAnimation((float) Math.random()*360);
                        System.out.println("123");
                        it.spawnParticle(Particle.BLOCK, it.getLocation(), 500, 10, 0 , 10, Material.DIRT.createBlockData());
                        it.spawnParticle(Particle.SCULK_SOUL,it.getLocation(),500, 10,0,10);
                        it.spawnParticle(Particle.SCULK_CHARGE_POP,it.getLocation(),500, 10,0,10);
                    },0,20);

                    List<LivingEntity> entities = new ArrayList<>();

                    Bukkit.getScheduler().scheduleSyncDelayedTask(JouWeen.getInstance(), () -> {
                        LivingEntity livingEntity = AllMobTypes.spawnRandomMob(it.getLocation());
                        livingEntity.setAI(false);
                        livingEntity.setInvulnerable(true);
                        entities.add(livingEntity);
                        livingEntity = AllMobTypes.spawnRandomMob(it.getLocation());
                        livingEntity.setAI(false);
                        livingEntity.setInvulnerable(true);
                        entities.add(livingEntity);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(JouWeen.getInstance(), () -> {
                            entities.forEach(entity -> {
                                entity.setInvulnerable(false);
                                entity.setAI(true);
                                Bukkit.getScheduler().cancelTask(taskId);
                            });
                        },100);
                    },100);

                });

    }

    public void spawnCaveMonsters(){

    }

    public void damageBoss() {

    }

    public void onDeath() {

    }

    public void targetSkill() {

    }

    public void radiusSkill() {

    }

    public void summonSkill() {

    }

    public void doBossTick() {

    }

}
