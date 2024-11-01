package org.joutak.jouween.mobs;

import lombok.Data;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;

import java.util.List;

@Data
public abstract class CustomMob {

    protected int weight;


    protected int luckId;


    protected String mobName;

    protected List<String> customNames;

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setCustomNames(List<String> customNames) {
        this.customNames = customNames;
    }

    public abstract LivingEntity spawnEntity(Location location);

    protected String getRandomName() {
        int number = (int) Math.ceil(Math.random()*(customNames.size()-1));
        return customNames.get(number);
    }

    protected Material getRandomSculkMaterial(){
        int number = (int) Math.ceil(Math.random()*5);
        Material material = Material.AIR;
        switch (number) {
            case 1:
                material = Material.SCULK;
                break;
            case 2:
                material = Material.SCULK_SENSOR;
                break;
            case 3:
                material = Material.SCULK_CATALYST;
                break;
            case 4:
                material = Material.SCULK_SHRIEKER;
                break;
            case 5:
                material = Material.CALIBRATED_SCULK_SENSOR;
                break;
        }
        return material;
    }

}
