package uk.lewisl.kitpvp.types;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.NumberConversions;

public class RLocation {
    private String world;
    private double x;
    private double y;
    private double z;


    public RLocation(String world, double x, double y, double z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Location getLocation(){
        return new Location(Bukkit.getWorld(world), x, y, z);
    }





    public Chunk getChunk() {
        return getLocation().getChunk();
    }

    public String getStringWorld() {
        return world;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
    public World getWorld(){
        return Bukkit.getWorld(world);
    }


    public void setWorld(String world) {
        this.world = world;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }


    public static int locToBlock(double loc) {
        return NumberConversions.floor(loc);
    }

    public int getBlockX() {
        return locToBlock(this.x);
    }
    public int getBlockY() {
        return locToBlock(this.y);
    }
    public int getBlockZ() {
        return locToBlock(this.z);
    }

}
