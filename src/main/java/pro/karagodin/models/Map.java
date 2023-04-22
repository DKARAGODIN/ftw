package pro.karagodin.models;

import pro.karagodin.game_engine.Coordinate;

public class Map {
    protected Cell[][] cells;

    public Map(int height, int width, Player player) {
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell();
            }
        }
        cells[97][30].unit = player;
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
}
