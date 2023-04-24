package pro.karagodin.ai_system;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

import java.util.Random;

public class RoamStrategy implements Strategy {
    private final Random RANDOM = new Random();

    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) {
        switch (RANDOM.nextInt(4)) {
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
    public TextColor getForeground() {
        return TextColor.ANSI.YELLOW;
    }
}
