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
import org.joutak.jouween.jack.quests.NormalZombieBottles;
import org.joutak.jouween.mobs.event.*;
import org.joutak.jouween.mobs.AllMobTypes;
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

        new JouWeenCommands();
        new JackDialogueCommands();

        addMobs();
        addQuests();
        new JackData().read();
        new JouWeenConfig().read();
        new MoonSwitcher().register();

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
        AllQuests.addQuest(new NormalZombieBottles(1,100, 1));
    }

    private void doAfterStart(){
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, Jack::create);
    }

}
