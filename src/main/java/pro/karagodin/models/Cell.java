package pro.karagodin.models;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.output.CIDrowable;

/**
 * Map cell, stores information about all objects on it
 */
public class Cell implements CIDrowable {
    private final Map map;
    private final Coordinate coordinate;
    private final Map.RawCell rawCell;

    public Cell(Map map, Coordinate coordinate, Map.RawCell rawCell) {
        this.map = map;
        this.coordinate = coordinate;
        this.rawCell = rawCell;
    }

    public Mob getUnit() {
        return rawCell.getUnit();
    }

    public void setUnit(Mob unit) {
        rawCell.setUnit(unit);
        if (unit instanceof Player) {
            map.setPlayerCoordinate(coordinate);
        }
    }

    public Wall getWall() {
        return rawCell.getWall();
    }

    public void setWall(Wall wall) {
        rawCell.setWall(wall);
    }

    public Floor getFloor() {
        return rawCell.getFloor();
    }

    public void setFloor(Floor floor) {
        rawCell.setFloor(floor);
    }

    @Override
    public char getView() {
        if (getUnit() != null)
            return getUnit().getView();
        if (getFloor().hasItem())
            return getFloor().getItem().getView();
        if (getWall() != null)
            return getWall().getView();
        return ' ';
    }

    @Override
    public TextColor getForeground() {
        if (getUnit() != null)
            return getUnit().getForeground();
        if (getFloor().hasItem())
            return getFloor().getItem().getForeground();
        if (getWall() != null)
            return getWall().getForeground();
        return TextColor.ANSI.WHITE;
    }

    @Override
    public TextColor getBackground() {
        return getFloor().getBackground();
    }

    public TextCharacter asCharacter() {
        return new TextCharacter(getView(), getForeground(), getBackground());
    }
}
