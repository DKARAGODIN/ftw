package pro.karagodin.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    private Item item;
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        item = new Item();
        inventory = new Inventory();
    }

    @Test
    void addItemToBackpack() {
        inventory.addItemToBackpack(item);
        List<Item> backpackItems = inventory.getBackpackItems();
        assertEquals(1, backpackItems.size());
        assertEquals(item, backpackItems.get(0));
    }

    @Test
    void removeItemFromBackpack() {
        inventory.addItemToBackpack(item);
        inventory.removeItemFromBackpack(item);
        List<Item> backpackItems = inventory.getBackpackItems();
        assertTrue(backpackItems.isEmpty());
    }

    @Test
    void equipItem() {
        inventory.addItemToBackpack(item);
        inventory.equipItem(item);
        assertFalse(inventory.getBackpackItems().contains(item));
        assertTrue(inventory.getEquippedItems().contains(item));
    }

    @Test
    void equipNonexistentItem() {
        assertThrows(IllegalArgumentException.class, () -> inventory.equipItem(item));
    }

    @Test
    void unequipItem() {
        inventory.addItemToBackpack(item);
        inventory.equipItem(item);
        inventory.unequipItem(item);
        assertTrue(inventory.getBackpackItems().contains(item));
        assertFalse(inventory.getEquippedItems().contains(item));
    }

    @Test
    void unequipNonexistentItem() {
        assertThrows(IllegalArgumentException.class, () -> inventory.unequipItem(item));
    }
}