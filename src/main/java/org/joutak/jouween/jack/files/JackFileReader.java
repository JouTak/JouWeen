package org.joutak.jouween.jack.files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.joutak.jouween.jack.data.JackData;
import org.joutak.jouween.jack.data.JackQuestPlayerData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JackFileReader {

    String filepath;

    public JackFileReader(String filepath) {
        this.filepath = filepath;
    }

    public JackQuestPlayerData readPlayers() {
        String json;
        try {
            json = Files.readString(Paths.get(filepath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JackQuestPlayerData list = new Gson().fromJson(json, JackQuestPlayerData.class);
        return list;
    }

    public JackData readJack() {
        String json;
        try {
            json = Files.readString(Paths.get(filepath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JackData jackData = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().fromJson(json, JackData.class);
        return jackData;
    }

}
