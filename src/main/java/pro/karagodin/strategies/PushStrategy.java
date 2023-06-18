package pro.karagodin.strategies;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.actions.Action;
import pro.karagodin.actions.Direction;
import pro.karagodin.actions.MoveAction;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

public class PushStrategy extends EffectiveStrategy {

    private int durationInMoveNumber;

    public PushStrategy(Strategy subStrategy, Direction direction, int durationInMoveNumber) {
        this.subStrategy = subStrategy;
        this.effectStrategy = new PrimitiveStrategy() {
            @Override
            public Action getNextAction(MobWithPosition mobAndCoord, Map map) {
                return new MoveAction(direction);
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
