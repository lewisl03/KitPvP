package uk.lewisl.kitpvp.runnable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.data.DataManager;
import uk.lewisl.kitpvp.types.PvPPlayer;
import uk.lewisl.kitpvp.types.Region;

public class PlayerLocationChecker extends BukkitTask{
    public PlayerLocationChecker(long delay, long period) {
        super(delay, period);
    }

    @Override
    public void run() {
       Region region = KitPvp.getPlugin().getDataManager().data.storage.spawnRegion;
       if(region == null){return;}
       if(region.getPos1() == null || region.getPos2() == null) return;
       Location loc1 = KitPvp.getPlugin().getDataManager().data.storage.spawnRegion.getPos1();
       Location loc2 = KitPvp.getPlugin().getDataManager().data.storage.spawnRegion.getPos2();
       Location loc;

        for(Player player : Bukkit.getOnlinePlayers())
        {
            loc = player.getLocation();
           // if(){}




        }




    }
}
