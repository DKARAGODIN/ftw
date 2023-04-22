package pro.karagodin.models;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    protected List<Item> equippedItems = new ArrayList<>();
    protected List<Item> backpackItems = new ArrayList<>();

    public List<Item> getEquippedItems() {
        return equippedItems;
    }

    public List<Item> getBackpackItems() {
        return backpackItems;
    }

    public void addItemToBackpack(Item item) {
        backpackItems.add(item);
    }

    public void removeItemFromBackpack(Item item) {
        backpackItems.remove(item);
    }

    public void equipItem(Item item) {
        if (!backpackItems.contains(item)) {
            throw new IllegalArgumentException("Item not found in backpack");
        }
        backpackItems.remove(item);
        equippedItems.add(item);
    }

    public void unequipItem(Item item) {
        if (!equippedItems.contains(item)) {
            throw new IllegalArgumentException("Item not found in equipped items");
        }
        equippedItems.remove(item);
        backpackItems.add(item);
    }
}
