package uk.lewisl.kitpvp.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import uk.lewisl.kitpvp.KitPvp;

public class BalanceCacher implements Listener {
    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e){
         KitPvp.dataManager.data.playersCache.add(KitPvp.dataManager.data.getPlayer(e.getPlayer()));
    }
    @EventHandler
    public void playerQuitEvent(PlayerQuitEvent e){
        KitPvp.dataManager.data.playersCache.remove(KitPvp.dataManager.data.getPlayer(e.getPlayer()));
    }


}
