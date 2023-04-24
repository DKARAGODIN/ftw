package pro.karagodin.models;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.output.CIDrowable;

public class Wall implements CIDrowable {
    public char getView(){
        return ' ';
    }

    @Override
    public TextColor getForeground() {
        return null;
    }

    @Override
    public TextColor getBackground() {
        return TextColor.ANSI.WHITE;
    }

}
