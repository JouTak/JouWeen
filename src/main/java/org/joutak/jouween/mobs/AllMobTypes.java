package org.joutak.jouween.mobs;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AllMobTypes {

    private static final List<CustomMob> customMobs = new ArrayList<>();

    private static int weightSum = 1;

    public static void addMob(CustomMob mob) {
        customMobs.add(mob);
        weightSum += mob.getWeight();
    }

    public static Entity spawnRandomMob(Location location) {
        int randomNumber = (int) Math.ceil(Math.random() * weightSum);
        int curr = 0;
        while (randomNumber > 0) {
            CustomMob customMob = customMobs.get(curr);
            if (customMob.getWeight() >= randomNumber) {
                return customMob.spawnEntity(location);
            }
            randomNumber -= customMob.getWeight();
            curr++;
        }
        return customMobs.get(0).spawnEntity(location);
    }

    public static CustomMob getCustomMobById(int luckId){
        return customMobs.stream().filter(mob -> mob.getLuckId()==luckId).findFirst().orElse(null);
    }
}
