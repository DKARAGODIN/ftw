package pro.karagodin.models;

import org.junit.jupiter.api.Test;
import pro.karagodin.output.Printer;
import pro.karagodin.time.TimeInterval;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    @Test
    public void testConstructor() {
        Map map = new Map(10, 20, new Player(100, 100, new TimeInterval(100), new Printer()));
        assertEquals(10, map.getHeight());
        assertEquals(20, map.getWidth());
    }
}