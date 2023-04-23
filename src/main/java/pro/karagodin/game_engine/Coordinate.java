package pro.karagodin.game_engine;

import java.util.function.UnaryOperator;

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
}
