package uk.lewisl.kitpvp.runnable;

import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.commands.cmds.Kit;

import java.util.Map;
import java.util.UUID;

public class CombatTagChecker extends BukkitTask{


    public CombatTagChecker(long delay, long period) {
        super(delay, period);
    }

    @Override
    public void run() {
        for(Map.Entry<UUID, Long> entry : KitPvp.dataManager.data.combatTag.entrySet()){
            if(entry.getValue() <= System.currentTimeMillis()){
                KitPvp.dataManager.data.combatTag.remove(entry.getKey());
            }
        }
    }
}
