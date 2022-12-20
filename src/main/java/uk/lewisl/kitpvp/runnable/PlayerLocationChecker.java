package uk.lewisl.kitpvp.runnable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.data.DataManager;
import uk.lewisl.kitpvp.types.PvPPlayer;
import uk.lewisl.kitpvp.types.RLocation;
import uk.lewisl.kitpvp.types.Region;
import uk.lewisl.kitpvp.util.Maths;

public class PlayerLocationChecker extends BukkitTask{
    public PlayerLocationChecker(long delay, long period) {
        super(delay, period);
    }

    @Override
    public void run() {
       Region region = KitPvp.getPlugin().getDataManager().data.storage.getSpawnRegion();
       if(region == null){return;}
       if(region.getPos1() == null || region.getPos2() == null) return;
       RLocation loc1 = KitPvp.getPlugin().getDataManager().data.storage.getSpawnRegion().getPos1();
       RLocation loc2 = KitPvp.getPlugin().getDataManager().data.storage.getSpawnRegion().getPos2();
       Location loc;

       System.out.println("Running");

        for(Player player : Bukkit.getOnlinePlayers())
        {
            if(KitPvp.dataManager.data.bypass.contains(player.getUniqueId()))continue;
            System.out.println(player.getName()+" "+Maths.isInRect(player, region));







        }




    }
}
