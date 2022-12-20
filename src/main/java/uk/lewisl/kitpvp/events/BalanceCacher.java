package uk.lewisl.kitpvp.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
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

    @EventHandler
    public void playerKillPlayer(PlayerDeathEvent e){

        //check if theyre both players
        if(!e.getEntity().getType().equals(EntityType.PLAYER) && !e.getEntity().getKiller().getType().equals(EntityType.PLAYER))return;
        Player killed = e.getEntity().getPlayer();
        Player killer = e.getEntity().getKiller();

        e.setDeathMessage(ChatColor.RED + killed.getName() + " has been slain by " + killer.getName());
        //add death to player killed
        KitPvp.dataManager.data.getPlayer(killed).addDeath(1);
        //add kill to the killer
        KitPvp.dataManager.data.getPlayer(killer).addKill(1);
    }



}
