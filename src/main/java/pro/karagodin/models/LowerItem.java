package pro.karagodin.models;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.game_engine.GameDiff;
import pro.karagodin.game_logic.Judge;
import pro.karagodin.output.CIDrowable;

/**
 * Base interface for object that player can interact with
 */
public interface LowerItem extends CIDrowable {
    @Override
    default TextColor getBackground() {
        return null;
    }

    GameDiff use(Judge judge);
}
