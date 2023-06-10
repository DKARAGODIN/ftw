package pro.karagodin.models;

import java.util.ArrayList;
import java.util.List;

import pro.karagodin.game_engine.Coordinate;

public class Inventory {

    public static final int MAX_EQUIPPED_ITEMS = 10;
    public static final int MAX_STASHED_ITEMS = 25;

    private List<SmallThing> equippedSmallThings = new ArrayList<>();
    private List<SmallThing> stashedSmallThings = new ArrayList<>();
    private int y = 0;
    private int x = 0;

    public List<SmallThing> getEquippedSmallThings() {
        return equippedSmallThings;
    }

    public List<SmallThing> getStashedSmallThings() {
        return stashedSmallThings;
    }

    public void addSmallThingToStash(SmallThing smallThing) {
        stashedSmallThings.add(smallThing);
    }

    public void removeSmallThingFromStash(SmallThing smallThing) {
        stashedSmallThings.remove(smallThing);
    }

    public void equipSmallThing(SmallThing smallThing) {
        if (!stashedSmallThings.contains(smallThing)) {
            throw new IllegalArgumentException("SmallThing not found in backpack");
        }
        stashedSmallThings.remove(smallThing);
        equippedSmallThings.add(smallThing);
    }

    public void unequipSmallThing(SmallThing smallThing) {
        if (!equippedSmallThings.contains(smallThing)) {
            throw new IllegalArgumentException("SmallThing not found in equipped items");
        }
        equippedSmallThings.remove(smallThing);
        stashedSmallThings.add(smallThing);
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
