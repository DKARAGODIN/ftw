package pro.karagodin.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    @Test
    public void testConstructor() {
        Map map = new Map(60, 195, new Player(100, 100));
        assertEquals(60, map.getHeight());
        assertEquals(195, map.getWidth());
    }
}