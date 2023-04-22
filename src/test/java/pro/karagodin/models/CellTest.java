package pro.karagodin.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    @Test
    void testConstructor() {
        Cell cell = new Cell();
        assertNull(cell.getFloor());
        assertNull(cell.getUnit());
        assertNull(cell.getWall());
    }

}