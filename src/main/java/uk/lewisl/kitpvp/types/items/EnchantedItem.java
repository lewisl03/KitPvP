package uk.lewisl.kitpvp.types.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.Map;

public class EnchantedItem extends Item {
    public EnchantedItem(Material material, int amount, short damage, Byte data,  Map<Enchantment, Integer> enchantments) {
        super(material, amount, damage, data);
        this.enchantments =  enchantments;
    }


    Map<Enchantment, Integer> enchantments;

    public Map<Enchantment, Integer>  getEnchantments() {
        return enchantments;
    }
}
