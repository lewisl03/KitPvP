package uk.lewisl.kitpvp.runnable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.commands.cmds.Kit;
import uk.lewisl.kitpvp.data.DataManager;
import uk.lewisl.kitpvp.types.PvPPlayer;
import uk.lewisl.kitpvp.types.RLocation;
import uk.lewisl.kitpvp.types.Region;
import uk.lewisl.kitpvp.util.Maths;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class PlayerLocationChecker extends BukkitTask{
    public PlayerLocationChecker(long delay, long period) {
        super(delay, period);
    }

    @Override
    public void run() {
       Region region = KitPvp.getPlugin().getDataManager().data.storage.getSpawnRegion();
       if(region == null){return;}
       if(region.getPos1() == null || region.getPos2() == null) return;


        Iterator it = Bukkit.getOnlinePlayers().iterator();



        while (it.hasNext()){
            Player player = (Player) it.next();
            if(player == null) continue;

            if(KitPvp.dataManager.data.storage.bypass.contains(player.getUniqueId()))continue;


            PvPPlayer p = KitPvp.dataManager.data.getPlayer(player);
            boolean inArea = Maths.isInRect(player,region);
            //if they have bypass then we don't do anything
            if(p.isPlayerBypass()){continue;}

            //if player is not in spawn
            if(!inArea){
                p.setInSpawn(false);


                //if they don't have a last selected kit tp them back to spawn
                if(p.getSelectedKit() == null || !KitPvp.dataManager.data.kits.containsKey(p.getSelectedKit())){
                    p.getPlayer().teleport(KitPvp.dataManager.data.storage.getSpawn().getLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
                    player.sendMessage("You must select a kit before leaving spawn");
                    continue;
                }


                if(!p.hasKit()){
                    p.giveKitAsync(p.getSelectedKit());
                    player.sendMessage("You have been given Kit "+ p.getSelectedKit());
                    p.setHasKit(true);

                }
            }else{
                //set that theyre in spawn so they can't take damage
                p.setInSpawn(true);
            }

        }
    }
}
