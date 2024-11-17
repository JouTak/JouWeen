package org.joutak.jouween.jack.files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.joutak.jouween.boss.JackBossData;
import org.joutak.jouween.jack.data.JackData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Slf4j
public class JackFileWriter {

    String filepath;

    public JackFileWriter(String filepath) {
        this.filepath = filepath;
    }

    public void write(JackData jackData) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(jackData);
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

    public void writeJackBoss(JackBossData jackData) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(jackData);
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
