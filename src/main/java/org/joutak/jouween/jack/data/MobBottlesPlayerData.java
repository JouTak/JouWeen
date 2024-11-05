package org.joutak.jouween.jack.data;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class MobBottlesPlayerData {

    private HashMap<String, Boolean> mobBottles = new HashMap<>(Map.of(
            "speedster", false,
            "eye", false,
            "shooter", false,
            "zombie", false,
            "spreader", false,
            "skelehorse", false,
            "muscle", false,
            "witch", false
    ));

}
