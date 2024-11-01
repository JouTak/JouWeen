package org.joutak.jouween.config.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.joutak.jouween.config.JouWeenConfig;
import org.joutak.jouween.jack.data.JackData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Slf4j
public class JouWeenConfigWriter {

    String filepath;

    public JouWeenConfigWriter(String filepath) {
        this.filepath = filepath;
    }

    public void write(JouWeenConfig jouWeenConfig) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(jouWeenConfig);
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(filepath));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            log.error("can't write to file: {}", filepath);
        }
        log.info("Json save was completed successfully");
    }

}
