package pro.karagodin.models;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.game_engine.GameDiff;
import pro.karagodin.game_logic.Judge;

/**
 * A game object that modifies hero stats permanently without stash
 */
public class ConsumableItem implements LowerItem {

    private final int hpBoost;

    public ConsumableItem(int hpBoost) {
        this.hpBoost = hpBoost;
    }

    @Override
    public GameDiff use(Judge judge) {
        return judge.useConsumableItem(this);
    }

    @Override
    public char getView() {
        return '+';
    }

    @Override
    public TextColor getForeground() {
        return TextColor.ANSI.MAGENTA;
    }

    public int getHpBoost() {
        return hpBoost;
    }
}
