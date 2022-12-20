package uk.lewisl.kitpvp.commands.cmds;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.types.PvPPlayer;
import uk.lewisl.kitpvp.util.PlayerUtil;
import uk.lewisl.kitpvp.util.Sound;
import uk.lewisl.kitpvp.util.Text;

public class Stats implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player){

            if(strings.length >= 1){
                OfflinePlayer targetPlayer = PlayerUtil.getPlayerFromName(strings[0]);
                if(targetPlayer == null){
                    commandSender.sendMessage(Text.convertString("Target does not exist"));
                    return true;
                }else{
                    PvPPlayer p = KitPvp.dataManager.data.getPlayer(targetPlayer.getPlayer());
                    commandSender.sendMessage(targetPlayer.getName()+"'s Stats\n" +
                            "Kills: "+ p.getKills()+"\n" +
                            "Deaths: "+p.getDeaths()+"\n");
                    return true;
                }

            }



            Player p = (Player) commandSender;
            PvPPlayer player = KitPvp.dataManager.data.getPlayer(p);
            p.sendMessage(Text.convertString(p.getName()+"'s Stats\n" +
                    "Kills: "+ player.getKills()+"\n" +
                    "Deaths: "+player.getDeaths()+"\n"));

            Sound.playSuccessSound(p);
            return true;
        }





        return false;


    }
}
