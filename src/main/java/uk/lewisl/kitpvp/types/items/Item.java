package uk.lewisl.kitpvp.types.items;

import org.bukkit.Material;

public class Item {

    Material material;
    int amount;
    short damage;
    Byte data;


    public Item(Material material, int amount, short damage, Byte data) {
        this.material = material;
        this.amount = amount;
        this.damage = damage;
        this.data = data;

    }

    public Material getMaterial() {
        return material;
    }

    public int getAmount() {
        return amount;
    }

    public short getDamage() {
        return damage;
    }

    public Byte getData() {
        return data;
    }


}



