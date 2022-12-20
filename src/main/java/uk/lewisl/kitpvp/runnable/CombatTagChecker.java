package uk.lewisl.kitpvp.runnable;

import org.bukkit.Bukkit;
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


        for(Map.Entry<UUID, Long> map : KitPvp.dataManager.data.combatTag.entrySet()){
            if(Bukkit.getPlayer(map.getKey()) != null && map.getValue() <= System.currentTimeMillis()) Bukkit.getPlayer(map.getKey()).sendMessage("You are no longer in combat");
        }


        KitPvp.dataManager.data.combatTag.entrySet().removeIf(entries -> (entries.getValue() <= System.currentTimeMillis()));


    }
}
