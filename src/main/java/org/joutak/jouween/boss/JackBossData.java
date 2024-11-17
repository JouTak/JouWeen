package org.joutak.jouween.boss;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.Getter;
import org.joutak.jouween.config.JouweenConst;
import org.joutak.jouween.jack.files.JackFileWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class JackBossData {

    @Getter
    private static JackBossData instance;

    @Expose
    public boolean bossStarted = false;

    @Expose
    public XYZLocation bossLocation = new XYZLocation();

    @Expose
    public String bossWorldName = "world";

    @Expose
    public List<XYZLocation> bossSummonLocations = List.of();

    @Expose
    public Map<XYZLocation, Boolean> mobSpawnLocations = Map.of();

    @Expose
    public XYZLocation portalLocation = new XYZLocation();

    @Expose
    public XYZLocation endLocation = new XYZLocation();

    public static void createJackBossData(){
        if (instance == null){
            instance = new JackBossData();
        }
    }

    public void write() {
        new JackFileWriter(JouweenConst.BOSS_FILEPATH).writeJackBoss(this);
    }

    public void addBossSummonLocation(XYZLocation location){
        bossSummonLocations.add(location);
        write();
    }

    public void addMobSpawnLocations(XYZLocation location){
        mobSpawnLocations.put(location, false);
        write();
    }

}
