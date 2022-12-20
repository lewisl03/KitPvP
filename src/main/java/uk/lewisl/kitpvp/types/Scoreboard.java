package uk.lewisl.kitpvp.types;


import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.commands.cmds.Kit;
import uk.lewisl.kitpvp.util.Maths;

public class Scoreboard {

    public static void addScoreboard(Player p){
        PvPPlayer player = KitPvp.dataManager.data.getPlayer(p);
        FastBoard board = new FastBoard(p);


        board.updateTitle(ChatColor.DARK_PURPLE + "KitPvP");
        board.updateLines(
                "", // Empty line
                ChatColor.LIGHT_PURPLE+"Kills: "+ChatColor.WHITE+ player.getKills(),
                ChatColor.LIGHT_PURPLE+"Deaths: "+ ChatColor.WHITE+player.getDeaths(),
                ChatColor.LIGHT_PURPLE+"KDR: "+ ChatColor.WHITE+Maths.getKDR(player.getKills(), player.getDeaths()),
                ChatColor.LIGHT_PURPLE+"Coins: "+ ChatColor.WHITE+player.getPlayerBalance(),
                ""
        );
        KitPvp.dataManager.data.scoreBoards.put(p.getUniqueId(), board);

    }

    public static void updateScoreboard(FastBoard board, Player p){
        PvPPlayer player = KitPvp.dataManager.data.getPlayer(p);

        board.updateLines(
                "", // Empty line
                ChatColor.LIGHT_PURPLE+"Kills: "+ChatColor.WHITE+ player.getKills(),
                ChatColor.LIGHT_PURPLE+"Deaths: "+ ChatColor.WHITE+player.getDeaths(),
                ChatColor.LIGHT_PURPLE+"KDR: "+ ChatColor.WHITE+Maths.getKDR(player.getKills(), player.getDeaths()),
                ChatColor.LIGHT_PURPLE+"Coins: "+ ChatColor.WHITE+player.getPlayerBalance(),
                ""
        );
    }

    public static void removeScoreboard(Player p){
        FastBoard board = KitPvp.dataManager.data.scoreBoards.get(p.getUniqueId());
        KitPvp.dataManager.data.scoreBoards.remove(p.getUniqueId());

        if(board != null){
            board.delete();
        }
    }

    public static void loadAll(){
        for(Player p : Bukkit.getOnlinePlayers()){
            addScoreboard(p);
        }
    }


}
