package uk.lewisl.kitpvp.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import uk.lewisl.kitpvp.KitPvp;

/**
 * @author lewis
 * @since 12/11/2022
 */
public class Sound {
   private static final FileConfiguration config = KitPvp.configManager.getConfig();


    //plays a success sound to the player
    public static void playSuccessSound(Player p) {


        if (config.getBoolean("soundEnabled")) {

            //gets settings from the config
            p.getWorld().playSound(p.getLocation(), org.bukkit.Sound.valueOf((String) config.getString("sound.successSound")), config.getInt("sound.volume"), config.getInt("sound.pitch"));
        }
    }
    //plays a deny sound to the player
    public static void playDenySound(Player p) {
        if (config.getBoolean("soundEnabled")) {
            //gets settings from the config
            p.getWorld().playSound(p.getLocation(), org.bukkit.Sound.valueOf((String) config.getString("sound.denySound")),  config.getInt("sound.volume"), config.getInt("sound.pitch"));
        }
    }

}