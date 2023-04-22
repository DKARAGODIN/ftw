package pro.karagodin.models;

import java.util.Objects;

public class Cell {
    protected Mob unit;
    protected Wall wall;
    protected Floor floor;


    public Mob getUnit() {
        return unit;
    }

    public void setUnit(Mob unit) {
        this.unit = unit;
    }

    public Wall getWall() {
        return wall;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }
}