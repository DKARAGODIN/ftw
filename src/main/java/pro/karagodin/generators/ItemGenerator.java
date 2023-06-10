package pro.karagodin.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import pro.karagodin.models.ConsumableItem;
import pro.karagodin.models.LootItem;
import pro.karagodin.models.LowerItem;

public class ItemGenerator {

    private static final Random random = new Random();
    private static final int ITEMS_PER_STAGE_DIVISOR = 3;
    private static final int CONSUMABLES_PER_STAGE_DIVISOR = 2;
    private static final int MAX_MODIFIERS = LootItem.Modifier.values().length;
    private static final int MODIFIERS_PER_STAGE_DIVISOR = 8;
    private static final double MIN_DISPERSION = 0.9;
    private static final double MAX_DISPERSION = 1.1;

    private ItemGenerator() {
    }

    public static List<LowerItem> generateItems(int stage) {
        List<LowerItem> result = new ArrayList<>();
        int lootItemsCount = 1 + stage / ITEMS_PER_STAGE_DIVISOR + random.nextInt(2);
        for (int i = 0; i < lootItemsCount; i++) {
            result.add(generateItem(stage));
        }
        int consumablesItemsCount = 1 + stage / CONSUMABLES_PER_STAGE_DIVISOR + random.nextInt(2);
        for (int i = 0; i < consumablesItemsCount; i++) {
            result.add(generateConsumable(stage));
        }

        return result;
    }

    private static LowerItem generateConsumable(int stage) {
        return new ConsumableItem(10 + stage * (random.nextInt(10)));
    }

    private static LootItem generateItem(int stage) {
        Map<LootItem.Modifier, Integer> modifiers = new TreeMap<>();
        int modifiersCount = Math.min(MAX_MODIFIERS, Math.max(1, stage / MODIFIERS_PER_STAGE_DIVISOR + random.nextInt(3)));
        int itemLevel = Math.min(stage, random.nextInt(4));
        for (int j = 0; j < modifiersCount; j++) {
            var modifier = getRandomModivier();
            var value = modifier.getStartValue() + (int) modifier.getLevelIncrease() * itemLevel;
            value = random.nextInt((int) Math.floor(value * MIN_DISPERSION), (int) Math.ceil(value * MAX_DISPERSION));
            modifiers.put(modifier, value);
        }
        return new LootItem(modifiers, 'T');
    }

    private static LootItem.Modifier getRandomModivier() {
        return LootItem.Modifier.values()[random.nextInt(LootItem.Modifier.values().length)];
    }
}
