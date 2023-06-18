package pro.karagodin.strategies;

import java.util.Random;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.actions.Action;
import pro.karagodin.actions.Direction;
import pro.karagodin.actions.DoNothingAction;
import pro.karagodin.actions.MoveAction;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

public class PursuitStrategy extends PrimitiveStrategy {
    private final Random randomGenerator;
    private final double pursuitIndex;

    public PursuitStrategy(double pursuitIndex) {
        this.pursuitIndex = pursuitIndex;
        this.randomGenerator = new Random();
    }

    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) {
        Coordinate playerCoordinate = map.findPlayerCoordinate();
        if (playerCoordinate == null) {
            return new DoNothingAction();
        }
        Direction horizontalVector = getHorizontalVector(mobAndCoord.getPosition(), playerCoordinate);
        Direction verticalVector = getVerticalVector(mobAndCoord.getPosition(), playerCoordinate);
        double randomDouble = randomGenerator.nextDouble();
        if (horizontalVector == null) {
            if (randomDouble <= 0.25 + 0.75 * pursuitIndex) {
                return new MoveAction(verticalVector);
            } else if (randomDouble > 0.25 + 0.75 * pursuitIndex && randomDouble <= 0.5 + 0.5 * pursuitIndex) {
                return new MoveAction(getReverseVector(verticalVector));
            } else if (randomDouble > 0.5 + 0.5 * pursuitIndex && randomDouble <= 0.75 + 0.25 * pursuitIndex) {
                return new MoveAction(Direction.Left);
            } else {
                return new MoveAction(Direction.Right);
            }
        } else if (verticalVector == null) {
            if (randomDouble <= 0.25 + 0.75 * pursuitIndex) {
                return new MoveAction(horizontalVector);
            } else if (randomDouble > 0.25 + 0.75 * pursuitIndex && randomDouble <= 0.5 + 0.5 * pursuitIndex) {
                return new MoveAction(getReverseVector(horizontalVector));
            } else if (randomDouble > 0.5 + 0.5 * pursuitIndex && randomDouble <= 0.75 + 0.25 * pursuitIndex) {
                return new MoveAction(Direction.Up);
            } else {
                return new MoveAction(Direction.Down);
            }
        } else {
            if (randomDouble <= 0.25 + 0.25 * pursuitIndex) {
                return new MoveAction(horizontalVector);
            } else if (randomDouble > 0.25 + 0.25 * pursuitIndex && randomDouble <= 0.5) {
                return new MoveAction(getReverseVector(horizontalVector));
            } else if (randomDouble > 0.5 && randomDouble <= 0.75 + 0.25 * pursuitIndex) {
                return new MoveAction(verticalVector);
            } else {
                return new MoveAction(getReverseVector(verticalVector));
            }
        }
    }

    private Direction getHorizontalVector(Coordinate mobCoordinate, Coordinate playerCoordinate) {
        if (mobCoordinate.getX() == playerCoordinate.getX()) {
            return null;
        } else if (mobCoordinate.getX() > playerCoordinate.getX()) {
            return Direction.Left;
        } else {
            return Direction.Right;
        }
    }

    private Direction getVerticalVector(Coordinate mobCoordinate, Coordinate playerCoordinate) {
        if (mobCoordinate.getY() == playerCoordinate.getY()) {
            return null;
        } else if (mobCoordinate.getY() > playerCoordinate.getY()) {
            return Direction.Up;
        } else {
            return Direction.Down;
        }
    }

    private Direction getReverseVector(Direction vector) {
        return switch (vector) {
            case Up -> Direction.Down;
            case Down -> Direction.Up;
            case Left -> Direction.Right;
            case Right -> Direction.Left;
        };
    }

    @Override
    public TextColor getForeground() {
        return TextColor.ANSI.YELLOW;
    }
}
