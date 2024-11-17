package org.joutak.jouween.boss;

import lombok.Data;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.*;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.joutak.jouween.JouWeen;
import org.joutak.jouween.Utils;
import org.joutak.jouween.boss.skillsets.CurrSkillSet;
import org.joutak.jouween.boss.skillsets.FirstSkillSet;
import org.joutak.jouween.mobs.AllMobTypes;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Data
public class JackBoss {

    @Getter
    private static JackBoss instance;

    private WitherSkeleton jackBossWitherSkeleton;

    private ArmorStand jackBossArmorStand;

    private BossBar bossBar;

    private long secondsPassed = 0;

    private boolean bossActivated = false;

    private CurrSkillSet skillSet = new FirstSkillSet();

    int bossTickId = 0;

    public JackBoss() {
        instance = this;
    }

    public void start() {
        JackBossData.getInstance().setBossStarted(true);
        spawnSittingBoss();
        JackBossData.getInstance().write();

        Bukkit.getOnlinePlayers().forEach(player -> {
            if (player.getName().equals("lapitaniy") || player.getName().equals("Solus_asc")) {
                return;
            }
            player.setGameMode(GameMode.ADVENTURE);
        });
        Bukkit.getWorld(JackBossData.getInstance().getBossWorldName()).setGameRule(GameRule.DO_MOB_SPAWNING, false);
        Bukkit.getWorld(JackBossData.getInstance().getBossWorldName()).setGameRule(GameRule.KEEP_INVENTORY, true);
        Bukkit.getWorld(JackBossData.getInstance().getBossWorldName()).setGameRule(GameRule.MOB_GRIEFING, false);
        doBossTick();
    }

    public void spawnSittingBoss() {
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
        witherSkeleton.setGravity(false);
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
        jackBossArmorStand.removePassenger(jackBossWitherSkeleton);
        jackBossArmorStand.setHealth(0);
        bossBar = Bukkit.createBossBar("Безголовый Джек", BarColor.RED, BarStyle.SEGMENTED_6);
        bossBar.setVisible(true);
        bossBar.setProgress(0);

        Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();

        players.stream()
                .forEach(it -> {
                    bossBar.addPlayer(it);
                    JackBossDialogues.sayEpicLines(it);

                    int i = 3;

                    int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(JouWeen.getInstance(), () -> {
                        it.playHurtAnimation((float) Math.random() * 360);
                        it.spawnParticle(Particle.BLOCK, it.getLocation(), 100, 10, 0, 10, Material.SCULK.createBlockData());
                        it.spawnParticle(Particle.SCULK_SOUL, it.getLocation(), 100, 10, 0, 10);
                        it.spawnParticle(Particle.SCULK_CHARGE_POP, it.getLocation(), 100, 10, 0, 10);
                    }, 0, 5);

                    Bukkit.getScheduler().scheduleSyncDelayedTask(JouWeen.getInstance(), () -> {
                        it.spawnParticle(Particle.SCULK_SOUL, jackBossWitherSkeleton.getLocation(), 1000, 10, 10, 10);
                        it.spawnParticle(Particle.SOUL_FIRE_FLAME, jackBossWitherSkeleton.getLocation(), 1000, 10, 10, 10);
                        it.playSound(jackBossWitherSkeleton, Sound.ENTITY_WITHER_SPAWN, 5, 1);

                        bossBar.setProgress((double) 1 / 6);
                    }, ++i * 120);

                    Bukkit.getScheduler().scheduleSyncDelayedTask(JouWeen.getInstance(), () -> {
                        it.spawnParticle(Particle.SCULK_SOUL, jackBossWitherSkeleton.getLocation(), 1000, 8, 8, 8);
                        it.spawnParticle(Particle.SOUL_FIRE_FLAME, jackBossWitherSkeleton.getLocation(), 1000, 8, 8, 8);
                        it.playSound(jackBossWitherSkeleton, Sound.ENTITY_WITHER_SPAWN, 5, 1);

                        bossBar.setProgress((double) 2 / 6);
                    }, ++i * 120);

                    Bukkit.getScheduler().scheduleSyncDelayedTask(JouWeen.getInstance(), () -> {
                        it.spawnParticle(Particle.SCULK_SOUL, jackBossWitherSkeleton.getLocation(), 1000, 6, 6, 6);
                        it.spawnParticle(Particle.SOUL_FIRE_FLAME, jackBossWitherSkeleton.getLocation(), 1000, 6, 6, 6);
                        it.playSound(jackBossWitherSkeleton, Sound.ENTITY_WITHER_SPAWN, 5, 1);

                        bossBar.setProgress((double) 3 / 6);
                    }, ++i * 120);

                    Bukkit.getScheduler().scheduleSyncDelayedTask(JouWeen.getInstance(), () -> {
                        it.spawnParticle(Particle.SCULK_SOUL, jackBossWitherSkeleton.getLocation(), 1000, 4, 4, 4);
                        it.spawnParticle(Particle.SOUL_FIRE_FLAME, jackBossWitherSkeleton.getLocation(), 1000, 4, 4, 4);
                        it.playSound(jackBossWitherSkeleton, Sound.ENTITY_WITHER_SPAWN, 5, 1);

                        bossBar.setProgress((double) 4 / 6);
                    }, ++i * 120);

                    Bukkit.getScheduler().scheduleSyncDelayedTask(JouWeen.getInstance(), () -> {
                        it.spawnParticle(Particle.SCULK_SOUL, jackBossWitherSkeleton.getLocation(), 1000, 2, 2, 2);
                        it.spawnParticle(Particle.SOUL_FIRE_FLAME, jackBossWitherSkeleton.getLocation(), 1000, 2, 2, 2);
                        it.playSound(jackBossWitherSkeleton, Sound.ENTITY_WITHER_SPAWN, 5, 1);

                        bossBar.setProgress((double) 5 / 6);
                    }, ++i * 120);

                    Bukkit.getScheduler().scheduleSyncDelayedTask(JouWeen.getInstance(), () -> {
                        it.spawnParticle(Particle.SCULK_SOUL, jackBossWitherSkeleton.getLocation(), 1000, 20, 20, 20);
                        it.spawnParticle(Particle.SOUL_FIRE_FLAME, jackBossWitherSkeleton.getLocation(), 1000, 20, 20, 20);
                        it.playSound(jackBossWitherSkeleton, Sound.ENTITY_WITHER_DEATH, 5, 1);

                        bossBar.setProgress(1);
                        Bukkit.getScheduler().cancelTask(taskId);
                        jackBossWitherSkeleton.setAI(true);
                        jackBossWitherSkeleton.setGravity(true);
                        jackBossWitherSkeleton.setInvulnerable(false);
                        jackBossWitherSkeleton.setGlowing(false);
                        bossBar.setColor(BarColor.YELLOW);
                        bossActivated = true;
                    }, ++i * 120);
                    jackBossWitherSkeleton.setGlowing(true);

                    Bukkit.getScheduler().scheduleSyncDelayedTask(JouWeen.getInstance(), () -> {
                        Location location = jackBossWitherSkeleton.getLocation();
                        location.setY(location.getY() + 5);
                        jackBossWitherSkeleton.teleport(location);
                    }, 360);

                });

    }

