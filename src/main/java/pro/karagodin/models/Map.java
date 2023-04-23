package pro.karagodin.models;

import pro.karagodin.game_engine.Coordinate;

/**
 * Play area: a two-dimensional array on which the positions of objects (walls, passages to other maps) are fixed.
 * Maps are generated automatically or loaded from a file.
 */
public class Map {
    protected Cell[][] cells;

    public Map(int height, int width) {
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell();
            }
        }
    }
    public void setPlayer(Player player, Coordinate playerPlace){
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

}
