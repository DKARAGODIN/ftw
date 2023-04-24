package pro.karagodin.models;

import com.googlecode.lanterna.TextColor;
import lombok.Getter;
import lombok.Setter;
import pro.karagodin.output.CIDrowable;

/**
 * A game object that has no behavior but affects the player's characteristics
 */
@Setter
@Getter
public class Item implements CIDrowable {
    protected int hpIncrement = 0;
    protected int attackIncrement = 0;
    protected char view = '?';
    public char getView(){
        return view;
    }

    @Override
    public TextColor getForeground() {
        return TextColor.ANSI.GREEN;
    }

    @Override
    public TextColor getBackground() {
        return null;
    }
}
