package pro.karagodin.game_engine;

import java.util.function.UnaryOperator;

import pro.karagodin.ai_system.Action;

/**
 * Represents physical coordinates of the screen. X - column, Y - row. Starting from left up corner. Only positive values allowed
 */
public class Coordinate {
    private int cX;
    private int cY;

    public Coordinate(int x, int y) {
        cX = x;
        cY = y;
    }

    public int getX() {
        return cX;
    }

    public int getY() {
        return cY;
    }

    public void setX(int x) {
        cX = x;
    }

    public void setY(int y) {
        cY = y;
    }

    public Coordinate withX(UnaryOperator<Integer> fX) {
        return new Coordinate(fX.apply(cX), cY);
    }

    public Coordinate withY(UnaryOperator<Integer> fY) {
        return new Coordinate(cX, fY.apply(cY));
    }

    public int getDistanceBetween(Coordinate other) {
        return Math.abs(this.getX() - other.getX()) + Math.abs(this.getY() - other.getY());
    }

    public Action getDirection(Coordinate other) {
        if (this.getX() == other.getX()) {
            if (this.getY() - 1 == other.getY()) {
                return Action.MoveUp;
            } else if (this.getY() + 1 == other.getY()) {
                return Action.MoveDown;
            } else {
                return null;
            }
        } else if (this.getY() == other.getY()) {
            if (this.getX() - 1 == other.getX()) {
                return Action.MoveLeft;
            } else if (this.getX() + 1 == other.getX()) {
                return Action.MoveRight;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
