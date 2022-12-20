package uk.lewisl.kitpvp.runnable;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.commands.cmds.Kit;
import uk.lewisl.kitpvp.types.Scoreboard;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class ScoreboardUpdater extends BukkitTask{
    public ScoreboardUpdater(long delay, long period) {
        super(delay, period);
    }

    @Override
    public void run() {


        Iterator it = KitPvp.dataManager.data.scoreBoards.entrySet().iterator();
        while (it.hasNext()){
            UUID key = (UUID) it.next();
            if(!KitPvp.dataManager.data.scoreBoards.containsKey(key)){ continue;}

            FastBoard value = KitPvp.dataManager.data.scoreBoards.get(key);
            if(Bukkit.getPlayer(key) == null){Scoreboard.removeScoreboard(Bukkit.getPlayer(key)); continue;}
            Scoreboard.updateScoreboard(value, Bukkit.getPlayer(key));


        }





    }
}
