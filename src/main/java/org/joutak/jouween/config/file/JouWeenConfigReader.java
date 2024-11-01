package org.joutak.jouween.config.file;

import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.joutak.jouween.config.JouWeenConfig;
import org.joutak.jouween.jack.data.JackData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class JouWeenConfigReader {

    String filepath;

    public JouWeenConfigReader(String filepath) {
        this.filepath = filepath;
    }

    public JouWeenConfig read() {

        String json;
        try {
            json = Files.readString(Paths.get(filepath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JouWeenConfig jouWeenConfig = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().fromJson(json, JouWeenConfig.class);
        return jouWeenConfig;

    }

}
