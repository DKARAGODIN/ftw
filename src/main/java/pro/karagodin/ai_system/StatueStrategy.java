package pro.karagodin.ai_system;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

public class StatueStrategy extends PrimitiveStrategy {
    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) {
        return Action.DoNothing;
    }

    @Override
    public TextColor getForeground() {
        return TextColor.ANSI.BLUE;
    }
}
