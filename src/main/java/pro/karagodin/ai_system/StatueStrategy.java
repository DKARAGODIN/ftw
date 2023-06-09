package pro.karagodin.ai_system;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

import java.io.IOException;

public class StatueStrategy implements Strategy {
    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) throws IOException {
        return Action.DoNothing;
    }

    @Override
    public TextColor getForeground() {
        return TextColor.ANSI.BLUE;
    }
}
