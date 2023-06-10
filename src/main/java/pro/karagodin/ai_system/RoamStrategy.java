package pro.karagodin.ai_system;

import java.util.Random;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

public class RoamStrategy implements Strategy {
    private final Random randomGenerator = new Random();

    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) {
        return switch (randomGenerator.nextInt(4)) {
            case 0 -> Action.MoveUp;
            case 1 -> Action.MoveDown;
            case 2 -> Action.MoveLeft;
            default -> Action.MoveRight;
        };
    }

    @Override
    public TextColor getForeground() {
        return TextColor.ANSI.YELLOW;
    }
}
