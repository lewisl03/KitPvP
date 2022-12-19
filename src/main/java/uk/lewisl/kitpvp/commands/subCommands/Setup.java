package uk.lewisl.kitpvp.commands.subCommands;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.data.DataManager;
import uk.lewisl.kitpvp.types.CmdHandler;
import uk.lewisl.kitpvp.types.Region;
import uk.lewisl.kitpvp.types.SpawnType;

import java.util.ArrayList;

public class Setup extends CmdHandler {
    public final static ItemStack WAND;

    static
    {
        WAND = new ItemStack(Material.IRON_HOE, 1);
        ItemMeta meta = WAND.getItemMeta();
        meta.setDisplayName("KitPvP Spawn Region Wand");
        WAND.setItemMeta(meta);
    }



    @Override
    public void onCommand(Player p, String[] args, String s) {

        if(args.length == 0){
            SpawnType spawn = KitPvp.dataManager.data.storage.spawn;
            Region spawnR = KitPvp.dataManager.data.storage.spawnRegion;
            p.sendMessage("Current setup\n" +
                    "Spawn: world: +"+spawn.getWorld()+"+ X: "+ spawn.getX()+" Y: "+ spawn.getY()+ " Z: "+ spawn.getZ()+"\n" +
                    "Spawn Region: " + spawnR.getPos1().toString()+" \n"+
                    spawnR.getPos2().toString()+
                    "");




        }



        if(args[0].equalsIgnoreCase("wand")){
            p.getInventory().addItem(WAND);
            p.sendMessage("You have been given spawn region wand");
        }

        if(args[0].equalsIgnoreCase("setspawn")){
            p.getInventory().addItem(WAND);
            p.sendMessage("You have been given spawn region wand");
        }





    }


    @Override
    public String name() {
        return "setup";
    }

    @Override
    public String info() {
        return "setup setspawn/spawnregion/wand";
    }

    @Override
    public ArrayList<String> aliases() {
        return new ArrayList<>();
    }

    @Override
    public boolean showOnHelp() {
        return true;
    }

    @Override
    public String example() {
        return "/setup";
    }

    @Override
    public String permission() {
        return "kitpvp.admin";
    }

    @Override
    public boolean playerOnly() {
        return false;
    }

    @Override
    public void setup(KitPvp plugin) {

    }




}
