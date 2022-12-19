package uk.lewisl.kitpvp.events;

import jdk.javadoc.internal.tool.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.commands.subCommands.Setup;
import uk.lewisl.kitpvp.types.Region;

public class UseWandEvent implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event)
    {
        if(event.getItem() == null || !event.getItem().equals(Setup.WAND))
        {
            return;
        }
        event.setCancelled(true);


        // Set position 1
        if(event.getAction().equals(Action.LEFT_CLICK_BLOCK))
        {
            Player player = event.getPlayer();
            Region region = KitPvp.getPlugin().getRegion(player);
            if(region == null)
            {
                region = new Region();
            }
            region.setPos1(event.getClickedBlock().getLocation());
            KitPvp.getPlugin().addRegion(player, region);
            player.sendMessage("Position 1 set at X: " + Integer.toString(region.getPos1().getBlockX())+", Y: "+Integer.toString(region.getPos1().getBlockY())+ " Z: "+Integer.toString(region.getPos1().getBlockZ()));

        }
        // Set position 2
        else if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
        {
            Player player = event.getPlayer();
            Region region = KitPvp.getPlugin().getRegion(player);
            if(region == null)
            {
                region = new Region();
            }
            region.setPos2(event.getClickedBlock().getLocation());
            KitPvp.getPlugin().addRegion(player, region);

            player.sendMessage("Position 2 set at X: " + Integer.toString(region.getPos2().getBlockX())+", Y: "+Integer.toString(region.getPos2().getBlockY())+ " Z: "+Integer.toString(region.getPos2().getBlockZ()));
        }
    }

}
