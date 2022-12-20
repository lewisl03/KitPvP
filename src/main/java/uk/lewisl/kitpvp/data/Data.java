package uk.lewisl.kitpvp.data;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.types.PvPPlayer;
import uk.lewisl.kitpvp.types.KitItem;

import java.util.*;

public class Data {

   public HashMap<String, ArrayList<KitItem>> kits;
   public HashSet<PvPPlayer> playersCache = new HashSet<>();
   public Storage storage;
   public HashMap<UUID, Long> combatTag = new HashMap<>();
   public final Map<UUID, FastBoard> scoreBoards = new HashMap<>();

   public void addPlayer(Player p){
      for (PvPPlayer player : playersCache) {
         System.out.println(p + " " + player);
         if (p.getUniqueId().equals(player.getUUID()))
            return;
      }
         playersCache.add(getPlayer(p));


   }



   public PvPPlayer getPlayer(Player p){

      for (PvPPlayer player : playersCache) {
         if (player.getUUID().equals(p.getUniqueId()))
            return player;
      }

      return KitPvp.mysql.getPlayer(p.getUniqueId());
   }
   public PvPPlayer getPlayer(OfflinePlayer p){

      for (PvPPlayer player : playersCache) {
         if (player.getUUID().equals(p.getUniqueId()))
            return player;
      }

      return KitPvp.mysql.getPlayer(p.getUniqueId());
   }






   public PvPPlayer getOnlinePlayer(UUID uuid){
      for (PvPPlayer player : playersCache) if(uuid.equals(player.getUUID())) return player;
      return null;
   }


   public void saveAll(){
      int i = 0;
      for(PvPPlayer p : playersCache) {
         System.out.println(p.getUUID());
         p.save();
         i++;
      }
      System.out.println("Saved "+ i+" Players");

   }



























}
