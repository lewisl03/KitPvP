package uk.lewisl.kitpvp.commands;

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
import uk.lewisl.kitpvp.types.items.EnchantedItem;
import uk.lewisl.kitpvp.types.items.PotionItem;

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

        if (args.length < 1) {
            String builder = "";

            for (String key : KitPvp.data.kits.keySet()) {
                builder += key +", ";
            }

             p.sendMessage(builder);

            return true;
        }

        String kitName = args[0];

        if(!KitPvp.data.kits.containsKey(kitName)){
            p.sendMessage("Kit does not exist");
            return true;
        }




        for(KitItem kitItem :  KitPvp.data.kits.get(kitName)){

            if(kitItem == null) continue;

            if(kitItem.slot >= 100){

                //set their armour as we can't loop through it :( well we can but i'm lazy atm

                ItemStack is = new ItemStack(kitItem.item.getMaterial(),kitItem.item.getAmount(),kitItem.item.getDamage(),kitItem.item.getData());

                if(kitItem.item instanceof EnchantedItem){
                    EnchantedItem enchantedItem = (EnchantedItem) kitItem.item;
                    is.addEnchantments(enchantedItem.getEnchantments());
                }

                if(kitItem.slot == 100) {

                    p.getInventory().setBoots(new ItemStack(is));
                    continue;
                }

                if(kitItem.slot == 101) {
                    p.getInventory().setLeggings(is);
                    continue;
                }

                if(kitItem.slot == 102) {

                    p.getInventory().setChestplate(is);
                    continue;
                }

                if(kitItem.slot == 103) {

                    p.getInventory().setHelmet(is);
                    continue;
                }

            }
            ItemStack item = new ItemStack(kitItem.item.getMaterial(), kitItem.item.getAmount(), kitItem.item.getDamage(), kitItem.item.getData());

            if(kitItem.item.getMaterial().equals(Material.POTION)){

                PotionItem potionItem = (PotionItem) kitItem.item;

                PotionMeta potionmeta = (PotionMeta) item.getItemMeta();
                Potion pot = new Potion(Potion.fromItemStack(item).getType(), potionItem.getPotionLevel(),potionItem.isSplashPotion());
                pot.apply(item);
            }


            if(kitItem.item instanceof EnchantedItem){
                EnchantedItem enchantedItem = (EnchantedItem) kitItem.item;
                item.addEnchantments(enchantedItem.getEnchantments());
            }





            p.getInventory().setItem(kitItem.slot, item);

        }



        new BukkitRunnable() {
            @Override
            public void run() {
                p.updateInventory();
            }
        }.runTaskLater(KitPvp.getPlugin(KitPvp.class), 0);



        p.sendMessage("You have been given kit "+ kitName);















        return true;
    }








}













