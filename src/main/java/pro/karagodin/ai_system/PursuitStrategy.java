package pro.karagodin.ai_system;

import java.util.Random;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

public class PursuitStrategy implements Strategy {
    private final Random randomGenerator;
    private final double pursuitIndex;

    public PursuitStrategy(double pursuitIndex) {
        this.pursuitIndex = pursuitIndex;
        this.randomGenerator = new Random();
    }

    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) {
        Coordinate playerCoordinate = map.findPlayerCoordinate();
        Action horizontalVector = getHorizontalVector(mobAndCoord.getPosition(), playerCoordinate);
        Action verticalVector = getVerticalVector(mobAndCoord.getPosition(), playerCoordinate);
        double randomDouble = randomGenerator.nextDouble();
        if (horizontalVector == null) {
            if (randomDouble <= 0.25 + 0.75 * pursuitIndex) {
                return verticalVector;
            } else if (randomDouble > 0.25 + 0.75 * pursuitIndex && randomDouble <= 0.5 + 0.5 * pursuitIndex) {
                return getReverseVector(verticalVector);
            } else if (randomDouble > 0.5 + 0.5 * pursuitIndex && randomDouble <= 0.75 + 0.25 * pursuitIndex) {
                return Action.MoveLeft;
            } else {
                return Action.MoveRight;
            }
        } else if (verticalVector == null) {
            if (randomDouble <= 0.25 + 0.75 * pursuitIndex) {
                return horizontalVector;
            } else if (randomDouble > 0.25 + 0.75 * pursuitIndex && randomDouble <= 0.5 + 0.5 * pursuitIndex) {
                return getReverseVector(horizontalVector);
            } else if (randomDouble > 0.5 + 0.5 * pursuitIndex && randomDouble <= 0.75 + 0.25 * pursuitIndex) {
                return Action.MoveUp;
            } else {
                return Action.MoveDown;
            }
        } else {
            if (randomDouble <= 0.25 + 0.25 * pursuitIndex) {
                return horizontalVector;
            } else if (randomDouble > 0.25 + 0.25 * pursuitIndex && randomDouble <= 0.5) {
                return getReverseVector(horizontalVector);
            } else if (randomDouble > 0.5 && randomDouble <= 0.75 + 0.25 * pursuitIndex) {
                return verticalVector;
            } else {
                return getReverseVector(verticalVector);
            }
        }
    }

    private Action getHorizontalVector(Coordinate mobCoordinate, Coordinate playerCoordinate) {
        if (mobCoordinate.getX() == playerCoordinate.getX()) {
            return null;
        } else if (mobCoordinate.getX() > playerCoordinate.getX()) {
            return Action.MoveLeft;
        } else {
            return Action.MoveRight;
        }
    }

    private Action getVerticalVector(Coordinate mobCoordinate, Coordinate playerCoordinate) {
        if (mobCoordinate.getY() == playerCoordinate.getY()) {
            return null;
        } else if (mobCoordinate.getY() > playerCoordinate.getY()) {
            return Action.MoveUp;
        } else {
            return Action.MoveDown;
        }
    }

    private Action getReverseVector(Action vector) {
        return switch (vector) {
            case MoveUp -> Action.MoveDown;
            case MoveDown -> Action.MoveUp;
            case MoveLeft -> Action.MoveRight;
            case MoveRight -> Action.MoveLeft;
            default -> null;
        };
    }

    @Override
    public TextColor getForeground() {
        return TextColor.ANSI.YELLOW;
    }
}
