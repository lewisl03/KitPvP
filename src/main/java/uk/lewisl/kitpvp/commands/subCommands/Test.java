package uk.lewisl.kitpvp.commands.subCommands;

import org.bukkit.entity.Player;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.commands.cmds.Kit;
import uk.lewisl.kitpvp.types.CmdHandler;

import java.util.ArrayList;

public class Test extends CmdHandler {
    @Override
    public void onCommand(Player p, String[] args, String s) {
       System.out.println(KitPvp.dataManager.data.playersCache.size());



    }

    @Override
    public String name() {
        return "test";
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
        return "/test";
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
