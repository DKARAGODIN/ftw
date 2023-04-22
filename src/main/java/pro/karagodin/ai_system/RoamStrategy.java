package pro.karagodin.ai_system;

import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

import java.util.Random;

public class RoamStrategy implements Strategy {

    private final Random randomGenerator = new Random();

    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) {
        switch (randomGenerator.nextInt(4)) {
            case 0:
                return Action.MoveLeft;
            case 1:
                return Action.MoveRight;
            case 2:
                return Action.MoveUp;
            default:
                return Action.MoveDown;
        }
    }
}
