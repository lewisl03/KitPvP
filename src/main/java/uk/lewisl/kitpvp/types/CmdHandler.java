package uk.lewisl.kitpvp.types;

import org.bukkit.entity.Player;
import uk.lewisl.kitpvp.KitPvp;

import java.util.ArrayList;

public abstract class CmdHandler {
    public CmdHandler(){ }

    public abstract void onCommand(Player p, String[] args, String s);
    public abstract String name();
    public abstract String info();
    public abstract ArrayList<String> aliases();
    public abstract boolean showOnHelp();
    public abstract String example();
    public abstract String permission();
    public abstract boolean playerOnly();
    public ArrayList<ArgumentComponent> arguments = new ArrayList<>();;
    public abstract void setup(KitPvp plugin);


}
