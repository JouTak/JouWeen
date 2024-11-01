package org.joutak.jouween.config;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.Getter;
import org.joutak.jouween.config.file.JouWeenConfigReader;
import org.joutak.jouween.config.file.JouWeenConfigWriter;

@Data
public class JouWeenConfig {

    @Getter
    private static JouWeenConfig instance;

    @Expose
    public int phaseNumber = 0;

    @Expose
    @Getter
    public String worldName = "world";

    @Expose
    @Getter
    public int moonPhase = 0;

    public static boolean isFirstPhaseEnabled() {
        return getInstance().phaseNumber >= 1;
    }

    public static boolean isSecondPhaseEnabled() {
        return getInstance().phaseNumber >= 2;
    }

    public void read() {
        JouWeenConfig jouWeenConfig = new JouWeenConfigReader(JouweenConst.PROPERTIES_FILEPATH).read();
        this.phaseNumber = jouWeenConfig.phaseNumber;
        this.worldName = jouWeenConfig.worldName;
        this.moonPhase = jouWeenConfig.moonPhase;
        instance = this;
    }

    public void write(){
        new JouWeenConfigWriter(JouweenConst.PROPERTIES_FILEPATH).write(this);
    }

}
