package pro.karagodin.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloorTest {
    @Test
    public void testConstructor() {
        Cell cell = new Cell(42, 24);
        Floor floor = new Floor(cell);
        assertEquals(cell, floor.getCell());
    }
}