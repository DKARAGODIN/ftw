package pro.karagodin.generators;

import org.junit.jupiter.api.Test;
import pro.karagodin.models.Item;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemGeneratorTest {
    @Test
    public void test() {
        List<Item> items = ItemGenerator.generateItems(1);
    }
}