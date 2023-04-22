package pro.karagodin.models;

public class Wall {
    protected Cell cell;

    public Wall(Cell cell) {
        this.cell = cell;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
