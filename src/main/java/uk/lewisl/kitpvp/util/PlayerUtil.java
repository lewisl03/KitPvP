package uk.lewisl.kitpvp.util;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import uk.lewisl.kitpvp.KitPvp;

import java.util.UUID;

public class PlayerUtil {

    public static boolean bypass(Player p){
        return KitPvp.dataManager.data.storage.bypass.contains(p.getUniqueId());
    }


    public static String getPlayerName(UUID uuid){
        if(Bukkit.getPlayer(uuid) != null){
            return Bukkit.getPlayer(uuid).getDisplayName();
        }else if(Bukkit.getOfflinePlayer(uuid) != null){
            return Bukkit.getOfflinePlayer(uuid).getName();
        }
        return null;
    }
    public static OfflinePlayer getPlayerFromUUID(String name){
        if(Bukkit.getPlayer(name) != null){
            return Bukkit.getPlayer(name);
        }else if(Bukkit.getOfflinePlayer(name) != null){
            return Bukkit.getOfflinePlayer(name);
        }
        return null;
    }

}
