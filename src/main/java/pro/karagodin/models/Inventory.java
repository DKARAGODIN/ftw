package pro.karagodin.models;

import java.util.ArrayList;
import java.util.List;

import pro.karagodin.game_engine.Coordinate;

public class Inventory {

    public static final int MAX_EQUIPPED_ITEMS = 10;
    public static final int MAX_STASHED_ITEMS = 25;

    private List<Item> equippedItems = new ArrayList<>();
    private List<Item> backpackItems = new ArrayList<>();
    private int y = 0;
    private int x = 0;

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

    public int getY() {
        return y;
    }

    public void setY(int row) {
        if (row > 6)
            throw new IllegalArgumentException("Inventory coordinates error");

        this.y = row;
    }

    public int getX() {
        return x;
    }

    public void setX(int column) {
        if (column > 4)
            throw new IllegalArgumentException("Inventory coordinates error");

        this.x = column;
    }

    public void setCoordinates(int x, int y) {
        setX(x);
        setY(y);
    }

    public Coordinate getCoordinate() {
        return new Coordinate(x, y);
    }

    public void setCoordinate(Coordinate newCoordinate) {
        setCoordinates(newCoordinate.getX(), newCoordinate.getY());
    }
}
