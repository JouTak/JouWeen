package org.joutak.jouween.commands;

import net.kyori.adventure.title.Title;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.joutak.jouween.boss.JackBoss;
import org.joutak.jouween.boss.JackBossData;
import org.joutak.jouween.boss.XYZLocation;
import org.joutak.jouween.config.JouWeenConfig;
import org.joutak.jouween.config.JouweenConst;
import org.joutak.jouween.jack.Jack;
import org.joutak.jouween.jack.data.JackData;
import org.joutak.jouween.jack.files.JackFileWriter;
import org.joutak.jouween.mobs.AllMobTypes;
import org.joutak.jouween.mobs.CustomMobsKiller;

public class JouWeenCommands extends AbstractCommand {

    public JouWeenCommands() {
        super("jouween");
    }

    @Override
    public void execute(CommandSender commandSender, Command command, String string, String[] args) {

        if (!(commandSender instanceof Player)) {
            return;
        }

        Player playerSender = (Player) commandSender;

        if (!(playerSender.getName().equals("lapitaniy") || playerSender.getName().equals("Solus_asc"))) {
            playerSender.sendMessage("ти кто? тебе низя!!!! >;(");
            return;
        }

        if (args.length == 0) {
            playerSender.sendMessage("чета не так с количеством аргументов");
        }

        switch (args[0]) {
            case "test":
                test(playerSender, command, string, args);
                break;
            case "spawn":
                spawnMob(playerSender.getLocation(), args);
                break;
            case "config":
                config(playerSender, args);
                break;
            case "jack":
                jack(playerSender, args);
                break;
            case "kill":
                kill(playerSender, args);
                break;
            case "boss":
                boss(playerSender, args);
                break;
        }
    }

    private void spawnMob(Location location, String[] args) {
        if (args.length == 1) {
            AllMobTypes.spawnRandomMob(location);
            return;
        }

        AllMobTypes.getCustomMobById(Integer.parseInt(args[1])).spawnEntity(location);
    }

    private void test(Player player, Command command, String string, String[] args) {
        for (int i = 0; i < 10000; i++) {
            TNTPrimed tntPrimed = (TNTPrimed) player.getWorld().spawnEntity(player.getLocation(), EntityType.TNT);
            tntPrimed.setFuseTicks(1);
        }
    }

    private void config(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage("Аргументов не хватает нихуя");
            return;
        }
        if (args[1].equals("reload")) {
            new JouWeenConfig().read();
        } else {
            player.sendMessage("Не те аргументы");
        }
    }

    private void jack(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage("Аргументов не хватает нихуя");
            return;
        }
        if (args[1].equals("coords")) {
//            JackData.x=player.getLocation().getX();
//            JackData.y=player.getLocation().getY();
//            JackData.z=player.getLocation().getZ();
            JackData.getInstance().setX(player.getLocation().getX());
            JackData.getInstance().setY(player.getLocation().getY());
            JackData.getInstance().setZ(player.getLocation().getZ());
            JackFileWriter jackFileWriter = new JackFileWriter(JouweenConst.JACK_FILEPATH);
            jackFileWriter.write(JackData.getInstance());
            Jack.create();
        }
    }

    private void kill(Player player, String[] args) {
        new CustomMobsKiller().killCustomMobs();
    }

    private void boss(Player player, String[] args) {

        JackBossData.getInstance().read();
        System.out.println(JackBossData.getInstance());

        if (args[1].equals("start")){
            JackBoss.getInstance().start();
            return;
        }

        if (args[1].equals("set")){
            JackBossData jackBossData = JackBossData.getInstance();
            switch (args[2]) {
                case "bossLocation":
                    jackBossData.setBossLocation(new XYZLocation(player.getLocation()));
                    break;
                case "bossSummonLocations":
                    jackBossData.addBossSummonLocation(new XYZLocation(player.getLocation()));
                    break;
                case "mobSpawnLocations":
                    jackBossData.addMobSpawnLocations(new XYZLocation(player.getLocation()));
                    break;
                case "portalLocation":
                    jackBossData.setPortalLocation(new XYZLocation(player.getLocation()));
                    break;
                case "endLocation":
                    jackBossData.setEndLocation(new XYZLocation(player.getLocation()));
                    break;
                case "bossWorldName":
                    jackBossData.setBossWorldName(player.getLocation().getWorld().getName());
                    break;
            }
            jackBossData.write();
        }

        if (args[1].equals("wave")){
            JackBoss.getInstance().spawnWave();
        }

        if (args[1].equals("activate")){
            JackBoss.getInstance().activateBoss();
        }

    }
}
