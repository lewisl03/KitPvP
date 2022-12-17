package uk.lewisl.kitpvp.data;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import uk.lewisl.kitpvp.KitPvp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

public class ConfigManager {

    private FileConfiguration config;
    private File configFile;



    private ArrayList<File> files = new ArrayList<>();

    public void setupFiles() {


        //get files

        files.add(new File(KitPvp.getPlugin().getDataFolder(), "config.yml"));







        for (File file : files) {
            if(!file.exists()){
                file.getParentFile().mkdirs();
                KitPvp.getPlugin().saveResource(file.getName(), false);
                KitPvp.logger(Level.FINE, file.getName() +" has been created");
            }

        }




        this.config = new YamlConfiguration();

        try {
            this.config.load(files.get(0));
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


    public void saveFiles() {
        /*
        try {
            this.config.save(files.get(0));
            Bukkit.getLogger().log(Level.FINE, "Config.yml has been saved");
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.SEVERE, "Config.yml could not be saved");
        }

         */


        for (File file : files) {
            try {
                this.config.save(file.getName());
                KitPvp.logger(Level.FINE, file.getName()+ " Has been saved");
            } catch (IOException e) {
                e.printStackTrace();
                KitPvp.logger(Level.SEVERE, file.getName()+ " Could not be saved");
            }




        }




    }



    public FileConfiguration getConfig() {
        return config;
    }




}
