package pro.karagodin.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import pro.karagodin.models.SmallThing;

public class ItemGenerator {

    private static final Random random = new Random();
    private static final int ITEMS_PER_STAGE_DIVISOR = 3;
    private static final int MAX_MODIFIERS = 8;
    private static final int MODIFIERS_PER_STAGE_DIVISOR = 8;
    private static final double MIN_DISPERSION = 0.9;
    private static final double MAX_DISPERSION = 1.1;

    private ItemGenerator() {
    }

    public static List<SmallThing> generateItems(int stage) {
        List<SmallThing> result = new ArrayList<>();
        int itemsCount = 1 + stage / ITEMS_PER_STAGE_DIVISOR + random.nextInt(2);
        for (int i = 0; i < itemsCount; i++) {
            result.add(generateItem(stage));
        }
        return result;
    }

    private static SmallThing generateItem(int stage) {
        Map<SmallThing.Modifier, Integer> modifiers = new TreeMap<>();
        int modifiersCount = Math.min(MAX_MODIFIERS, Math.max(1, stage / MODIFIERS_PER_STAGE_DIVISOR + random.nextInt(3)));
        int itemLevel = Math.min(stage, random.nextInt(4));
        for (int j = 0; j < modifiersCount; j++) {
            var modifier = getRandomModivier();
            var value = modifier.getStartValue() + (int) modifier.getLevelIncrease() * itemLevel;
            value = random.nextInt((int) Math.floor(value * MIN_DISPERSION), (int) Math.ceil(value * MAX_DISPERSION));
            modifiers.put(modifier, value);
        }
        return new SmallThing(modifiers, 'T');
    }

    private static SmallThing.Modifier getRandomModivier() {
        return SmallThing.Modifier.values()[random.nextInt(SmallThing.Modifier.values().length)];
    }
}
