package pro.karagodin.models;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
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
    protected Floor floor = new Floor();

    @Override
    public char getView() {
        if (unit != null)
            return unit.getView();
        if (floor.hasItem())
            return floor.getItem().getView();
        if (wall != null)
            return wall.getView();
        return ' ';
    }

    @Override
    public TextColor getForeground() {
        if (unit != null)
            return unit.getForeground();
        if (floor.hasItem())
            return floor.getItem().getForeground();
        if (wall != null)
            return wall.getForeground();
        return TextColor.ANSI.WHITE;
    }

    @Override
    public TextColor getBackground() {
        return floor.getBackground();
    }

    public TextCharacter asCharacter() {
        return new TextCharacter(getView(), getForeground(), getBackground());
    }
}
