package org.joutak.jouween.boss;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bukkit.Location;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class XYZLocation {

    private double x = 0;
    private double y = 0;
    private double z = 0;

    public XYZLocation(Location location) {
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
    }

}
