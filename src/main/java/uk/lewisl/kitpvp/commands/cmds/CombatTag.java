package uk.lewisl.kitpvp.commands.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.types.PvPPlayer;

public class CombatTag implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){commandSender.sendMessage("This command can only be used by players");  return true; }

        PvPPlayer player = KitPvp.dataManager.data.getPlayer((Player) commandSender);

        commandSender.sendMessage(player.isCombatTagged() ? "You're in combat for "+player.getCombatTime()+" seconds" : "You're not in combat");

        return true;
    }
}
