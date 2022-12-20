package uk.lewisl.kitpvp.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import uk.lewisl.kitpvp.util.Text;

public class LogInOutMessages implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void playerJoinEvent(PlayerJoinEvent e){
        Text.sendGlobalMessage("&8[&2+&8]&7 "+ e.getPlayer().getName());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void playerLeaveEvent(PlayerQuitEvent e){
        Text.sendGlobalMessage("&8[&4-&8]&7 "+ e.getPlayer().getName());
    }

}
