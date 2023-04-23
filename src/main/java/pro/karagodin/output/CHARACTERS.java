package pro.karagodin.output;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;

public class CHARACTERS {
    public static final TextCharacter HERO_CHAR = TextCharacter.fromCharacter('@', TextColor.ANSI.RED, TextColor.ANSI.GREEN)[0];
    public static final TextCharacter BLACK_EMPTY = TextCharacter.fromCharacter(' ', TextColor.ANSI.BLACK, TextColor.ANSI.BLACK)[0];
    public static final TextCharacter GREY_EMPTY = TextCharacter.fromCharacter(' ', TextColor.ANSI.BLACK, TextColor.ANSI.WHITE)[0];

}
