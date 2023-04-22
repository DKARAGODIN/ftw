package pro.karagodin.models;

import pro.karagodin.game_engine.Coordinate;

public class Map {
    protected Cell[][] cells;

    /*public Map() {
    }*/

    public Map(int height, int width, Player player) {
        cells = new Cell[height][width];
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                cells[x][y] = new Cell();
            }
        }
        cells[97][30].unit = player;
    }

    public Cell getCell(int row, int column) {
        return cells[row][column];
    }

    public Cell getCell(Coordinate coord) {
        return cells[coord.getX()][coord.getY()];
    }

    public int getHeight() {
        return cells.length;
    }

    public int getWidth() {
        return cells[0].length;
    }
}
