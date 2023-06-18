package pro.karagodin.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    private LootItem smallThing;
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        smallThing = new LootItem(new HashMap<>(), 1,'!');
        inventory = new Inventory();
    }

    @Test
    void addItemToBackpack() {
        inventory.addLootToStash(smallThing);
        List<LootItem> backpackSmallThings = inventory.getStashedSmallThings();
        assertEquals(1, backpackSmallThings.size());
        assertEquals(smallThing, backpackSmallThings.get(0));
    }

    @Test
    void removeItemFromBackpack() {
        inventory.addLootToStash(smallThing);
        inventory.removeLootFromStash(smallThing);
        List<LootItem> backpackSmallThings = inventory.getStashedSmallThings();
        assertTrue(backpackSmallThings.isEmpty());
    }

    @Test
    void equipItem() {
        inventory.addLootToStash(smallThing);
        inventory.equipLoot(smallThing);
        assertFalse(inventory.getStashedSmallThings().contains(smallThing));
        assertTrue(inventory.getEquippedSmallThings().contains(smallThing));
    }

    @Test
    void equipNonexistentItem() {
        assertThrows(IllegalArgumentException.class, () -> inventory.equipLoot(smallThing));
    }

    @Test
    void unequipItem() {
        inventory.addLootToStash(smallThing);
        inventory.equipLoot(smallThing);
        inventory.unequipLoot(smallThing);
        assertTrue(inventory.getStashedSmallThings().contains(smallThing));
        assertFalse(inventory.getEquippedSmallThings().contains(smallThing));
    }

    @Test
    void unequipNonexistentItem() {
        assertThrows(IllegalArgumentException.class, () -> inventory.unequipLoot(smallThing));
    }
}