package uk.lewisl.kitpvp.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.types.Scoreboard;
import uk.lewisl.kitpvp.util.Text;

//this class is used for messaging and scoreboard management
public class LogInOutEvents implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void playerJoinEvent(PlayerJoinEvent e){
        Text.sendGlobalMessage("&8[&2+&8]&7 "+ e.getPlayer().getName());
        //add them to the scoreboard thing
        Scoreboard.addScoreboard(e.getPlayer());


    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void playerLeaveEvent(PlayerQuitEvent e){
        Text.sendGlobalMessage("&8[&4-&8]&7 "+ e.getPlayer().getName());

        //remove them from the scoreboard thing
        Scoreboard.removeScoreboard(e.getPlayer());

    }

}
