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
        Iterator it = KitPvp.dataManager.data.combatTag.entrySet().iterator();
        while (it.hasNext()){
             UUID key = (UUID) it.next();
             //check if its been removed
             if(!KitPvp.dataManager.data.combatTag.containsKey(key)){ continue;}

             long value = KitPvp.dataManager.data.combatTag.get(key);


            if(value <= System.currentTimeMillis()){
                KitPvp.dataManager.data.combatTag.remove(key);
            }



        }


    }
}
