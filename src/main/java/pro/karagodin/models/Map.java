package pro.karagodin.models;

import java.util.List;
import java.util.function.UnaryOperator;

import pro.karagodin.ai_system.Action;
import pro.karagodin.game_engine.Coordinate;

/**
 * Play area: a two-dimensional array on which the positions of objects (walls, passages to other maps) are fixed.
 * Maps are generated automatically or loaded from a file.
 */
public class Map {
    public static class MapDirection {
        private final UnaryOperator<Coordinate> operator;
        private final Action action;

        private MapDirection(UnaryOperator<Coordinate> operator, Action action) {
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

    protected Cell[][] cells;

    public Map(int height, int width) {
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell();
            }
        }
    }

    public void setPlayer(Player player, Coordinate playerPlace) {
        cells[playerPlace.getX()][playerPlace.getY()].unit = player;
    }

    public Cell getCell(Coordinate coord) {
        return cells[coord.getX()][coord.getY()];
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

    public List<MapDirection> getAllDirections() {
        return List.of(
                new MapDirection(this::getHigherCoordinate, Action.MoveUp),
                new MapDirection(this::getLowerCoordinate, Action.MoveDown),
                new MapDirection(this::getLefterCoordinate, Action.MoveLeft),
                new MapDirection(this::getRighterCoordinate, Action.MoveRight)
        );
    }
}
