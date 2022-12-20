package uk.lewisl.kitpvp.data;

import uk.lewisl.kitpvp.types.RLocation;
import uk.lewisl.kitpvp.types.Region;

import java.util.HashSet;
import java.util.UUID;

public class Storage {
    RLocation spawn;
    Region spawnRegion;
    public HashSet<UUID> bypass = new HashSet<>();

    public Storage(RLocation spawn, Region spawnRegion) {
        this.spawn = spawn;
        this.spawnRegion = spawnRegion;
    }

    public RLocation getSpawn() {
        return spawn;
    }

    public void setSpawn(RLocation spawn) {
        this.spawn = spawn;
    }

    public Region getSpawnRegion() {
        return spawnRegion;
    }

    public void setSpawnRegion(Region spawnRegion) {
        this.spawnRegion = spawnRegion;
    }
}
