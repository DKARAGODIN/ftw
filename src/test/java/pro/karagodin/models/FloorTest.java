package pro.karagodin.models;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class FloorTest {

    @Test
    void testPickItem() {
        Floor floor = new Floor();
        SmallThing smallThing = new SmallThing(new HashMap<>(), '!');
        floor.setItem(smallThing);
        assertEquals(smallThing, floor.pickItem());
        assertNull(floor.getItem());
    }
}