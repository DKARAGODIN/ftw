package pro.karagodin.ai_system;

import java.io.IOException;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

public class BifurcateStrategy implements Strategy {

    private final Strategy subStrategy;
    private final int frequencyOfBifurcating;
    private int stepCounter;

    public BifurcateStrategy(Strategy subStrategy, int frequencyOfBifurcating) {
        this.subStrategy = subStrategy;
        this.frequencyOfBifurcating = frequencyOfBifurcating;
        this.stepCounter = 0;
    }

    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) throws IOException {
        if (stepCounter < frequencyOfBifurcating)
            stepCounter++;
        Action resultAction = subStrategy.getNextAction(mobAndCoord, map);
        if (stepCounter == frequencyOfBifurcating) {
            stepCounter = 0;
            return moveToBifurcate(resultAction);
        } else {
            return resultAction;
        }
    }

    private Action moveToBifurcate(Action action) {
        return switch (action) {
            case MoveUp -> Action.BifurcateUp;
            case MoveDown -> Action.BifurcateDown;
            case MoveLeft -> Action.BifurcateLeft;
            case MoveRight -> Action.BifurcateRight;
            default -> action;
        };
    }

    @Override
    public TextColor getForeground() {
        return subStrategy.getForeground();
    }

    @Override
    public Strategy cloneStrategy() {
        return new BifurcateStrategy(subStrategy.cloneStrategy(), frequencyOfBifurcating);
    }
}
