package uk.lewisl.kitpvp.commands.subCommands;

import org.bukkit.entity.Player;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.types.CmdHandler;

import java.util.ArrayList;

public class Bypass extends CmdHandler {
    @Override
    public void onCommand(Player p, String[] args, String s) {
        if(KitPvp.dataManager.data.bypass.contains(p.getUniqueId())){
            KitPvp.dataManager.data.bypass.remove(p.getUniqueId());
            p.sendMessage("Bypass has been turned off");
        }else{
            KitPvp.dataManager.data.bypass.add(p.getUniqueId());
            p.sendMessage("Bypass has been turned on");
        }



    }

    @Override
    public String name() {
        return "bypass";
    }

    @Override
    public String info() {
        return "bypass limits";
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
        return "/bypass";
    }

    @Override
    public String permission() {
        return "kitpvp.bypass";
    }

    @Override
    public boolean playerOnly() {
        return false;
    }

    @Override
    public void setup(KitPvp plugin) {

    }
}
