package pro.karagodin.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloorTest {

    @Test
    void testPickItem() {
        Floor floor = new Floor();
        Item item = new Item();
        floor.setItem(item);
        assertEquals(item, floor.pickItem());
        assertNull(floor.getItem());
    }
}