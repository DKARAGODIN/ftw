package pro.karagodin.models;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class FloorTest {

    @Test
    void testPickItem() {
        Floor floor = new Floor();
        LootItem smallThing = new LootItem(new HashMap<>(), 1, '!');
        floor.setItem(smallThing);
        assertEquals(smallThing, floor.pickItem());
        assertNull(floor.getItem());
    }
}