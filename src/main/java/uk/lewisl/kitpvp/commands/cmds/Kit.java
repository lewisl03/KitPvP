package uk.lewisl.kitpvp.commands.cmds;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.scheduler.BukkitRunnable;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.types.KitItem;
import uk.lewisl.kitpvp.types.PvPPlayer;
import uk.lewisl.kitpvp.types.items.EnchantedItem;
import uk.lewisl.kitpvp.types.items.PotionItem;
import uk.lewisl.kitpvp.util.PlayerUtil;

public class Kit implements CommandExecutor {


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
        PvPPlayer player = KitPvp.dataManager.data.getPlayer(p);

        if (args.length < 1) {
            String builder = "";

            for (String key : KitPvp.dataManager.data.kits.keySet()) {
                builder += key +", ";
            }

             p.sendMessage(builder);

            return true;
        }

        String kitName = args[0];
        player.giveKitAsync(kitName);
        p.sendMessage("You have been given kit "+ kitName);
        player.setSelectedKit(kitName);
        player.setHasKit(true);

















        return true;
    }








}













