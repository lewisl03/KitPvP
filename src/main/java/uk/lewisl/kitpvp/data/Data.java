package uk.lewisl.kitpvp.data;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Material;
import uk.lewisl.kitpvp.KitPvp;
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


    File kitJson ;


    Gson gson  = new GsonBuilder()
            .setPrettyPrinting()
            .create();



    public void setup(){

        kitJson = new File(KitPvp.getPlugin().getDataFolder(), "kits.json");


        if(!(kitJson.exists())){
            kits = new HashMap<>();
            ArrayList kit =  new ArrayList<KitItem>();
            byte b = 0;
            short s = 0;
            kit.add(new KitItem(0, new Item(Material.STONE_SWORD, 1, s, b)));
            kit.add(new KitItem(1, new Item(Material.GOLDEN_APPLE, 8, s, b)));
            kits.put("Example", kit);
            kits.put("default", kit);
            kitJson.getParentFile().mkdirs();
            KitPvp.getPlugin().saveResource(kitJson.getName(), false);
            KitPvp.logger(Level.FINE, kitJson.getName() +" has been created");
        }else{

            try {
                Type type = new TypeToken<HashMap<String, ArrayList<KitItem>>>() {}.getType();
                FileReader reader = new FileReader(kitJson);
                kits = gson.fromJson(reader, type);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(kits == null){
            System.out.println("Unable to load kits");
            kits = new HashMap<>();
            ArrayList kit =  new ArrayList<KitItem>();
            byte b = 0;
            short s = 0;
            kit.add(new KitItem(0, new Item(Material.STONE_SWORD,1, s, b)));
            kit.add(new KitItem(1, new Item(Material.GOLDEN_APPLE, 8, s, b)));
            kits.put("Example", kit);
            kits.put("Example", kit);
            kits.put("default", kit);

        }




    }


    public void saveData(){

            try {
                FileWriter writer = new FileWriter(kitJson);

                Type type = new TypeToken<HashMap<String, ArrayList<KitItem>>>() {}.getType();
                String json = gson.toJson(kits, type);

                writer.write(json);
                writer.close();
                System.out.println("Saved file kits.json");


            } catch (IOException e) {
                e.printStackTrace();
            }



    }












}
