package pro.karagodin.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MobTest {
    @Test
    public void testConstructor() {

        Mob mob1 = new Mob(10);
        assertEquals(10, mob1.getHp(), 0.001);
        assertEquals(10, mob1.getMaxHp());

        Mob mob2 = new Mob(5, 10);
        assertEquals(5, mob2.getHp(), 0.001);
        assertEquals(10, mob2.getMaxHp());
    }
}