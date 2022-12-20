package uk.lewisl.kitpvp.types;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.scheduler.BukkitRunnable;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.commands.cmds.Kit;
import uk.lewisl.kitpvp.types.items.EnchantedItem;
import uk.lewisl.kitpvp.types.items.PotionItem;
import uk.lewisl.kitpvp.util.Maths;
import uk.lewisl.kitpvp.util.PlayerUtil;

import java.util.Objects;
import java.util.UUID;

public class PvPPlayer {
    UUID uuid;
    long balance;
    long kills;
    long deaths;
    String selectedKit;
    boolean hasKit;


    public PvPPlayer(UUID uuid, long balance, long kills, long deaths) {
        this.uuid = uuid;
        this.balance = balance;
        this.kills = kills;
        this.deaths = deaths;
    }

    public UUID getUUID() {
        return uuid;
    }

    public long getPlayerBalance(){
        return balance;
    }


    public String getPlayerStringBalance(){
        return Maths.longComma(balance);

    }

    public boolean playerHasBalance(long amount){
        return balance >= amount;
    }

    public boolean subtractBal(long amount){
        if(getPlayerBalance() >= amount ){
           balance -= amount;
            KitPvp.mysql.removeBalance(uuid, amount);
            return  true;
        }
        return false;
    }

    public boolean addBal(long amount){
        if(balance + amount <= KitPvp.configManager.getConfig().getLong("moneySettings.maxBalance")){
            balance += amount;
            KitPvp.mysql.addBalance(uuid, amount);
            return true;
        }
        return false;
    }

    public void addKill(long amount){
        kills += amount;
        KitPvp.mysql.addKill(uuid, amount);
    }

    public void addDeath(long amount){
        deaths += amount;
        KitPvp.mysql.addDeath(uuid, amount);
    }

    public long getKills() {
        return kills;
    }

    public long getDeaths() {
        return deaths;
    }

    public void save(){
        KitPvp.mysql.savePlayer(uuid);
    }

    public boolean isCombatTagged(){
       return KitPvp.dataManager.data.combatTag.containsKey(uuid);
    }
    public void setCombatTag(){
        //add a combat time for x seconds * by 1000 to convert milli seconds to seconds
      KitPvp.dataManager.data.combatTag.put(uuid, System.currentTimeMillis() + (KitPvp.configManager.getConfig().getLong("combatTag.TagTime") * 1000));
    }
    public long getCombatTime(){
       return KitPvp.dataManager.data.combatTag.get(uuid);
    }

    public boolean isPlayerBypass(){
        return KitPvp.dataManager.data.storage.bypass.contains(uuid);
    }
    public void setPlayerBypass(boolean enabled){
        if(enabled)
            KitPvp.dataManager.data.storage.bypass.add(uuid);
        else
            KitPvp.dataManager.data.storage.bypass.remove(uuid);
    }

    public String getSelectedKit() {
        return selectedKit;
    }

    public boolean hasKit() {
        return hasKit;
    }

    public Player getPlayer(){
       return Bukkit.getPlayer(uuid);
    }

    public void setSelectedKit(String selectedKit) {
        this.selectedKit = selectedKit;
    }

    public void setHasKit(boolean hasKit) {
        this.hasKit = hasKit;
    }

    public boolean giveKitAsync(String kitName){
        if(!KitPvp.dataManager.data.kits.containsKey(kitName)){
            getPlayer().sendMessage("Kit does not exist");
            return false;
        }


        Bukkit.getScheduler().runTaskAsynchronously(KitPvp.getPlugin(), new Runnable() {
            @Override
            public void run() {

        for(KitItem kitItem :  KitPvp.dataManager.data.kits.get(kitName)){

            if(kitItem == null) continue;

            if(kitItem.slot >= 100){

                //set their armour as we can't loop through it :( well we can but i'm lazy atm

                ItemStack is = new ItemStack(kitItem.item.getMaterial(),kitItem.item.getAmount(),kitItem.item.getDamage(),kitItem.item.getData());

                if(kitItem.item instanceof EnchantedItem){
                    EnchantedItem enchantedItem = (EnchantedItem) kitItem.item;
                    is.addEnchantments(enchantedItem.getEnchantments());
                }

                if(kitItem.slot == 100) {

                    getPlayer().getInventory().setBoots(new ItemStack(is));
                    continue;
                }

                if(kitItem.slot == 101) {
                    getPlayer().getInventory().setLeggings(is);
                    continue;
                }

                if(kitItem.slot == 102) {

                    getPlayer().getInventory().setChestplate(is);
                    continue;
                }

                if(kitItem.slot == 103) {

                    getPlayer().getInventory().setHelmet(is);
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

            getPlayer().getInventory().setItem(kitItem.slot, item);

        }




         getPlayer().updateInventory();



            }
        });

        return true;

    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PvPPlayer player = (PvPPlayer) o;
        return uuid.equals(player.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
