package uk.lewisl.kitpvp.commands.cmds;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.types.items.EnchantedItem;
import uk.lewisl.kitpvp.types.items.Item;
import uk.lewisl.kitpvp.types.KitItem;
import uk.lewisl.kitpvp.types.items.PotionItem;

import java.util.ArrayList;

public class KitCreate implements CommandExecutor {


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


        if(KitPvp.dataManager.data.kits.containsKey(kitName)){
            p.sendMessage("This kit already exists, please delete it first");
            return true;
        }





        ArrayList<KitItem> kitItems = new ArrayList<>();

        for (int i = 0; i < p.getInventory().getSize(); i++){
            ItemStack item = p.getInventory().getItem(i);
            if(item == null) continue;

                if (item.getType().equals(Material.POTION)) {
                    Potion potion = Potion.fromItemStack(p.getInventory().getItem(i));

                    kitItems.add(new KitItem(i, new PotionItem(item.getType(), item.getAmount(), item.getDurability(), item.getData().getData(), potion.isSplash(), potion.getLevel())));
                    continue;
                }
                //might null error
                if(item.getEnchantments().size() != 0){
                    kitItems.add(new KitItem(i, new EnchantedItem(item.getType(), item.getAmount(), item.getDurability(), item.getData().getData(), item.getEnchantments())));
                    continue;
                }



                kitItems.add(new KitItem(i, new Item(item.getType(), item.getAmount(), item.getDurability(), item.getData().getData())));

        }






//need to enchant for armour
        ItemStack helm = p.getInventory().getHelmet();
        if(helm != null) {
            if(helm.getEnchantments().size() != 0)
                kitItems.add(new KitItem(103,new EnchantedItem(helm.getType(), helm.getAmount(), helm.getDurability(), helm.getData().getData(), helm.getEnchantments())));
            else
            kitItems.add(new KitItem(103, new Item(helm.getType(), helm.getAmount(), helm.getDurability(), helm.getData().getData())));
        }

        ItemStack chest = p.getInventory().getChestplate();
        if(chest != null) {
            if (chest.getEnchantments().size() != 0)
                kitItems.add(new KitItem(102, new EnchantedItem(chest.getType(), chest.getAmount(), chest.getDurability(), chest.getData().getData(), chest.getEnchantments())));
            else
                kitItems.add(new KitItem(102, new Item(chest.getType(), chest.getAmount(), chest.getDurability(), chest.getData().getData())));
        }

        ItemStack leg = p.getInventory().getLeggings();
        if(leg != null) {
            if (leg.getEnchantments().size() != 0)
                kitItems.add(new KitItem(101, new EnchantedItem(leg.getType(), leg.getAmount(), leg.getDurability(), leg.getData().getData(), leg.getEnchantments())));
            else
                kitItems.add(new KitItem(101, new Item(leg.getType(), leg.getAmount(), leg.getDurability(), leg.getData().getData())));
        }

        ItemStack boot = p.getInventory().getBoots();
        if(boot != null) {
            if (boot.getEnchantments().size() != 0)
                kitItems.add(new KitItem(100, new EnchantedItem(boot.getType(), boot.getAmount(), boot.getDurability(), boot.getData().getData(), boot.getEnchantments())));
            else
                kitItems.add(new KitItem(100, new Item(boot.getType(), boot.getAmount(), boot.getDurability(), boot.getData().getData())));
        }




        KitPvp.dataManager.data.kits.put(kitName, kitItems);




        p.sendMessage("You've created kit "+ kitName);

        return true;
    }

}
