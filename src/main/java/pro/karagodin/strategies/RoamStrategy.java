package pro.karagodin.strategies;

import java.util.Random;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.actions.Action;
import pro.karagodin.actions.Direction;
import pro.karagodin.actions.MoveAction;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

public class RoamStrategy extends PrimitiveStrategy {
    private final Random randomGenerator = new Random();

    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) {
        return new MoveAction(
                switch (randomGenerator.nextInt(4)) {
                    case 0 -> Direction.Up;
                    case 1 -> Direction.Down;
                    case 2 -> Direction.Left;
                    default -> Direction.Right;
        });
    }

    @Override
    public TextColor getForeground() {
        return TextColor.ANSI.YELLOW;
    }
}
