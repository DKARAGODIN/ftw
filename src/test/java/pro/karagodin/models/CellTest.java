package pro.karagodin.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    @Test
    void testConstructor() {
        Cell cell = new Cell(1, 2);
        assertEquals(cell.getX(), 1);
        assertEquals(cell.getY(), 2);
    }

}