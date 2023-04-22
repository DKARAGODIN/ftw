package pro.karagodin.models;

public class Map {
    protected Cell[][] cells;

    public Map() {
    }

    public Map(int height, int width) {
        cells = new Cell[height][width];
    }

    public Cell getCell(int row, int column) {
        return cells[row][column];
    }

    public int getHeight() {
        return cells.length;
    }

    public int getWidth() {
        return cells[0].length;
    }
}
