package pro.karagodin.generators;

import pro.karagodin.models.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class ItemGenerator {

    private static final Random random = new Random();

    private ItemGenerator() {}

    public static List<Item> generateItems(int stage) {
        List<Item> result = new ArrayList<>();
        int itemsCount = 1 + stage / 3 + random.nextInt(2);
        for (int i = 0; i < itemsCount; i++) {
            Map<Item.Modifier, Integer> modifiers = new TreeMap<>();
            int modifiersCount = Math.min(8, Math.max(1, stage / 8 + random.nextInt(3)));
            int itemLevel = Math.min(stage, random.nextInt(4));
            for (int j = 0; j < modifiersCount; j++) {
                Item.Modifier modifier = Item.Modifier.values()[random.nextInt(Item.Modifier.values().length)];
                int value = modifier.getStartValue() + (int) modifier.getLevelIncrease() * itemLevel;
                value = random.nextInt((int) Math.floor(value * 0.90), (int) Math.ceil(value * 1.10));
                modifiers.put(modifier, value);
            }
            Item item = new Item(modifiers, 'T');
            result.add(item);
        }
        return result;
    }
}
