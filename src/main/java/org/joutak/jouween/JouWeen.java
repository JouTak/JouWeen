package org.joutak.jouween;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.joutak.jouween.commands.JackDialogueCommands;
import org.joutak.jouween.commands.JouWeenCommands;
import org.joutak.jouween.config.JouWeenConfig;
import org.joutak.jouween.jack.Jack;
import org.joutak.jouween.jack.data.JackData;
import org.joutak.jouween.jack.events.JackPlayerInteractEvent;
import org.joutak.jouween.jack.quests.AllQuests;
import org.joutak.jouween.jack.quests.ArmorQuests.DiamondBootsQuest;
import org.joutak.jouween.jack.quests.ArmorQuests.DiamondChestPlateQuest;
import org.joutak.jouween.jack.quests.ArmorQuests.DiamondHelmetQuest;
import org.joutak.jouween.jack.quests.ArmorQuests.DiamondLeggingsQuest;
import org.joutak.jouween.jack.quests.BottleQuests.*;
import org.joutak.jouween.jack.quests.BringQuests.EchoShards;
import org.joutak.jouween.jack.quests.BringQuests.PhantomMembranes;
import org.joutak.jouween.jack.quests.BringQuests.Torchlight;
import org.joutak.jouween.jack.quests.WeaponsQuests.ArrowsQuest;
import org.joutak.jouween.jack.quests.WeaponsQuests.BowQuest;
import org.joutak.jouween.jack.quests.WeaponsQuests.BreadQuest;
import org.joutak.jouween.jack.quests.WeaponsQuests.DiamondSwordQuest;
import org.joutak.jouween.jack.quests.specialQuests.CompleteRaidQuest;
import org.joutak.jouween.jack.quests.specialQuests.RaidCompleteEvent;
import org.joutak.jouween.mobs.AllMobTypes;
import org.joutak.jouween.mobs.CustomMobsKiller;
import org.joutak.jouween.mobs.event.*;
import org.joutak.jouween.mobs.mob.*;
import org.joutak.jouween.moon.MoonPlayerJoinEvent;
import org.joutak.jouween.moon.MoonSwitcher;

public final class JouWeen extends JavaPlugin {

    @Getter
    private static JouWeen instance;

    @Getter
    private static float sculkMobSpawnPercent = 0.25F;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        Bukkit.getPluginManager().registerEvents(new MobPhaseMobSpawnEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MobPhaseEvokerEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MobPhasePlayerKillEntityEventHandler(), this);
        Bukkit.getPluginManager().registerEvents(new MobPhasePlayerHitEntityEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MobPhasePlayerInteractWithEntity(), this);
        Bukkit.getPluginManager().registerEvents(new JackPlayerInteractEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MoonPlayerJoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MobPhaseEntityKillsEntityEvent(), this);
        Bukkit.getPluginManager().registerEvents(new RaidCompleteEvent(), this);
        Bukkit.getPluginManager().registerEvents(new KillSkeletonOnHorseEvent(), this);

        new JouWeenCommands();
        new JackDialogueCommands();

        new JackData().read();
        new JouWeenConfig().read();
        new MoonSwitcher().register();
        new CustomMobsKiller().registerMobKiller();

        addMobs();
        addQuests();

        doAfterStart();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void addMobs() {
        AllMobTypes.addMob(new NormalZombie(1000, 5));
        AllMobTypes.addMob(new WitchOnPhantom(1, 12));
        AllMobTypes.addMob(new SculkEvoker(20, 11));
        AllMobTypes.addMob(new FastZombie(200, 7));
        AllMobTypes.addMob(new StrongZombie(200, 8));
        AllMobTypes.addMob(new SkeletonOnHorse(100, 9));
        AllMobTypes.addMob(new GuardianOnBat(500, 10));
        AllMobTypes.addMob(new NormalSkeleton(1000, 6));
    }

    private void addQuests() {

        // bottles
        AllQuests.addQuest(new NormalZombieBottles(1, 100, 1));
        AllQuests.addQuest(new NormalSkeletonsBottles(2, 100, 1));
        AllQuests.addQuest(new FastZombieBottles(3, 100, 1));
        AllQuests.addQuest(new EyeBottles(4, 100, 1));
        AllQuests.addQuest(new EvokerBottles(5, 100, 1));
        AllQuests.addQuest(new SkeletonOnHorseBottles(6, 100, 1));
        AllQuests.addQuest(new StrongZombieBottles(7, 100, 1));

        // armor
        AllQuests.addQuest(new DiamondHelmetQuest(8, 100, 1));
        AllQuests.addQuest(new DiamondChestPlateQuest(9, 100, 1));
        AllQuests.addQuest(new DiamondLeggingsQuest(10, 100, 1));
        AllQuests.addQuest(new DiamondBootsQuest(11, 100, 1));

        // special
        AllQuests.addQuest(new CompleteRaidQuest(12, 50, 2));

        // fight equipment
        AllQuests.addQuest(new DiamondSwordQuest(13, 100, 1));
        AllQuests.addQuest(new BowQuest(14, 100, 1));
        AllQuests.addQuest(new BreadQuest(15, 100, 1));
        AllQuests.addQuest(new ArrowsQuest(19, 100, 1));

        // bring quests
        AllQuests.addQuest(new EchoShards(16, 100, 1));
        AllQuests.addQuest(new PhantomMembranes(17, 100, 1));
        AllQuests.addQuest(new Torchlight(18, 100, 1));
    }

    private void doAfterStart() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, Jack::create);
    }

}
