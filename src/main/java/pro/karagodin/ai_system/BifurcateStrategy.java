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
        if (stepCounter == frequencyOfBifurcating && (action instanceof MoveAction)) {
            stepCounter = 0;
            return new BifurcateAction(((MoveAction) action).getDirection());
        } else {
            return action;
        }
    }
}
