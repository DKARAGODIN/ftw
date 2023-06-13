package pro.karagodin.models;

import java.util.ArrayList;
import java.util.List;

import pro.karagodin.game_engine.Coordinate;

public class Inventory {

    public static final int MAX_EQUIPPED_ITEMS = 10;
    public static final int MAX_STASHED_ITEMS = 25;

    private List<LootItem> equippedLoot = new ArrayList<>();
    private List<LootItem> stashedLoot = new ArrayList<>();
    private int y = 0;
    private int x = 0;

    public List<LootItem> getEquippedSmallThings() {
        return equippedLoot;
    }

    public List<LootItem> getStashedSmallThings() {
        return stashedLoot;
    }

    public void addLootToStash(LootItem smallThing) {
        stashedLoot.add(smallThing);
    }

    public void removeLootFromStash(LootItem smallThing) {
        stashedLoot.remove(smallThing);
    }

    public void equipLoot(LootItem smallThing) {
        if (!stashedLoot.contains(smallThing)) {
            throw new IllegalArgumentException("SmallThing not found in backpack");
        }
        stashedLoot.remove(smallThing);
        equippedLoot.add(smallThing);
    }

    public void unequipLoot(LootItem smallThing) {
        if (!equippedLoot.contains(smallThing)) {
            throw new IllegalArgumentException("SmallThing not found in equipped items");
        }
        equippedLoot.remove(smallThing);
        stashedLoot.add(smallThing);
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

    public void clear() {
        this.equippedLoot.clear();
        this.stashedLoot.clear();
    }
}
