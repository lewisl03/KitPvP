package uk.lewisl.kitpvp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import uk.lewisl.kitpvp.commands.CmdManager;
import uk.lewisl.kitpvp.commands.cmds.Balance;
import uk.lewisl.kitpvp.commands.cmds.Kit;
import uk.lewisl.kitpvp.commands.cmds.KitCreate;
import uk.lewisl.kitpvp.commands.cmds.KitDelete;
import uk.lewisl.kitpvp.data.ConfigManager;
import uk.lewisl.kitpvp.data.DataManager;
import uk.lewisl.kitpvp.data.MySQL;
import uk.lewisl.kitpvp.events.*;
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





        // Plugin startup logic
        Bukkit.getLogger().log(Level.INFO, "KitPvP Core has loaded");

        Bukkit.getPluginCommand("kit").setExecutor(new Kit());
        Bukkit.getPluginCommand("kitcreate").setExecutor(new KitCreate());
        Bukkit.getPluginCommand("kitdelete").setExecutor(new KitDelete());
        Bukkit.getPluginCommand("balance").setExecutor(new Balance());

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
