package pro.karagodin.models;

import lombok.Getter;
import lombok.Setter;
import pro.karagodin.output.CIDrowable;

/**
 * Map cell, stores information about all objects on it
 */
@Getter
@Setter
public class Cell implements CIDrowable {
    protected Mob unit = null;
    protected Wall wall = null;
    protected Floor floor;

    public Cell(){
        floor = new Floor();
    }

    @Override
    public char getView() {
        if(unit != null)
            return unit.getView();
        if(floor.getItem() != null)
            return floor.getItem().getView();
        return ' ';
    }
}