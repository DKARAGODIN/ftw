package pro.karagodin.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Map cell, stores information about all objects on it
 */
@Getter
@Setter
public class Cell {
    protected Mob unit = null;
    protected Wall wall = null;
    protected Item item = null;
    protected Floor floor;

    public Cell() {
        floor = new Floor();
    }

    public Item pickItem() {
        Item item = this.item;
        this.item = null;
        return item;
    }
}
