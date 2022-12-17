package uk.lewisl.kitpvp.types.items;

import org.bukkit.Material;
import uk.lewisl.kitpvp.types.items.Item;

public class PotionItem extends Item {
    public PotionItem(Material material, int amount, short damage, Byte data, boolean splashPotion, int potionLevel) {
        super(material, amount, damage, data);
        this.splashPotion = splashPotion;
        this.potionLevel = potionLevel;
    }

    boolean splashPotion;
    int potionLevel;

    public boolean isSplashPotion() {
        return splashPotion;
    }



    public int getPotionLevel() {
        return potionLevel;
    }



}
