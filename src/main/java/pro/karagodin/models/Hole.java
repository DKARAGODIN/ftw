package pro.karagodin.models;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.game_engine.GameDiff;
import pro.karagodin.game_logic.Judge;

public class Hole implements LowerItem {
    @Override
    public char getView() {
        return 'O';
    }

    @Override
    public TextColor getForeground() {
        return TextColor.ANSI.BLUE_BRIGHT;
    }

    @Override
    public GameDiff use(Judge judge) {
        return judge.useHole(this);
    }
}
