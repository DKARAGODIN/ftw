package pro.karagodin.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MobTest {
    @Test
    public void testConstructor() {
        Cell cell = new Cell(42, 24);

        Mob mob1 = new Mob(10, cell);
        assertEquals(10, mob1.getHp(), 0.001);
        assertEquals(10, mob1.getMaxHp());
        assertEquals(cell, mob1.getPosition());

        Mob mob2 = new Mob(5, 10, cell);
        assertEquals(5, mob2.getHp(), 0.001);
        assertEquals(10, mob2.getMaxHp());
        assertEquals(cell, mob2.getPosition());
    }
}