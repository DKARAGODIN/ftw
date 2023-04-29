package pro.karagodin.models;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.output.CIDrowable;

public class Floor implements CIDrowable {
    protected Item item;

    public Item getItem() {
        return item;
    }

    /** remove item from floor and return it */
    public Item pickItem() {
        var pickedItem = this.item;
        this.item = null;
        return pickedItem;
    }

    public boolean hasItem() {
        return item != null;
    }

    public void setItem(Item item) {
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
