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


        Iterator<Map.Entry<UUID, FastBoard>> it = KitPvp.dataManager.data.scoreBoards.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<UUID, FastBoard> entry = it.next();
            if(!KitPvp.dataManager.data.scoreBoards.containsKey(entry.getKey())){ continue;}


            if(Bukkit.getPlayer(entry.getKey()) == null){Scoreboard.removeScoreboard(Bukkit.getPlayer(entry.getKey())); continue;}
            Scoreboard.updateScoreboard(entry.getValue(), Bukkit.getPlayer(entry.getKey()));


        }





    }
}
