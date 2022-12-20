package uk.lewisl.kitpvp.commands.subCommands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.commands.cmds.Kit;
import uk.lewisl.kitpvp.types.CmdHandler;
import uk.lewisl.kitpvp.types.RLocation;
import uk.lewisl.kitpvp.types.Region;

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

        if(args.length <= 1){
            RLocation spawn = KitPvp.dataManager.data.storage.getSpawn();
            Region spawnR = KitPvp.dataManager.data.storage.getSpawnRegion();
            p.sendMessage("Current setup\n" +
                    "Spawn: world: +"+spawn.getWorld().getName()+"+ X: "+ spawn.getX()+" Y: "+ spawn.getY()+ " Z: "+ spawn.getZ()+"\n" +
                    "Spawn Region: " + spawnR.getPos1().toString()+" \n"+
                    spawnR.getPos2().toString()+
                    "");




        }


        if(args[1].equalsIgnoreCase("wand")){
            p.getInventory().addItem(WAND);
            p.sendMessage("You have been given spawn region wand");
        }

        if(args[1].equalsIgnoreCase("setspawnregion")){
            Region region = KitPvp.getPlugin().getRegion(p);
            if(region.getPos1() == null){
                p.sendMessage("Position 1 has not been set");
                return;
            }else if(region.getPos2() == null) {
                p.sendMessage("Position 2 has not been set");
                return;
            }

            KitPvp.dataManager.data.storage.setSpawnRegion(region);
            p.sendMessage("Spawn region has successfully been set");
        }


        if(args[1].equalsIgnoreCase("setspawn")){
            Location loc = p.getLocation();
            KitPvp.dataManager.data.storage.setSpawn(new RLocation(loc.getWorld().getName(), loc.getX(), loc.getY(), loc.getZ()));
            p.sendMessage("You have set spawn to "+ loc.getX()+":"+loc.getY()+":"+loc.getZ());
            return;
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
