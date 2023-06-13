package pro.karagodin.ai_system;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

public class PushStrategy extends EffectiveStrategy {

    private int durationInMoveNumber;

    public PushStrategy(Strategy subStrategy, Action move, int durationInMoveNumber) {
        this.subStrategy = subStrategy;
        this.effectStrategy = new Strategy() {
            @Override
            public Action getNextAction(MobWithPosition mobAndCoord, Map map) {
                return move;
            }

            @Override
            public TextColor getForeground() {
                return TextColor.ANSI.BLUE;
            }
        };
        this.durationInMoveNumber = durationInMoveNumber;
    }

    @Override
    protected void updateState(MobWithPosition mobAndCoord, Map map) {
        durationInMoveNumber--;
    }

    @Override
    protected boolean isEndOfEffect() {
        return durationInMoveNumber <= 0;
    }
}
