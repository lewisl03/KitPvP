package uk.lewisl.kitpvp.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.types.PvPPlayer;
import uk.lewisl.kitpvp.util.Text;

public class Spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)){commandSender.sendMessage("This command can only be used by players");  return true; }

        Player p = (Player) commandSender;

        if(strings.length >= 1 && p.hasPermission("KitPvp.spawnOther")){
            Player target = Bukkit.getPlayer(strings[0]);
            if(target == null){
                p.sendMessage(Text.convertString("Target does not exist"));
                return true;
            }else{
               target.teleport(KitPvp.dataManager.data.storage.getSpawn().getLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
               //clear their assisted
                KitPvp.dataManager.data.getPlayer( target).clearAssisted();
                p.sendMessage("You have teleported "+ target.getName()+" to spawn");
                return true;
            }
        }


        if(!p.hasPermission("KitPvp.spawn")){p.sendMessage("You do not have permissions to performe this command"); return true;}
        PvPPlayer player = KitPvp.dataManager.data.getPlayer((Player) commandSender);

        //check combat tag
        if(player.isCombatTagged()){p.sendMessage("You are currently in combat for "+ (player.getCombatTime() - System.currentTimeMillis() / 1000)+" seconds"); return true;}

        //then we send them to spawn
        p.teleport(KitPvp.dataManager.data.storage.getSpawn().getLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
        //clear all the assisted players
        player.clearAssisted();

        return true;
    }
}
