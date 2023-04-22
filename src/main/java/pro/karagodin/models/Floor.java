package pro.karagodin.models;

public class Floor {
    protected Cell cell;

    public Floor(Cell cell) {
        this.cell = cell;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
