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
    private static final int ITEMS_PER_STAGE_DIVISOR = 2;
    private static final int CONSUMABLES_PER_STAGE_DIVISOR = 1;
    private static final int MAX_MODIFIERS = LootItem.Modifier.values().length;
    private static final int MODIFIERS_PER_STAGE_DIVISOR = 5;
    private static final double MIN_DISPERSION = 0.9;
    private static final double MAX_DISPERSION = 1.1;
    private static final double PRIMARY_STAT_MULTIPLIER = 1.5;


    private static Pair[] ITEMS_PROJECTION = new Pair[MAX_MODIFIERS];

    static {
        ITEMS_PROJECTION[0] = new Pair('Q', LootItem.Modifier.values()[0]);
        ITEMS_PROJECTION[1] = new Pair('W', LootItem.Modifier.values()[1]);
        ITEMS_PROJECTION[2] = new Pair('E', LootItem.Modifier.values()[2]);
        ITEMS_PROJECTION[3] = new Pair('R', LootItem.Modifier.values()[3]);
        ITEMS_PROJECTION[4] = new Pair('T', LootItem.Modifier.values()[4]);
        ITEMS_PROJECTION[5] = new Pair('Y', LootItem.Modifier.values()[5]);
    }

    private ItemGenerator() {
    }

    public static List<LowerItem> generateItems(int stage) {
        List<LowerItem> result = new ArrayList<>();
        int lootItemsCount = stage / ITEMS_PER_STAGE_DIVISOR + random.nextInt(1);
        if (lootItemsCount < 1) lootItemsCount = 1;
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
        int itemLevel = stage + random.nextInt(4);
        int modifiersCount = Math.min(MAX_MODIFIERS, Math.max(1, 1 + (itemLevel / MODIFIERS_PER_STAGE_DIVISOR)));
        //Primary modifier
        Pair p = ITEMS_PROJECTION[random.nextInt(MAX_MODIFIERS)]; {
            var modifier = p.modifier;
            var value = modifier.getStartValue() + (int) (modifier.getLevelIncrease() * itemLevel);
            value = random.nextInt((int) Math.floor(value * MIN_DISPERSION), (int) Math.ceil(value * MAX_DISPERSION));
            if (value == 0) value = 1;
            value *= PRIMARY_STAT_MULTIPLIER;
            modifiers.put(modifier, value);
        }
        //Additional modifiers
        for (int j = 1; j < modifiersCount; j++) {
            var modifier = getRandomModivier();
            while (modifiers.containsKey(modifier)) {
                modifier = getRandomModivier();
            }
            var value = modifier.getStartValue() + (int) (modifier.getLevelIncrease() * itemLevel);
            value = random.nextInt((int) Math.floor(value * MIN_DISPERSION), (int) Math.ceil(value * MAX_DISPERSION));
            if (value == 0) value = 1;
            modifiers.put(modifier, value);
        }
        return new LootItem(modifiers, itemLevel, p.character);
    }

    private static LootItem.Modifier getRandomModivier() {
        return LootItem.Modifier.values()[random.nextInt(LootItem.Modifier.values().length)];
    }

    private static class Pair {
        Character character;
        LootItem.Modifier modifier;

        public Pair(Character character, LootItem.Modifier modifier) {
            this.character = character;
            this.modifier = modifier;
        }
    }
}
