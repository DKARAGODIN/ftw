package pro.karagodin.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    @Test
    void testConstructor() {
        Map.RawCell cell = new Map.RawCell();
        assertNotNull(cell.getFloor());
        assertNull(cell.getUnit());
        assertNull(cell.getWall());
    }

}