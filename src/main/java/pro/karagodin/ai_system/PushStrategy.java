package pro.karagodin.ai_system;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

public class PushStrategy implements Strategy {

    private final Strategy originStrategy;
    private int durationInMoveNumber;
    private final Action move;

    public PushStrategy(Strategy originStrategy, Action move, int durationInMoveNumber) {
        this.originStrategy = originStrategy;
        this.durationInMoveNumber = durationInMoveNumber;
        this.move = move;
    }

    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) {
        durationInMoveNumber--;
        return move;
    }

    @Override
    public Strategy nextStrategy() {
        return durationInMoveNumber > 0 ? this : originStrategy;
    }

    @Override
    public TextColor getForeground() {
        return TextColor.ANSI.BLUE;
    }
}
