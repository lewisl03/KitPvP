package uk.lewisl.kitpvp.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import uk.lewisl.kitpvp.util.PlayerUtil;

public class BlockEvents implements Listener {

    @EventHandler
    public void onBreakBlockEvent(BlockBreakEvent e){
        if(!PlayerUtil.bypass(e.getPlayer())){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e){


        //tnt blows up after 3 seconds
        if(e.getBlock().getType().equals(Material.TNT)){

            e.setCancelled(true);
            TNTPrimed t = e.getBlock().getWorld().spawn(e.getBlock().getLocation(), TNTPrimed.class);
            t.setFuseTicks(30);
            e.getPlayer().getItemInHand().setAmount(e.getItemInHand().getAmount() - 1);
            return;
        }

        if(!PlayerUtil.bypass(e.getPlayer())){
            e.setCancelled(true);
        }



    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){


        if(!PlayerUtil.bypass(e.getPlayer())){
            e.setCancelled(true);
        }
    }







}
