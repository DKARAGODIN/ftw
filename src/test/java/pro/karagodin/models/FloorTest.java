package pro.karagodin.models;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class FloorTest {

    @Test
    void testPickItem() {
        Floor floor = new Floor();
        Item item = new Item(new HashMap<>(), '!');
        floor.setItem(item);
        assertEquals(item, floor.pickItem());
        assertNull(floor.getItem());
    }
}