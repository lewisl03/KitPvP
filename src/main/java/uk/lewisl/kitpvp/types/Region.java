package uk.lewisl.kitpvp.types;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.HashSet;

public class Region
{
    private RLocation pos1;
    private RLocation pos2;
    private ArrayList<Chunk> innerChunks = null;

    public Region(RLocation pos1, RLocation pos2)
    {
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    public Region()
    {
        this.pos1 = null;
        this.pos2 = null;
    }

    public boolean bothPositionsSelected()
    {
        return this.pos1 != null && this.pos2 != null;
    }

    public HashSet<Location> getBorderLocations()
    {
        HashSet<Location> locations = new HashSet<>();
        if(pos1 == null || pos2 == null)
        {
            return locations;
        }

        int minX = Math.min(pos1.getBlockX(), pos2.getBlockX());
        int minZ = Math.min(pos1.getBlockZ(), pos2.getBlockZ());

        int maxX = Math.max(pos1.getBlockX(), pos2.getBlockX());
        int maxZ = Math.max(pos1.getBlockZ(), pos2.getBlockZ());

        for(int x = minX; x < maxX; x++)
        {
            locations.add(new Location(pos1.getWorld(), x, 0, minZ));
            locations.add(new Location(pos1.getWorld(), x, 0, maxZ));
        }

        for(int z = minZ; z < maxZ; z++)
        {
            locations.add(new Location(pos1.getWorld(), minX, 0, z));
            locations.add(new Location(pos1.getWorld(), maxX, 0, z));
        }

        return locations;
    }

    public ArrayList<Location> getInnerLocations()
    {
        ArrayList<Location> locations = new ArrayList<>();
        if(pos1 == null || pos2 == null)
        {
            return locations;
        }

        int minX = Math.min(pos1.getBlockX(), pos2.getBlockX());
        int minY = Math.min(pos1.getBlockY(), pos2.getBlockY());
        int minZ = Math.min(pos1.getBlockZ(), pos2.getBlockZ());

        int maxX = Math.max(pos1.getBlockX(), pos2.getBlockX());
        int maxY = Math.max(pos1.getBlockY(), pos2.getBlockY());
        int maxZ = Math.max(pos1.getBlockZ(), pos2.getBlockZ());

        for(int x = minX; x <= maxX; x++)
            for(int y = minY; y <= maxY; y++)
                for(int z = minZ; z <= maxZ; z++)
                {
                    locations.add(new Location(pos1.getWorld(), x, y, z));
                }

        return locations;
    }

    private ArrayList<Chunk> getInnerChunks()
    {
        ArrayList<Chunk> chunks = new ArrayList<>();
        if(this.pos1 == null || this.pos2 == null)
        {
            return chunks;
        }
        World world = pos1.getWorld();

        int minX = Math.min(pos1.getChunk().getX(), pos2.getChunk().getX());
        int minZ = Math.min(pos1.getChunk().getZ(), pos2.getChunk().getZ());

        int maxX = Math.max(pos1.getChunk().getX(), pos2.getChunk().getX());
        int maxZ = Math.max(pos1.getChunk().getZ(), pos2.getChunk().getZ());

        for(int x = minX; x <= maxX; x++)
            for(int z = minZ; z <= maxZ; z++)
            {
                chunks.add(world.getChunkAt(x, z));
            }

        this.innerChunks = new ArrayList<>(chunks);
        return chunks;
    }

    public ArrayList<Chunk> getInnerChunks(boolean forceRecalculation)
    {
        if(forceRecalculation || this.innerChunks == null)
        {
            return this.getInnerChunks();
        }
        else
        {
            return this.innerChunks;
        }
    }

    public void setPos1(RLocation pos1)
    {
        this.pos1 = pos1;
    }

    public void setPos2(RLocation pos2)
    {
        this.pos2 = pos2;
    }

    public RLocation getPos1()
    {
       return pos1;
    }

    public RLocation getPos2()
    {
        return pos2;
    }

    @Override
    public String toString()
    {
        return "{" +
                "pos1:" + pos1 +
                ", pos2:" + pos2 +
                '}';
    }
}
