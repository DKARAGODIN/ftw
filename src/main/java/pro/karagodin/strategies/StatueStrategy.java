package pro.karagodin.strategies;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.actions.Action;
import pro.karagodin.actions.DoNothingAction;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

public class StatueStrategy extends PrimitiveStrategy {
    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) {
        return new DoNothingAction();
    }

    @Override
    public TextColor getForeground() {
        return TextColor.ANSI.BLUE;
    }
}
