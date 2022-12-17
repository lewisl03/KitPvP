package uk.lewisl.kitpvp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import uk.lewisl.kitpvp.commands.CmdManager;
import uk.lewisl.kitpvp.commands.Kit;
import uk.lewisl.kitpvp.commands.cmds.KitCreate;
import uk.lewisl.kitpvp.commands.cmds.KitDelete;
import uk.lewisl.kitpvp.data.ConfigManager;
import uk.lewisl.kitpvp.data.Data;
import uk.lewisl.kitpvp.data.MySQL;
import uk.lewisl.kitpvp.events.BlockEvents;
import uk.lewisl.kitpvp.events.PlayerEvents;
import uk.lewisl.kitpvp.events.TnTExplosionEvent;

import java.util.logging.Level;

public final class KitPvp extends JavaPlugin {
    public static Data data;
    public static ConfigManager configManager;
    public static MySQL mysql;
    private static KitPvp plugin;
    public CmdManager cmdManager;

    @Override
    public void onEnable() {

        this.plugin = this;

        configManager = new ConfigManager();
        configManager.setupFiles();

        mysql = new MySQL();
        mysql.setup();
        data = new Data();
        data.setup();

        cmdManager = new CmdManager();
        cmdManager.setup(plugin);





        // Plugin startup logic
        Bukkit.getLogger().log(Level.INFO, "KitPvP Core has loaded");

        Bukkit.getPluginCommand("kit").setExecutor(new Kit());
        Bukkit.getPluginCommand("kitcreate").setExecutor(new KitCreate());
        Bukkit.getPluginCommand("kitdelete").setExecutor(new KitDelete());

        //events nerd
        PluginManager manager = this.getServer().getPluginManager();
        manager.registerEvents(new BlockEvents(), this);
        manager.registerEvents(new TnTExplosionEvent(), this);
        manager.registerEvents(new PlayerEvents(), this);







    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        configManager.saveFiles();
        data.saveData();
    }



    public static void logger(Level level, String message){
        Bukkit.getLogger().log(level, message);
    }

    public static KitPvp getPlugin() {
        return plugin;
    }


}
