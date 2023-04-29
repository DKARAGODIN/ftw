package pro.karagodin.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    private Item item;
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        item = new Item(new HashMap<>(), '!');
        inventory = new Inventory();
    }

    @Test
    void addItemToBackpack() {
        inventory.addItemToStash(item);
        List<Item> backpackItems = inventory.getStashedItems();
        assertEquals(1, backpackItems.size());
        assertEquals(item, backpackItems.get(0));
    }

    @Test
    void removeItemFromBackpack() {
        inventory.addItemToStash(item);
        inventory.removeItemFromStash(item);
        List<Item> backpackItems = inventory.getStashedItems();
        assertTrue(backpackItems.isEmpty());
    }

    @Test
    void equipItem() {
        inventory.addItemToStash(item);
        inventory.equipItem(item);
        assertFalse(inventory.getStashedItems().contains(item));
        assertTrue(inventory.getEquippedItems().contains(item));
    }

    @Test
    void equipNonexistentItem() {
        assertThrows(IllegalArgumentException.class, () -> inventory.equipItem(item));
    }

    @Test
    void unequipItem() {
        inventory.addItemToStash(item);
        inventory.equipItem(item);
        inventory.unequipItem(item);
        assertTrue(inventory.getStashedItems().contains(item));
        assertFalse(inventory.getEquippedItems().contains(item));
    }

    @Test
    void unequipNonexistentItem() {
        assertThrows(IllegalArgumentException.class, () -> inventory.unequipItem(item));
    }
}