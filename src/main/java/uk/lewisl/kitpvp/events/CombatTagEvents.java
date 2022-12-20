package uk.lewisl.kitpvp.events;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.types.PvPPlayer;

public class CombatTagEvents implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void playerQuitEvent(PlayerQuitEvent e){
        PvPPlayer player = KitPvp.dataManager.data.getPlayer(e.getPlayer());
        if(player.isCombatTagged()){
            if(KitPvp.configManager.getConfig().getBoolean("combatTag.killOnLogout"))
                player.addDeath(1);

            if(KitPvp.configManager.getConfig().getBoolean("combatTag.summonLighteningOnLogout"))
                e.getPlayer().getLocation().getWorld().strikeLightningEffect(e.getPlayer().getLocation());
        }
    }


    @EventHandler
    public void onPlayerHitPlayer(EntityDamageByEntityEvent e){
        if(e.getEntity().getType() != EntityType.PLAYER && e.getDamager().getType() != EntityType.PLAYER)return;
        Player p = (Player) e.getEntity();
        PvPPlayer player = KitPvp.dataManager.data.getPlayer(p);
        player.setCombatTag();
        p.sendMessage("You have been combat tagged for "+ KitPvp.configManager.getConfig().getLong("combatTag.TagTime")+" seconds");


    }





}
