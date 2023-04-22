package pro.karagodin.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {
    @Test
    public void testConstructor() {
        Cell cell = new Cell(42, 24);
        Wall wall = new Wall(cell);
        assertEquals(cell, wall.getCell());
    }
}