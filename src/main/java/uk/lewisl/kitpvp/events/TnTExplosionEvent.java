package uk.lewisl.kitpvp.events;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class TnTExplosionEvent implements Listener {

    @EventHandler
    public void onExploadEvent(EntityExplodeEvent e){

        if(e.getEntity().getType().equals(EntityType.PRIMED_TNT)){
            e.blockList().clear();
        }
    }







}
