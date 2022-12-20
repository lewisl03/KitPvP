package uk.lewisl.kitpvp.data;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.types.KitItem;
import uk.lewisl.kitpvp.types.PvPPlayer;
import uk.lewisl.kitpvp.types.RLocation;
import uk.lewisl.kitpvp.types.Region;
import uk.lewisl.kitpvp.types.items.Item;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;

public class DataManager {
    public Data data;
    File kitJson;
    File storageJson;




    Gson gson  = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public void setup(){

        data = new Data();



        //load kits
        kitJson = new File(KitPvp.getPlugin().getDataFolder(), "kits.json");


        if(!(kitJson.exists())){
           data.kits = new HashMap<>();
            ArrayList kit =  new ArrayList<KitItem>();
            byte b = 0;
            short s = 0;
            kit.add(new KitItem(0, new Item(Material.STONE_SWORD, 1, s, b)));
            kit.add(new KitItem(1, new Item(Material.GOLDEN_APPLE, 8, s, b)));
            data.kits.put("Example", kit);
            data.kits.put("default", kit);
            kitJson.getParentFile().mkdirs();
            KitPvp.getPlugin().saveResource(kitJson.getName(), false);
            KitPvp.logger(Level.FINE, kitJson.getName() +" has been created");
        }else{

            try {
                Type type = new TypeToken<HashMap<String, ArrayList<KitItem>>>() {}.getType();
                FileReader reader = new FileReader(kitJson);
                data.kits = gson.fromJson(reader, type);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(data.kits == null){
            System.out.println("Unable to load kits");
            data.kits = new HashMap<>();
            ArrayList kit =  new ArrayList<KitItem>();
            byte b = 0;
            short s = 0;
            kit.add(new KitItem(0, new Item(Material.STONE_SWORD,1, s, b)));
            kit.add(new KitItem(1, new Item(Material.GOLDEN_APPLE, 8, s, b)));
            data.kits.put("Example", kit);
            data.kits.put("default", kit);

        }


        //load storage


        storageJson = new File(KitPvp.getPlugin().getDataFolder(), "storage.json");

        if(!(storageJson.exists())){
            data.storage = new Storage(new RLocation("world",0.0,0.0,0.0), new Region(new RLocation("world", 0,0,0), new RLocation("world", 1,1,1)), new HashSet<>());

            storageJson.getParentFile().mkdirs();
            KitPvp.getPlugin().saveResource(storageJson.getName(), false);
            KitPvp.logger(Level.FINE, storageJson.getName() +" has been created");
        }else{

            try {

                FileReader reader = new FileReader(storageJson);
                data.storage = gson.fromJson(reader, Storage.class);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(data.storage == null){
            System.out.println("Unable to load storage");
            data.storage = new Storage(new RLocation("world",0.0,0.0,0.0), new Region(new RLocation("world", 0,0,0), new RLocation("world", 1,1,1)), new HashSet<>());

        }












        //load balance, kills, deaths from mysql for all online players

        for(Player p : Bukkit.getOnlinePlayers())
            data.playersCache.add(data.getPlayer(p));


        System.out.println("Cache size "+data.playersCache.size());






    }

    public void saveData(){

        //save kits.json
        try {
            FileWriter writer = new FileWriter(kitJson);
            String json = gson.toJson(data.kits);


            writer.write(json);
            writer.close();
            System.out.println("Saved file kits.json");


        } catch (IOException e) {
            e.printStackTrace();
        }

        //save storage.json
        gson =  new Gson();

        try {
            FileWriter writer = new FileWriter(storageJson);

            Type type = new TypeToken<Storage>() {}.getType();
            String json = gson.toJson(data.storage, type);


            writer.write(json);
            writer.close();
            System.out.println("Saved file storage.json");


        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Cache size "+data.playersCache.size());
        //save all cache data to mysql
        int i = 0;
        for(PvPPlayer p : data.playersCache){
            System.out.println("Saving: "+ p.getUUID());
            p.save();
            i++;
        }
        System.out.println("Saved "+ i+" Players");



    }




}
