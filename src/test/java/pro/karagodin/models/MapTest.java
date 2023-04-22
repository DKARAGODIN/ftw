package pro.karagodin.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    @Test
    public void testConstructor() {
        Map map = new Map(10, 20);
        assertEquals(10, map.getHeight());
        assertEquals(20, map.getWidth());
    }
}