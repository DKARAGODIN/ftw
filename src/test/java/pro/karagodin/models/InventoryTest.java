package pro.karagodin.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    private SmallThing smallThing;
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        smallThing = new SmallThing(new HashMap<>(), '!');
        inventory = new Inventory();
    }

    @Test
    void addItemToBackpack() {
        inventory.addSmallThingToStash(smallThing);
        List<SmallThing> backpackSmallThings = inventory.getStashedSmallThings();
        assertEquals(1, backpackSmallThings.size());
        assertEquals(smallThing, backpackSmallThings.get(0));
    }

    @Test
    void removeItemFromBackpack() {
        inventory.addSmallThingToStash(smallThing);
        inventory.removeSmallThingFromStash(smallThing);
        List<SmallThing> backpackSmallThings = inventory.getStashedSmallThings();
        assertTrue(backpackSmallThings.isEmpty());
    }

    @Test
    void equipItem() {
        inventory.addSmallThingToStash(smallThing);
        inventory.equipSmallThing(smallThing);
        assertFalse(inventory.getStashedSmallThings().contains(smallThing));
        assertTrue(inventory.getEquippedSmallThings().contains(smallThing));
    }

    @Test
    void equipNonexistentItem() {
        assertThrows(IllegalArgumentException.class, () -> inventory.equipSmallThing(smallThing));
    }

    @Test
    void unequipItem() {
        inventory.addSmallThingToStash(smallThing);
        inventory.equipSmallThing(smallThing);
        inventory.unequipSmallThing(smallThing);
        assertTrue(inventory.getStashedSmallThings().contains(smallThing));
        assertFalse(inventory.getEquippedSmallThings().contains(smallThing));
    }

    @Test
    void unequipNonexistentItem() {
        assertThrows(IllegalArgumentException.class, () -> inventory.unequipSmallThing(smallThing));
    }
}