package uk.lewisl.kitpvp.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.commands.cmds.Kit;
import uk.lewisl.kitpvp.types.PvPPlayer;
import uk.lewisl.kitpvp.util.PlayerUtil;

import java.util.UUID;

public class BalanceCacher implements Listener {

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e){
         KitPvp.dataManager.data.playersCache.add(KitPvp.dataManager.data.getPlayer(e.getPlayer()));
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void playerQuitEvent(PlayerQuitEvent e){
        KitPvp.dataManager.data.playersCache.remove(KitPvp.dataManager.data.getPlayer(e.getPlayer()));
    }

    @EventHandler
    public void playerKillPlayer(PlayerDeathEvent e){
        //if the player /kills or dies and doesn't actually have a killer
        if(!(e.getEntity().getKiller() instanceof Player)){return;}

        //check if theyre both players
        if(!e.getEntity().getType().equals(EntityType.PLAYER) && !e.getEntity().getKiller().getType().equals(EntityType.PLAYER))return;
        Player killed = e.getEntity().getPlayer();
        Player killer = e.getEntity().getKiller();

        PvPPlayer killedP = KitPvp.dataManager.data.getPlayer(killed);


        //if its not null then we give the assistor points and a assist
        UUID assistorUUID = killedP.getHighestAssists(killer.getUniqueId());
        if(assistorUUID != null){
            PvPPlayer highestAssist = KitPvp.dataManager.data.getPlayer(PlayerUtil.getPlayerFromUUID(assistorUUID));
            highestAssist.addAssist(1);
            Player assistorPlayer = Bukkit.getPlayer(assistorUUID);
            if(assistorPlayer != null){
                assistorPlayer.sendMessage("You helped killed "+ killed.getName());
            }
        }


        e.setDeathMessage(ChatColor.RED + killed.getName() + " has been slain by " + killer.getName() + assistorUUID != null ? " assisted by "+ PlayerUtil.getPlayerFromUUID(assistorUUID).getName() : "");
        //add death to player killed
        KitPvp.dataManager.data.getPlayer(killed).addDeath(1);
        //add kill to the killer
        KitPvp.dataManager.data.getPlayer(killer).addKill(1);
    }



}
