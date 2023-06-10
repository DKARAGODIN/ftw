package pro.karagodin.generators;

import org.junit.jupiter.api.Test;
import pro.karagodin.models.SmallThing;

import java.util.List;

class SmallThingGeneratorTest {
    @Test
    public void test() {
        List<SmallThing> smallThings = ItemGenerator.generateItems(1);
    }
}