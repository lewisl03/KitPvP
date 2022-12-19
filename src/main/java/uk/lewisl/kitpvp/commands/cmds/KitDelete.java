package uk.lewisl.kitpvp.commands.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.types.KitItem;

import java.util.ArrayList;

public class KitDelete  implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage("You do not have permissions to perform this command");
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage("Youre console, you can't create a kid L dance kid");
            return true;
        }
        Player p = (Player) sender;



        if(args.length < 1){
            p.sendMessage("You must provide a kit name");
            return true;
        }
        String kitName = args[0];


        if(!KitPvp.dataManager.data.kits.containsKey(kitName)){
            p.sendMessage("This kit does not exists");
            return true;
        }




        KitPvp.dataManager.data.kits.remove(kitName);
        p.sendMessage(kitName+" has been deleted");




        return true;
    }

}
