package uk.lewisl.kitpvp.types;

import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.util.Maths;

import java.util.Objects;
import java.util.UUID;

public class PvPPlayer {
    UUID uuid;
    long balance;
    long kills;
    long deaths;
    String selectedKit;


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
