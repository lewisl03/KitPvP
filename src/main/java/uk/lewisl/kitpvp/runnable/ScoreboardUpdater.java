package uk.lewisl.kitpvp.runnable;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.commands.cmds.Kit;
import uk.lewisl.kitpvp.types.Scoreboard;

import java.util.Map;
import java.util.UUID;

public class ScoreboardUpdater extends BukkitTask{
    public ScoreboardUpdater(long delay, long period) {
        super(delay, period);
    }

    @Override
    public void run() {

        for(Map.Entry<UUID, FastBoard> entry : KitPvp.dataManager.data.scoreBoards.entrySet()){
            if(Bukkit.getPlayer(entry.getKey()) == null){Scoreboard.removeScoreboard(Bukkit.getPlayer(entry.getKey())); continue;}
            Scoreboard.updateScoreboard(entry.getValue(), Bukkit.getPlayer(entry.getKey()));
        }

    }
}
