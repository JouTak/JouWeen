package org.joutak.jouween.boss.skillsets;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;
import org.joutak.jouween.JouWeen;
import org.joutak.jouween.Utils;
import org.joutak.jouween.boss.JackBoss;
import org.joutak.jouween.boss.JackBossData;
import org.joutak.jouween.mobs.AllMobTypes;

import java.util.ArrayList;
import java.util.Objects;

public class SecondSkillSet implements CurrSkillSet {

    public SecondSkillSet() {
    }

    @Override
    public void targetSkill() {

        //fireballs

        ArrayList<Player> players = getAllNearPlayers();

        if (players.isEmpty()) return;

        ArrayList<Player> targets = new ArrayList<>();

        for (int i = 0; i < 1 + (players.size() / 5); i++) {
            targets.add(players.get((int) (Math.random() * 1000) % players.size()));
        }

        for (Player player : targets
        ) {
            Location loc = player.getLocation();

            AreaEffectCloud areaEffectCloud = (AreaEffectCloud) Objects.requireNonNull(Bukkit.getWorld(JackBossData.getInstance().getBossWorldName())).spawnEntity(loc, EntityType.AREA_EFFECT_CLOUD);
            areaEffectCloud.setColor(Color.RED);
            areaEffectCloud.setParticle(Particle.DAMAGE_INDICATOR);
            areaEffectCloud.setRadius(1.5F);
            areaEffectCloud.setDuration(60);

            Bukkit.getScheduler().scheduleSyncDelayedTask(JouWeen.getInstance(), () -> {
                loc.setY(loc.getY() + 10);
                Vector vector = new Vector();
                vector.setY(-2);
                loc.setDirection(vector);
                Fireball fireball = (Fireball) Objects.requireNonNull(Bukkit.getWorld(JackBossData.getInstance().getBossWorldName())).spawnEntity(loc, EntityType.FIREBALL);

                fireball.setYield(3F);
            }, 60);
        }

    }

    @Override
    public void radiusSkill() {

        //wither skulls

        Location loc = JackBoss.getInstance().getJackBossWitherSkeleton().getLocation();
        loc.setY(loc.getY() + 2 + (int) (Math.random() * 1000) % 3);

        int angle = (int) (Math.random() * 1000) % 180;
        int radius = 2;

        double x0 = loc.getX();
        double z0 = loc.getZ();

        for (int i = 0; i < 6; i++) {


            double x1 = x0 + radius * Math.sin(Math.toRadians(angle));
            double z1 = z0 + radius * Math.cos(Math.toRadians(angle));

            loc.setX(x1);
            loc.setZ(z1);

            Location location = new Location(Bukkit.getWorld(JackBossData.getInstance().getBossWorldName()), x1, loc.getY(), z1);

            Vector vector = new Vector();

            vector.setX((x1 - x0) * 0.5D);
            vector.setZ((z1 - z0) * 0.5D);
            vector.setY(-0.3D);

            location.setDirection(vector);

            angle += 60;

            final WitherSkull[] witherSkull = new WitherSkull[1];

            Bukkit.getScheduler().scheduleSyncDelayedTask(JouWeen.getInstance(), () -> {
                witherSkull[0] = (WitherSkull) Objects.requireNonNull(Bukkit.getWorld(JackBossData.getInstance().getBossWorldName())).spawnEntity(location, EntityType.WITHER_SKULL);
                witherSkull[0].setGravity(false);
            }, 5 * i);
        }

    }

    @Override
    public void summonSkill() {

        JackBossData.getInstance().getBossSummonLocations().forEach(
                it -> {
                    for (int i = 0; i < 1; i++) {
                        AllMobTypes.spawnRandomMob(Utils.getLocation(Bukkit.getWorld(JackBossData.getInstance().bossWorldName), it));
                    }
                }
        );

    }

    @Override
    public CurrSkillSet shiftForward() {
        return new ThirdSkillSet();
    }

    @Override
    public CurrSkillSet shiftBackwards() {
        return new FirstSkillSet();
    }

    @Override
    public int getCurrStageNumb() {
        return 4;
    }
}
