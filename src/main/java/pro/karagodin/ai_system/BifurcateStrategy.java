package pro.karagodin.ai_system;

import java.io.IOException;

import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

public class BifurcateStrategy extends DecoratingStrategy {

    private final int frequencyOfBifurcating;
    private int stepCounter;

    public BifurcateStrategy(Strategy subStrategy, int frequencyOfBifurcating) {
        this.subStrategy = subStrategy;
        this.frequencyOfBifurcating = frequencyOfBifurcating;
        this.stepCounter = 0;
    }

    @Override
    protected Strategy constructor(Strategy subStrategy) {
        return new BifurcateStrategy(subStrategy, frequencyOfBifurcating);
    }

    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) throws IOException {
        if (stepCounter < frequencyOfBifurcating)
            stepCounter++;
        Action action = subStrategy.getNextAction(mobAndCoord, map);
        if (stepCounter == frequencyOfBifurcating && isMoveAction(action)) {
            stepCounter = 0;
            return moveToBifurcate(action);
        } else {
            return action;
        }
    }

    private boolean isMoveAction(Action action) {
        return switch (action) {
            case MoveUp, MoveDown, MoveLeft, MoveRight -> true;
            default -> false;
        };
    }

    private Action moveToBifurcate(Action action) {
        return switch (action) {
            case MoveUp -> Action.BifurcateUp;
            case MoveDown -> Action.BifurcateDown;
            case MoveLeft -> Action.BifurcateLeft;
            case MoveRight -> Action.BifurcateRight;
            default -> null;
        };
    }
}
