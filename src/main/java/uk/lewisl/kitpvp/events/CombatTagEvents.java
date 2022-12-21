package uk.lewisl.kitpvp.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.types.PvPPlayer;
import uk.lewisl.kitpvp.util.PlayerUtil;
import uk.lewisl.kitpvp.util.Text;

import java.util.UUID;

public class CombatTagEvents implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void playerQuitEvent(PlayerQuitEvent e){
        PvPPlayer player = KitPvp.dataManager.data.getPlayer(e.getPlayer());

        if(player.isCombatTagged()){
            if(KitPvp.configManager.getConfig().getBoolean("combatTag.killOnLogout")) {
                player.addDeath(1);



                PvPPlayer target = KitPvp.dataManager.data.getPlayer(PlayerUtil.getPlayerFromUUID(player.getLastPersonToHit()));
                target.addKill(1);

                UUID assistorUUID = player.getHighestAssists(target.getUUID());
                if(assistorUUID != null){
                    PvPPlayer highestAssist = KitPvp.dataManager.data.getPlayer(PlayerUtil.getPlayerFromUUID(assistorUUID));
                    highestAssist.addAssist(1);
                    Player assistorPlayer = Bukkit.getPlayer(assistorUUID);
                    if(assistorPlayer != null){
                        assistorPlayer.sendMessage("You helped killed "+ e.getPlayer().getName());
                    }
                }
                Text.sendGlobalMessage(ChatColor.RED + e.getPlayer().getName() + " has combat logged to avoid " + PlayerUtil.getPlayerFromUUID(player.getLastPersonToHit()).getName() + (assistorUUID != null ? " assisted by "+ PlayerUtil.getPlayerFromUUID(assistorUUID).getName() : ""));
            }


            if(KitPvp.configManager.getConfig().getBoolean("combatTag.summonLighteningOnLogout"))
                e.getPlayer().getLocation().getWorld().strikeLightningEffect(e.getPlayer().getLocation());
        }
    }


    @EventHandler
    public void onPlayerHitPlayer(EntityDamageByEntityEvent e){
        if(e.getEntity().getType() != EntityType.PLAYER && e.getDamager().getType() != EntityType.PLAYER)return;
        Player p = (Player) e.getEntity();
        PvPPlayer player = KitPvp.dataManager.data.getPlayer(p);
        PvPPlayer damager = KitPvp.dataManager.data.getPlayer((Player) e.getDamager());



        //set combat tag
        player.setCombatTag();
        damager.setCombatTag();
        player.setLastPersonToHit(damager.getUUID());

        if(!player.isCombatTagged())
            p.sendMessage("You have been combat tagged for " + KitPvp.configManager.getConfig().getLong("combatTag.TagTime") + " seconds");
        if(!damager.isCombatTagged())
            damager.getPlayer().sendMessage("You have been combat tagged for " + KitPvp.configManager.getConfig().getLong("combatTag.TagTime") + " seconds");


    }





}
