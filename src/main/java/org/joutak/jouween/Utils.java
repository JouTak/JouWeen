package org.joutak.jouween;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Utils {

    public static Player getNearestPlayerToLocation(Location location) {
        ImmutableList<Player> players = ImmutableList.copyOf(Bukkit.getOnlinePlayers());
        double minDist = Double.MAX_VALUE;
        double distance;
        Player nearestPlayer = null;
        for (Player player : players) {
            if (!player.getLocation().getWorld().equals(location.getWorld())) {
                continue;
            }

            distance = getDistance(player.getLocation(), location);
            if (distance<minDist){
                minDist = distance;
                nearestPlayer = player;
            }
        }
        return nearestPlayer;
    }

    public static double getDistance(Location location1, Location location2) {
        return Math.sqrt(Math.pow(location1.getX() - location2.getX(), 2) +
                Math.pow(location1.getY() - location2.getY(), 2) +
                Math.pow(location1.getZ() - location2.getZ(), 2));
    }

    public static String getPropertyAsString(JsonObject jsonObject, String propertyName) {
        try {
            return jsonObject.get(propertyName).toString().replace("\"", "");
        } catch (Exception e) {
            return "";
        }
    }

//    public static boolean is

}