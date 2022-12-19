package uk.lewisl.kitpvp.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntityNoSpawn  implements Listener {

    @EventHandler
    public void onCreatureSpawnEvent(CreatureSpawnEvent e){
        if (e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.NATURAL)) e.setCancelled(true);
    }


}
