package uk.lewisl.kitpvp.data;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.types.PvPPlayer;
import uk.lewisl.kitpvp.types.SpawnType;
import uk.lewisl.kitpvp.types.items.Item;
import uk.lewisl.kitpvp.types.KitItem;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.logging.Level;

public class Data {

   public HashMap<String, ArrayList<KitItem>> kits;
   public HashSet<UUID> bypass = new HashSet<>();
   public HashSet<PvPPlayer> playersCache = new HashSet<>();
   public Storage storage;




   public PvPPlayer getPlayer(Player p){
      for (PvPPlayer player : playersCache)
         if(p.getUniqueId().equals(player.getUUID()))
            return player;

      return KitPvp.mysql.getPlayer(p.getUniqueId());
   }
   public PvPPlayer getOnlinePlayer(UUID uuid){
      for (PvPPlayer player : playersCache) if(uuid.equals(player.getUUID())) return player;
      return null;
   }


   public void saveAll(){

   }



























}
