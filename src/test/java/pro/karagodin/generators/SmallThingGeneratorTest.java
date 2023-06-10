package pro.karagodin.generators;

import org.junit.jupiter.api.Test;
import pro.karagodin.models.LootItem;

import java.util.List;

class SmallThingGeneratorTest {
    @Test
    public void test() {
        List<LootItem> smallThings = ItemGenerator.generateItems(1);
    }
}