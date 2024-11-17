package org.joutak.jouween.boss;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bukkit.Location;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class XYZLocation {

    @Expose
    private double x = 0;
    @Expose
    private double y = 0;
    @Expose
    private double z = 0;
    @Expose
    private boolean used = false;

    public XYZLocation(Location location) {
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
    }

}