    public void spawnWave() {
        Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();

        players.stream()
                .forEach(it -> {
                    Title title = Title.title(Component.text("Земля дрожит...", NamedTextColor.DARK_RED), Component.text("Духи вырываются наружу...", NamedTextColor.DARK_AQUA));
                    it.showTitle(title);

                    int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(JouWeen.getInstance(), () -> {
                        it.playHurtAnimation((float) Math.random() * 360);
                        it.spawnParticle(Particle.BLOCK, it.getLocation(), 500, 10, 0, 10, Material.DIRT.createBlockData());
                        it.spawnParticle(Particle.SCULK_SOUL, it.getLocation(), 500, 10, 0, 10);
                        it.spawnParticle(Particle.SCULK_CHARGE_POP, it.getLocation(), 500, 10, 0, 10);
                    }, 0, 20);

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
                        }, 100);
                    }, 100);

                });

    }

    public void spawnCaveMonsters() {
        Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
        List<XYZLocation> locations = JackBossData.getInstance().mobSpawnLocations;
        AtomicBoolean activated = new AtomicBoolean(false);
        players.forEach(player -> {
            locations.stream()
                    .filter(entry -> !entry.isUsed()) // Фильтруем только не занятые локации
                    .map(entry -> {
                        double distance = Utils.getDistance(player.getLocation(),
                                Bukkit.getWorld(JackBossData.getInstance().getBossWorldName()).getBlockAt(
                                        (int) entry.getX(),
                                        (int) entry.getY(),
                                        (int) entry.getZ()).getLocation());
                        return new AbstractMap.SimpleEntry<>(distance, entry);
                    })
                    .filter(entry -> entry.getKey() <= 10)
                    .min(Map.Entry.comparingByKey())
                    .map(Map.Entry::getValue)
                    .ifPresent(closestEntry -> {
                        closestEntry.setUsed(true);
                        for (byte i = 0; i < 10; i++) {
                            AllMobTypes.spawnRandomMob(new Location(
                                    Bukkit.getWorld(JackBossData.getInstance().getBossWorldName()),
                                    closestEntry.getX(),
                                    closestEntry.getY(),
                                    closestEntry.getZ()));
                        }
                        activated.set(true);
                    });
        });
    }

    public void damageBoss() {
        bossBar.setProgress(jackBossWitherSkeleton.getHealth() / Objects.requireNonNull(jackBossWitherSkeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue());

        if (jackBossWitherSkeleton.getHealth() == Objects.requireNonNull(jackBossWitherSkeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue())
            return;

        if (Math.floor(jackBossWitherSkeleton.getHealth() / (jackBossWitherSkeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() / 6)) > skillSet.getCurrStageNumb()) {
            skillSet = skillSet.shiftBackwards();
        }
        if (Math.floor(jackBossWitherSkeleton.getHealth() / (jackBossWitherSkeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() / 6)) < skillSet.getCurrStageNumb()) {
            skillSet = skillSet.shiftForward();
            jackBossWitherSkeleton.setGlowing(true);
            jackBossWitherSkeleton.setInvulnerable(true);
            bossBar.setColor(BarColor.RED);
            summonSkill();
        }

        if (jackBossWitherSkeleton.getHealth() <= 0 || jackBossWitherSkeleton.isDead()) {
            onDeath();
        }
    }

    public void onDeath() {
        JackBossDialogues.do_death_dialogue();
        bossBar.removeAll();
        Bukkit.getScheduler().cancelTask(bossTickId);
        Bukkit.getScheduler().scheduleSyncDelayedTask(JouWeen.getInstance(), () -> {
            Bukkit.getServer().dispatchCommand(JouWeen.getInstance().getServer().getConsoleSender(),"give @a written_book[written_book_content={pages:['[[\"\",{\"text\":\"Глава 1: \\\\\"В ожидании полной луны\\\\\"\",\"bold\":true},\"\\\\nЯ стою на грани между мирами, ощущая, как гниющие пальцы духов цепляются за реальность. Эти беспокойные души рвутся на свободу, жаждая мести и… освобождения.\"]]','[[\"Священники, конечно, почувствовали эти потрясения. Их тревога как музыка, предвещающая бурю. Они видят сны о лунном свете, видят меня в своих ночных кошмарах, но что \",{\"text\":\"они\",\"italic\":true},\" могут сделать? Эти старики и их молитвы – пустая трата времени.\"]]','[[\"Сила тьмы растет, но я только начинаю. Мобов, ставших сильнее под гнетом злых духов, все больше, и я их создатель.\\\\n\",{\"text\":\"Они \",\"bold\":true},\"– мои посланники, хаос, посеянный ради великой цели. \"]]','[[\"Кто бы знал, что бедные души будут становиться моими орудиями, а их дымка – моим ресурсом.\"]]','[[\"\",{\"text\":\"Глава 2: \\\\\"Загадка для смертных\\\\\"\",\"bold\":true},\"\\\\nЯ появился среди смертных, лицом к лицу с ними, притворялся союзником. \\\\\"\",{\"text\":\"Выполняйте мои задания,\",\"italic\":true},\"\\\\\" — говорил я им, — \\\\\"\",{\"text\":\"и всё вернётся на круги своя\",\"italic\":true},\"\\\\\". И глупцы поверили.\"]]','[[\"Они собирают для меня силы убитых ими, приближая каждой капле прибавляя мне сил, не зная, что каждое выполненное задание приближает их к той самой судьбе, от которой они пытаются бежать.\"]]','[[\"Священники смотрят на \",{\"text\":\"луну\",\"color\":\"dark_aqua\"},\", будто ищут ответы, которых у них нет. Они чувствуют, что \",{\"text\":\"что-то\",\"color\":\"dark_aqua\"},\" не так, но у них нет ни знаний, ни смелости меня остановить.\"]]','[[\"\",{\"text\":\"Глава 3: \\\\\"Когда дымка превращается в силу\\\\\"\",\"bold\":true},\"\\\\n Прошло время, и я получил почти достаточно. Моё терпение заканчивается, а потребность в большем не угасает... \\\\nЯ напомнил игрокам: нужно торопиться, иначе их ожидает нечто страшное. \"]]','[[\" Они ускорились, как муравьи, спешащие к своей гибели, их ноги запутались в паутине моих обманов.\\\\n\\\\n Каждое их действие, каждый собранный бутылек – это камень в фундамент моей силы и их погибели.\"]]','[[\"\",{\"text\":\"Луна \",\"color\":\"dark_aqua\"},\"зловеще светит, предупреждая о надвигающейся беде, но люди видят лишь слабый свет.\"]]','[[\"\",{\"text\":\"Глава 4: \\\\\"Нечаянная потеря и паника\\\\\"\",\"bold\":true},\"\\\\n\\\\n Когда я собрал всё, что мне нужно, я ушел, оставив след хаоса. Они заметили, что я исчез, но забыли, что страх – лучшее наследие. В панике, они нашли \",{\"text\":\"компас\",\"italic\":true},\", выпавший из моего кармана.\"]]','[[\" О, \",{\"text\":\"как глупо думать\",\"bold\":true,\"italic\":true},\", что этот инструмент может их спасти. Он всего лишь лишняя подсказка к тому, что я не тот, за кого меня принимают. Как давно я тянул за ниточки этих людей, как давно священники не могут разобраться в моей игре.\"]]','[[\"\",{\"text\":\" Глава 5: \\\\\"Игра окончена, но до новой встречи\\\\\"\",\"bold\":true},\"\\\\nКогда они наконец доберутся до моего последнего следа, найдут мою записную книжку – ах, как я представляю их лица, когда они начнут читать!\"]]','[[\"Они так старались, собирали дымку, выполняли задания, стремились понять, что происходит, – и сделали всё именно так, как я задумал. Как же мне было весело наблюдать за их наивными попытками меня перехитрить!\"]]','[[\"Я оставил им несколько слов на прощание но не для того, чтобы пугать, нет – скорее, чтобы они ждали моего возвращения.\\\\nВедь такие захватывающие игры заслуживают продолжения, \",{\"text\":\"не так ли? \",\"italic\":true,\"bold\":true}]]','[[\"Когда придет время, я снова появлюсь в их мире. И снова начнется новый виток нашего веселья\\\\n\\\\nТак что прощайте, но ненадолго. Я скоро вернусь, и тогда мы продолжим.\\\\n\\\\n\\\\nДо скорой встречи,\\\\n                      \",{\"text\":\"Джек\",\"color\":\"dark_aqua\"}]]'],title:\"Души и туши\",author:\"Джек\",generation:3},custom_name='{\"text\":\"Души и туши\",\"color\":\"dark_purple\",\"italic\":false}',lore=['{\"text\":\"Книга того, кто играл\",\"color\":\"dark_aqua\",\"italic\":true}','{\"text\":\"Для тех с кем он играл\",\"color\":\"dark_aqua\",\"italic\":true}','{\"text\":\"Запомни\",\"color\":\"dark_aqua\",\"italic\":true}','{\"text\":\"И жди\",\"color\":\"dark_aqua\",\"italic\":true}']]");
        }, 240);
    }

    public void targetSkill() {
        skillSet.targetSkill();
    }

    public void radiusSkill() {
        skillSet.radiusSkill();
    }

    public void summonSkill() {
        skillSet.summonSkill();
    }

    public void doBossTick() {
        bossTickId = Bukkit.getScheduler().scheduleSyncRepeatingTask(JouWeen.getInstance(), () -> {
            if (bossActivated){
                if (secondsPassed%7==0) targetSkill();
                if (secondsPassed%15==0) radiusSkill();
                checkInvulnerable();
            }

            spawnCaveMonsters();
            secondsPassed++;

        },0,20);
    }

    public void checkInvulnerable() {
        if (!Bukkit.getWorld(JackBossData.getInstance().bossWorldName).getEntities()
                .stream().filter(it -> !it.getType().equals(EntityType.PLAYER))
                .filter(it -> !it.getType().equals(EntityType.WITHER_SKELETON))
                .filter(it -> it instanceof LivingEntity)
                .map(it -> (LivingEntity) it)
                .filter(it -> it.getPotionEffect(PotionEffectType.NIGHT_VISION)!=null)
                .anyMatch(it -> it.getPotionEffect(PotionEffectType.NIGHT_VISION).getAmplifier()>5)){
            jackBossWitherSkeleton.setGlowing(false);
            jackBossWitherSkeleton.setInvulnerable(false);
            bossBar.setColor(BarColor.YELLOW);
        };
    }

}
