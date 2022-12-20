package uk.lewisl.kitpvp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import uk.lewisl.kitpvp.commands.CmdManager;
import uk.lewisl.kitpvp.commands.cmds.*;
import uk.lewisl.kitpvp.data.ConfigManager;
import uk.lewisl.kitpvp.data.DataManager;
import uk.lewisl.kitpvp.data.MySQL;
import uk.lewisl.kitpvp.events.*;
import uk.lewisl.kitpvp.runnable.PlayerLocationChecker;
import uk.lewisl.kitpvp.types.Region;

import java.util.HashMap;
import java.util.logging.Level;

public final class KitPvp extends JavaPlugin {
    public static DataManager dataManager;
    public static ConfigManager configManager;
    public static MySQL mysql;
    private static KitPvp plugin;
    public CmdManager cmdManager;
    private HashMap<Player, Region> setupRegions = new HashMap<>();
    private PlayerLocationChecker playerLocationChecker;


    @Override
    public void onEnable() {

        this.plugin = this;

        configManager = new ConfigManager();
        configManager.setupFiles();

        mysql = new MySQL();
        mysql.setup();
        dataManager = new DataManager();
        dataManager.setup();

        cmdManager = new CmdManager();
        cmdManager.setup(plugin);

        setupTasks();





        // Plugin startup logic
        Bukkit.getLogger().log(Level.INFO, "KitPvP Core has loaded");

        Bukkit.getPluginCommand("kit").setExecutor(new Kit());
        Bukkit.getPluginCommand("kitcreate").setExecutor(new KitCreate());
        Bukkit.getPluginCommand("kitdelete").setExecutor(new KitDelete());
        Bukkit.getPluginCommand("coins").setExecutor(new Balance());
        Bukkit.getPluginCommand("stats").setExecutor(new Stats());


        //events nerd
        PluginManager manager = this.getServer().getPluginManager();
        manager.registerEvents(new BlockEvents(), this);
        manager.registerEvents(new TnTExplosionEvent(), this);
        manager.registerEvents(new PlayerEvents(), this);
        manager.registerEvents(new BalanceCacher(), this);
        manager.registerEvents(new EntityNoSpawn(), this);
        manager.registerEvents(new UseWandEvent(), this);







    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        configManager.saveFiles();
        dataManager.saveData();

        if(this.playerLocationChecker != null){this.playerLocationChecker.cancel();}

    }


    private void setupTasks(){
        this.playerLocationChecker = new PlayerLocationChecker(0, 5L);
        System.out.println("Setup async tasks!");
    }



    public static void logger(Level level, String message){
        Bukkit.getLogger().log(level, message);
    }

    public static KitPvp getPlugin() {
        return plugin;
    }
    public Region getRegion(Player player)
    {
        return setupRegions.get(player);
    }
    public void addRegion(Player player, Region region)
    {
        setupRegions.put(player, region);
    }

    public DataManager getDataManager(){
        return dataManager;
    }


}
