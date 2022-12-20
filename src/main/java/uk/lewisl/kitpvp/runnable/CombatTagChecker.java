package uk.lewisl.kitpvp.runnable;

import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.commands.cmds.Kit;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class CombatTagChecker extends BukkitTask{


    public CombatTagChecker(long delay, long period) {
        super(delay, period);
    }

    @Override
    public void run() {

        KitPvp.dataManager.data.combatTag.entrySet().removeIf(entries -> (entries.getValue() <= System.currentTimeMillis()));
        /*

        Iterator<Map.Entry<UUID, Long>> it = KitPvp.dataManager.data.combatTag.entrySet().iterator();




        while (it.hasNext()){
             Map.Entry<UUID, Long> entry = it.next();
             //check if its been removed
             if(!KitPvp.dataManager.data.combatTag.containsKey(entry.getKey())){ continue;}


            if(entry.getValue() <= System.currentTimeMillis()){
                KitPvp.dataManager.data.combatTag.remove(entry.getKey());
            }



        }

         */


    }
}
