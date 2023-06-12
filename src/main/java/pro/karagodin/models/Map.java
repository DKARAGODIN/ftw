package pro.karagodin.models;

import java.util.List;
import java.util.function.UnaryOperator;

import lombok.Getter;
import lombok.Setter;
import pro.karagodin.ai_system.Action;
import pro.karagodin.game_engine.Coordinate;

/**
 * Play area: a two-dimensional array on which the positions of objects (walls, passages to other maps) are fixed.
 * Maps are generated automatically or loaded from a file.
 */
public class Map {
    @Getter
    @Setter
    public static class RawCell {
        private Mob unit = null;
        private Wall wall = null;
        private Floor floor = new Floor();
    }

    public static class Direction {
        private final UnaryOperator<Coordinate> operator;
        private final Action action;

        private Direction(UnaryOperator<Coordinate> operator, Action action) {
            this.operator = operator;
            this.action = action;
        }

        public UnaryOperator<Coordinate> getOperator() {
            return operator;
        }

        public Action getAction() {
            return action;
        }
    }

    protected RawCell[][] cells;
    private Coordinate playerCoordinate;

    public Map(int height, int width) {
        cells = new RawCell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new RawCell();
            }
        }
    }

    public void setPlayerCoordinate(Coordinate playerCoordinate) {
        this.playerCoordinate = playerCoordinate;
    }

    public Cell getCell(Coordinate coord) {
        return new Cell(this, coord, cells[coord.getX()][coord.getY()]);
    }

    public Cell getCell(int x, int y) {
        return new Cell(this, new Coordinate(x, y), cells[x][y]);
    }

    public int getWidth() {
        return cells.length;
    }

    public int getHeight() {
        return cells[0].length;
    }

    public Coordinate getHigherCoordinate(Coordinate coordinate) {
        return coordinate.getY() > 0 ? coordinate.withY(y -> y - 1) : null;
    }

    public Coordinate getLowerCoordinate(Coordinate coordinate) {
        return coordinate.getY() < getHeight() - 1 ? coordinate.withY(y -> y + 1) : null;
    }

    public Coordinate getLefterCoordinate(Coordinate coordinate) {
        return coordinate.getX() > 0 ? coordinate.withX(x -> x - 1) : null;
    }

    public Coordinate getRighterCoordinate(Coordinate coordinate) {
        return coordinate.getX() < getWidth() - 1 ? coordinate.withX(x -> x + 1) : null;
    }

    public List<Direction> getAllDirections() {
        return List.of(
                new Direction(this::getHigherCoordinate, Action.MoveUp),
                new Direction(this::getLowerCoordinate, Action.MoveDown),
                new Direction(this::getLefterCoordinate, Action.MoveLeft),
                new Direction(this::getRighterCoordinate, Action.MoveRight)
        );
    }

    public Coordinate getCoordinateByAction(Coordinate coordinate, Action action) {
        return switch (action) {
            case MoveLeft, BifurcateLeft -> getLefterCoordinate(coordinate);
            case MoveRight, BifurcateRight -> getRighterCoordinate(coordinate);
            case MoveDown, BifurcateDown -> getLowerCoordinate(coordinate);
            case MoveUp, BifurcateUp -> getHigherCoordinate(coordinate);
            default -> null;
        };
    }

    public Coordinate findPlayerCoordinate() {
        if (playerCoordinate != null && (getCell(playerCoordinate).getUnit() == null || !(getCell(playerCoordinate).getUnit() instanceof Player))) {
            throw new RuntimeException("Invalid playerCoordinate");
        }
        return playerCoordinate;
    }
}
