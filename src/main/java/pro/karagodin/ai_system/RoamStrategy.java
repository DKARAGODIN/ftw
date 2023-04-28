package pro.karagodin.ai_system;

import java.util.Random;

import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

public class RoamStrategy implements Strategy {
    private final Random randomGenerator = new Random();

    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) {
        switch (randomGenerator.nextInt(4)) {
            case 0:
                return Action.MoveUp;
            case 1:
                return Action.MoveDown;
            case 2:
                return Action.MoveLeft;
            default:
                return Action.MoveRight;
        }
    }

    @Override
    public TypeOfStrategy getTypeOfStrategy() {
        return TypeOfStrategy.PassiveStrategy;
    }
}
