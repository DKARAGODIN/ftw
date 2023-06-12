package pro.karagodin.models;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.output.CIDrowable;

public class Floor implements CIDrowable {
    protected LowerItem item = null;

    public LowerItem getItem() {
        return item;
    }

    /** remove smallThing from floor and return it */
    public LowerItem pickItem() {
        var pickedItem = item;
        this.item = null;
        return pickedItem;
    }

    public boolean hasItem() {
        return item != null;
    }

    public void setItem(LowerItem item) {
        this.item = item;
    }

    @Override
    public char getView() {
        return 0;
    }

    @Override
    public TextColor getForeground() {
        return null;
    }

    @Override
    public TextColor getBackground() {
        return TextColor.ANSI.BLACK;
    }
}
