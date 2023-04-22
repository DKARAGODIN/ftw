package pro.karagodin.models;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> equippedItems = new ArrayList<>();
    private List<Item> backpackItems = new ArrayList<>();
    private int row = 0;
    private int column = 0;

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

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setCoordinates (int row, int column) {
        this.row = row;
        this.column = column;
    }
}
