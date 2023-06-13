package pro.karagodin.generators;

import java.util.ArrayList;
import java.util.HashMap;
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
    private static final int PRIMARY_STAT_MULTIPLIER = 2;


    private static Map.Entry<Character, LootItem.Modifier>[] ITEMS_PROJECTION = new Map.Entry[MAX_MODIFIERS];

    static {
        Map<Character, LootItem.Modifier> map = new HashMap<>();
        for (int i = 0; i < MAX_MODIFIERS; i++) {
            int r = 0;
            do {
                r = 65 + random.nextInt(26);
            } while (map.containsKey((char) r));
            Character c = (char) r;
            map.put(c, LootItem.Modifier.values()[i]);
        }
        int i = 0;
        for (Map.Entry<Character, LootItem.Modifier> e : map.entrySet()) {
            ITEMS_PROJECTION[i] = e;
            i++;
        }
    }

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
        //Primary modifier
        Map.Entry<Character, LootItem.Modifier> e = ITEMS_PROJECTION[random.nextInt(MAX_MODIFIERS)];
        {
            var modifier = e.getValue();
            var value = modifier.getStartValue() + (int) modifier.getLevelIncrease() * itemLevel;
            value = random.nextInt((int) Math.floor(value * MIN_DISPERSION), (int) Math.ceil(value * MAX_DISPERSION));
            if (value == 0) value = 1;
            value *= PRIMARY_STAT_MULTIPLIER;
            modifiers.put(modifier, value);
        }
        //Additional modifiers
        for (int j = 1; j < modifiersCount; j++) {
            var modifier = getRandomModivier();
            var value = modifier.getStartValue() + (int) modifier.getLevelIncrease() * itemLevel;
            value = random.nextInt((int) Math.floor(value * MIN_DISPERSION), (int) Math.ceil(value * MAX_DISPERSION));
            if (value == 0) value = 1;
            modifiers.put(modifier, value);
        }
        return new LootItem(modifiers, e.getKey());
    }

    private static LootItem.Modifier getRandomModivier() {
        return LootItem.Modifier.values()[random.nextInt(LootItem.Modifier.values().length)];
    }
}
