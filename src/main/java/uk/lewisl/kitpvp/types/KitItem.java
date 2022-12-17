package uk.lewisl.kitpvp.types;

import uk.lewisl.kitpvp.types.items.Item;

public class KitItem {
    public int slot;
    public Item item;



    public KitItem(int slot, Item item) {
        this.slot = slot;
        this.item = item;
    }
}
