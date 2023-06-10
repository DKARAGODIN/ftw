package pro.karagodin.generators;

import org.junit.jupiter.api.Test;
import pro.karagodin.models.LowerItem;

import java.util.List;

class SmallThingGeneratorTest {
    @Test
    public void test() {
        List<LowerItem> items = ItemGenerator.generateItems(1);
    }
}