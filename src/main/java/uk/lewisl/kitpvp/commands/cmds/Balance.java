package uk.lewisl.kitpvp.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.types.PvPPlayer;
import uk.lewisl.kitpvp.util.Sound;
import uk.lewisl.kitpvp.util.Text;

public class Balance implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
/*
        if(commandSender instanceof Player){

            if(strings.length >= 1){
                Player targetPlayer = Bukkit.getPlayer(strings[0]);
                if(targetPlayer == null){
                    commandSender.sendMessage(Text.convertString(KitPvp.configManager.getConfig().getString("messages.targetNotExist")));
                    return true;
                }

            }



            Player p = (Player) commandSender;
            PvPPlayer playerBalance = PvPPlayer.balanceCache.getPlayer(p);
            p.sendMessage(Text.convertString(KitPvp.configManager.getConfig().getString("messages.balance")).replaceAll("%amount%", playerBalance.getPlayerStringBalance()));

            Sound.playSuccessSound(p);
            return true;
        }




 */
        return false;


    }


}