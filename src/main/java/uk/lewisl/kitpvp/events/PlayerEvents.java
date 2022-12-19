package uk.lewisl.kitpvp.events;

import net.kettlemc.nocroptrample.event.CropTrampleEvent;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.ItemMeta;
import uk.lewisl.kitpvp.util.PlayerUtil;

public class PlayerEvents implements Listener {

    @EventHandler
    public void onPlayerDamageEvent(EntityDamageEvent e) {
        if(!(e.getEntity().getType().equals(EntityType.PLAYER))) return;
        Player p = (Player) e.getEntity();


        if(e.getCause().equals(EntityDamageEvent.DamageCause.DROWNING) ||
                e.getCause().equals(EntityDamageEvent.DamageCause.FALL) ||
                e.getCause().equals(EntityDamageEvent.DamageCause.LAVA)){
            if (!PlayerUtil.bypass(p)) {
                e.setCancelled(true);
            }
        }
    }

/*
    @EventHandler
    public void onTrample(CropTrampleEvent e) {
        if (e.getCause() == CropTrampleEvent.TrampleCause.PLAYER) {
            e.setCancelled(true);
        }
    }

 */

//instant mushroom stew healing
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        if(e.getClickedBlock() == null){return;}

        Player p = e.getPlayer();
        if (p.getHealth() == 20) {
        } else {
            int soup = +7;

            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK && p.getItemInHand().getType() == Material.MUSHROOM_STEW) {
                p.setHealth(p.getHealth() + soup > p.getMaxHealth() ? p.getMaxHealth() : p.getHealth() + soup);
               // e.getPlayer().getItemInHand().setAmount(e.getPlayer().getItemInHand().getAmount() - 1);
                e.getPlayer().getItemInHand().setType(Material.BOWL);
                e.getPlayer().getItemInHand().setItemMeta((ItemMeta)null);


            }
        }

        if(e.getClickedBlock().getType().equals(Material.CHEST) || e.getClickedBlock().getType().equals(Material.TRAPPED_CHEST) &&  e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(!PlayerUtil.bypass(e.getPlayer())){
                e.setCancelled(true);
            }

        }






    }



    @EventHandler
    public void playerLoseHunger(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }


    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent e){

        e.getDrops().clear();
        e.setNewExp(0);
    }

    @EventHandler
    public void playerDropItemEvent(PlayerDropItemEvent e) {
        e.getPlayer().sendMessage("You cannot drop items");
        e.setCancelled(true);
    }














}