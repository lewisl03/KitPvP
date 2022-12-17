package uk.lewisl.kitpvp.util;

import org.bukkit.entity.Player;
import uk.lewisl.kitpvp.KitPvp;

public class PlayerUtil {

    public static boolean bypass(Player p){
        return KitPvp.data.bypass.contains(p.getUniqueId());
    }

}
