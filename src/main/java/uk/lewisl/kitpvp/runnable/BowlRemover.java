package uk.lewisl.kitpvp.runnable;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.types.PvPPlayer;

public class BowlRemover extends BukkitTask{


    public BowlRemover(long delay, long period) {
        super(delay, period);
    }

    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            PvPPlayer player = KitPvp.dataManager.data.getPlayer(p);
            if(!player.isPlayerBypass()){
                for(ItemStack is : p.getInventory()){
                    if(is == null) continue;
                    if(is.getType().equals(Material.BOWL)){
                        p.getInventory().removeItem(is);
                    }
                }

            }

        }

    }
}
