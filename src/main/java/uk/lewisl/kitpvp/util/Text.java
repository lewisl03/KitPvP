package uk.lewisl.kitpvp.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Text {

    public static String convertString(String message){
        return ChatColor.translateAlternateColorCodes('&', message);

    }

    public static void sendGlobalMessage(String message){
        for(Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

}
